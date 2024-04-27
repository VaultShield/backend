package routers

import (
	"github.com/VaultShield/handlers"
	"github.com/gofiber/fiber/v2"
)

func exportation(api fiber.Router) {
	api.Post("/export", handlers.ToExportHandler())
}
