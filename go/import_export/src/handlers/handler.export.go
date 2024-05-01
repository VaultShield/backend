package handlers

import (
	"encoding/base64"
	"encoding/json"
	"fmt"
	"log"
	"net/http"

	"github.com/VaultShield/handlers/error_handler"
	"github.com/VaultShield/models/request"
	"github.com/VaultShield/models/response"
	"github.com/VaultShield/services"
)

// Handler to manage the actions to be done, based on the request sent,
// it will execute the ToJsonExport or ToCsvExport function if available
func ToExportHandler(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPost {
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
		return
	}
	var request request.ExportationRequest
	err := json.NewDecoder(r.Body).Decode(&request)
	if err != nil {
		log.Println("ERROR:", err)
		RespondWithError(w, "Bad Request", http.StatusBadRequest)
		return
	}

	if request.Username == "" || request.CredentialsType == "" || request.FormatToExport == "" {
		RespondWithError(w, "Bad Request", http.StatusBadRequest)
	}

	if request.CredentialsType == "password" {
		var attachment string
		var dataByte []byte

		if request.FormatToExport == "json" {
			dataByte, err = services.ToJSONExport(request)
			if err != nil {
				if err == error_handler.Err_NOT_FOUND {
					RespondWithError(w, error_handler.Err_NOT_FOUND.Error(), http.StatusNotFound)
					return
				} else {
					RespondWithError(w, "Internal Server Error", http.StatusInternalServerError)
				}

				RespondWithError(w, fmt.Sprintf("Error generating export %s", err), http.StatusInternalServerError)
				return
			}
		} else {
			errorString := fmt.Sprintf("unimplemented export %s type, try with 'json'", request.FormatToExport)
			RespondWithError(w, errorString, http.StatusServiceUnavailable)
			return
		}

		attachment = fmt.Sprintf("attachment; filename=%s.json", request.Username)
		w.Header().Set("Content-Disposition", attachment)
		w.Header().Set("Content-Type", "application/json")
		encodedData := base64.StdEncoding.EncodeToString(dataByte)
		w.WriteHeader(http.StatusCreated)
		w.Write([]byte(encodedData))
		return
	} else {
		errorString := fmt.Sprintf("unimplemented export %s credential, try with 'password'", request.CredentialsType)
		RespondWithError(w, errorString, http.StatusServiceUnavailable)
		return
	}
}

func RespondWithError(w http.ResponseWriter, message string, statuscode int) {
	resp := response.StandardHttpResponse{
		Message: message,
		Data:    nil,
	}
	jsonResp, err := json.Marshal(resp)
	if err != nil {
		http.Error(w, "Error processing the request", http.StatusInternalServerError)
		return
	}
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(statuscode)
	w.Write(jsonResp)
}
