package main

import (
	"log"

	"github.com/VaultShield/repository"
	"github.com/VaultShield/routers"
	"github.com/gofiber/fiber/v3"
)

func main() {
	db := repository.ConnectionDB()
	db.Ping()

	app := fiber.New()
	routers.Setup(app)

	err := app.Listen(":8081")
	if err != nil {
		log.Fatal(err)
	}
}
