package main

import (
	"fmt"
	"log"
	"net/http"
	"os"

	"github.com/VaultShield/handlers"
	"github.com/VaultShield/middlewares"
	"github.com/VaultShield/repository"
)

func main() {
	PORT := os.Getenv("SERVER_PORT")
	if PORT == "" {
		PORT = "8085" // default port
	}
	db, err := repository.ConnectionDB()
	if err != nil {
		panic(err)
	}
	repository.DB = db
	defer db.Close()

	// The only access route
	http.Handle("/api/intern/export", middlewares.TokenValidation(http.HandlerFunc(handlers.ToExportHandler)))
	// TODO: import route

	fmt.Printf("Server is starting on http://localhost:%s\n", PORT)
	if err := http.ListenAndServe(fmt.Sprintf(":%s", PORT), nil); err != nil {
		log.Fatalf("Failed to start server: %v", err)
	}
}
