package middlewares

import (
	"net/http"

	"github.com/gofiber/fiber/v2"
)

const validationServiceURL = "" // to do

func TokenValidation(c *fiber.Ctx) error {
	token := c.Get("Authorization")
	if token == "" {
		return c.Status(fiber.StatusUnauthorized).JSON(fiber.Map{
			"Message": "No token provied",
		})
	}

	req, _ := http.NewRequest("GET", validationServiceURL, nil)
	req.Header.Add("Authorization", token)
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		return c.SendStatus(fiber.StatusInternalServerError)
	}

	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		return c.Status(fiber.StatusUnauthorized).JSON(fiber.Map{
			"Message": "Unauthorized",
		})
	}

	return c.Next()
}
