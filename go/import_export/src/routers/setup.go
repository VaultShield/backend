package routers

import (
	"github.com/VaultShield/middlewares"
	"github.com/gofiber/fiber/v2"
)

func Setup(app *fiber.App) {
	api := app.Group("/api/go", middlewares.TokenValidation)

	exportation(api)
}
