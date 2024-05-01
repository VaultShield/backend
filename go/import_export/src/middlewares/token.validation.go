package middlewares

import (
	"log"
	"net/http"

	"github.com/VaultShield/handlers"
)

// This constant defines how a route works with a Docker environment
const validationServiceURL = "http://java-service:8080/api/intern/validate"

// Middleware to validate a token with the API Gateway, waits for an ok, otherwise it gives unauthorized access
//
//	const validationServiceURL // with a constant URI
func TokenValidation(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		token := r.Header.Get("Authorization")
		if token == "" {
			handlers.RespondWithError(w, "no token provided", http.StatusBadRequest)
		}

		client := &http.Client{}
		req, err := http.NewRequest("GET", validationServiceURL, nil)
		if err != nil {
			log.Println(err)
			handlers.RespondWithError(w, "Internal Error", http.StatusInternalServerError)
		}

		req.Header.Add("Authorization", token)
		resp, err := client.Do(req)
		if err != nil {
			log.Println(err)
			handlers.RespondWithError(w, "Internal Error", http.StatusInternalServerError)
		}

		defer resp.Body.Close()

		if resp.StatusCode != http.StatusOK {
			http.Error(w, "Unauthorized", http.StatusUnauthorized)
			return
		}
		next.ServeHTTP(w, r)
	})
}
