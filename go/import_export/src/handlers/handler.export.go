package handlers

import (
	"encoding/base64"
	"encoding/json"
	"fmt"
	"log"
	"net/http"

	"github.com/VaultShield/models/request"
	"github.com/VaultShield/models/response"
	"github.com/VaultShield/services"
)

// func ToExportHandler() fiber.Handler {
// 	return func(c *fiber.Ctx) error {
// 		body := c.Body()
// 		var rqst request.ExportationRequest

// 		if err := json.Unmarshal(body, &rqst); err != nil {
// 			log.Println("ERROR:", err)
// 			return c.Status(fiber.StatusBadRequest).JSON(response.StandardHttpResponse{
// 				Message: fiber.ErrBadRequest.Message,
// 				Data:    nil,
// 			})
// 		}

// 		if rqst.CredentialsType == "password" {
// 			var attachment string
// 			var dataByte []byte
// 			var err error

// 			if rqst.FormatToExport == "json" {
// 				dataByte, err = services.ToJSONExport(rqst)
// 				if err != nil {
// 					if err == error_handler.Err_NOT_FOUND {
// 						return c.Status(fiber.StatusNotFound).JSON(response.StandardHttpResponse{
// 							Message: "this user does not have passwords",
// 							Data:    nil,
// 						})
// 					}

// 					return c.Status(fiber.StatusInternalServerError).JSON(response.StandardHttpResponse{
// 						Message: "Error generating export",
// 						Data:    nil,
// 					})
// 				}
// 			} else {
// 				errorString := fmt.Sprintf("unimplemented export %s type, try with 'json'", rqst.FormatToExport)
// 				return c.Status(fiber.StatusServiceUnavailable).JSON(fiber.Map{
// 					"Message": errorString,
// 				})
// 			}

// 			attachment = fmt.Sprintf("attachment; filename=%s.json", rqst.Username)

// 			c.Set("Content-Disposition", attachment)
// 			c.Set("Content-Type", "application/json")

// 			encodedData := base64.StdEncoding.EncodeToString(dataByte)

// 			return c.Status(fiber.StatusCreated).JSON(encodedData)
// 		}
// 		errorString := fmt.Sprintf("unimplemented credetial %s type, try with 'password'", rqst.CredentialsType)
// 		return c.Status(fiber.StatusNotImplemented).JSON(fiber.Map{
// 			"Message": errorString,
// 		})
// 	}
// }

func ToExportHandler2(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPost {
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
		return
	}
	var request request.ExportationRequest
	err := json.NewDecoder(r.Body).Decode(&request)
	if err != nil {
		log.Println("ERROR:", err)
		respondWithError(w, "Bad Request", http.StatusBadRequest)
		return
	}

	if request.CredentialsType == "password" {
		var attachment string
		var dataByte []byte

		if request.FormatToExport == "json" {
			dataByte, err = services.ToJSONExport(request)
			if err != nil {
				if err.Error() == "NotFound" {
					respondWithError(w, "this user does not have passwords", http.StatusNotFound)
					return
				}

				respondWithError(w, fmt.Sprintf("Error generating export %s", err), http.StatusInternalServerError)
				return
			}
		} else {
			errorString := fmt.Sprintf("unimplemented export %s type, try with 'json'", request.FormatToExport)
			respondWithError(w, errorString, http.StatusServiceUnavailable)
			return
		}

		attachment = fmt.Sprintf("attachment; filename=%s.json", request.Username)
		w.Header().Set("Content-Disposition", attachment)
		w.Header().Set("Content-Type", "application/json")
		encodedData := base64.StdEncoding.EncodeToString(dataByte)
		w.WriteHeader(http.StatusCreated)
		w.Write([]byte(encodedData))
		return
	}
}

func respondWithError(w http.ResponseWriter, message string, statuscode int) {
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
