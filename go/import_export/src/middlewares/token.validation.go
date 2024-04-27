package middlewares

import (
	"github.com/gofiber/fiber/v2"
)

const validationServiceURL = ""

func tokenValidation(c *fiber.Ctx) error {
	token := c.Get("Authorization")
	if token == "" {
		return c.Status(fiber.StatusUnauthorized).JSON(fiber.Map{
			"Message": "No token provied",
		})
	}

}
