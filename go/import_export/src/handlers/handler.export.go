package handlers

import (
	"encoding/base64"
	"encoding/json"
	"fmt"
	"log"

	"github.com/VaultShield/handlers/error_handler"
	"github.com/VaultShield/models/request"
	"github.com/VaultShield/models/response"
	"github.com/VaultShield/services"
	"github.com/gofiber/fiber/v2"
)

func ToExportHandler() fiber.Handler {
	return func(c *fiber.Ctx) error {
		body := c.Body()
		var rqst request.ExportationRequest

		if err := json.Unmarshal(body, &rqst); err != nil {
			log.Println("ERROR:", err)
			return c.Status(fiber.StatusBadRequest).JSON(response.StandardHttpResponse{
				Message: fiber.ErrBadRequest.Message,
				Status:  fiber.StatusBadRequest,
				Data:    nil,
			})
		}

		if rqst.CredentialsType == "password" {
			var attachment string
			var dataByte []byte
			var err error

			if rqst.FormatToExport == "json" {
				dataByte, err = services.ToJSONExport(rqst)
				if err != nil {
					if err == error_handler.Err_NOT_FOUND {
						return c.Status(fiber.StatusNotFound).JSON(response.StandardHttpResponse{
							Message: "this user does not have passwords",
							Status:  fiber.StatusNotFound,
							Data:    nil,
						})
					}

					return c.Status(fiber.StatusInternalServerError).JSON(response.StandardHttpResponse{
						Message: "Error generating export",
						Status:  fiber.StatusInternalServerError,
						Data:    nil,
					})
				}
			} else {
				errorString := fmt.Sprintf("unimplemented export %s type, try with 'json'", rqst.FormatToExport)
				return c.Status(fiber.StatusServiceUnavailable).JSON(fiber.Map{
					"Message": errorString,
				})
			}

			attachment = fmt.Sprintf("attachment; filename=%s.json", rqst.Account)

			c.Set("Content-Disposition", attachment)
			c.Set("Content-Type", "application/json")

			encodedData := base64.StdEncoding.EncodeToString(dataByte)

			return c.Status(fiber.StatusCreated).JSON(encodedData)
		}
		errorString := fmt.Sprintf("unimplemented credetial %s type, try with 'password'", rqst.CredentialsType)
		return c.Status(fiber.StatusNotImplemented).JSON(fiber.Map{
			"Message": errorString,
		})
	}
}
