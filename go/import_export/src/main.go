package main

import (
	"log"

	"github.com/VaultShield/repository"
	"github.com/VaultShield/routers"
	"github.com/gofiber/fiber/v2"
)

func main() {

	db, err := repository.ConnectionDB()
	if err != nil {
		panic(err)
	}
	repository.DB = db
	defer db.Close()

	app := fiber.New()
	routers.Setup(app)

	err = app.Listen(":4000")
	if err != nil {
		log.Fatal(err)
	}
}
