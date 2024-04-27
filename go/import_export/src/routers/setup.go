package routers

import "github.com/gofiber/fiber/v2"

func Setup(app *fiber.App) {
	api := app.Group("/api/go")

	exportation(api)
}
