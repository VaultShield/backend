package middlewares

import (
	"log"
	"net/http"

	"github.com/gofiber/fiber/v2"
)

const validationServiceURL = "http://java-service:8080/api/intern/validate"

func TokenValidation(c *fiber.Ctx) error {
	token := c.Get("Authorization")
	if token == "" {
		return c.Status(fiber.StatusUnauthorized).JSON(fiber.Map{
			"Message": "No token provied",
		})
	}

	req, err := http.NewRequest("GET", validationServiceURL, nil)
	if err != nil {
		log.Println(err)
		return err
	}

	req.Header.Add("Authorization", token)
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.Println(err)
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
