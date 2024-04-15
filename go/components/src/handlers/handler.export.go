package handlers

import (
	"encoding/json"
	"fmt"
	"log"

	"github.com/VaultShield/models/request"
	"github.com/VaultShield/models/response"
	"github.com/VaultShield/services"
	"github.com/gofiber/fiber/v3"
)

func ToExportHandler() fiber.Handler {
	return func(c fiber.Ctx) error {
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

		dataByte, err := services.ToJSONExport(rqst.Username, rqst.FormatToExport, rqst.CredentialsType)
		if err != nil {
			return c.Status(fiber.StatusInternalServerError).JSON(response.StandardHttpResponse{
				Message: "Error generating export",
				Status:  fiber.StatusInternalServerError,
				Data:    nil,
			})
		}

		attachment := fmt.Sprintf("attachment; filename=%s.json", rqst.Username)

		c.Set("Content-Disposition", attachment)
		c.Set("Content-Type", "application/json")

		return c.Status(fiber.StatusCreated).JSON(dataByte)
	}
}
