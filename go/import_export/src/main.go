package main

import (
	"fmt"
	"log"
	"net/http"
	"os"

	"github.com/VaultShield/handlers"
	"github.com/VaultShield/repository"
)

func main() {
	PORT := os.Getenv("SERVER-PORT")
	if PORT == "" {
		PORT = "8085"
	}
	db, err := repository.ConnectionDB()
	if err != nil {
		panic(err)
	}
	repository.DB = db
	defer db.Close()

	// Standard library
	http.HandleFunc("/api/intern/export", handlers.ToExportHandler)
	fmt.Printf("Server is starting on http://localhost:%s\n", PORT)
	if err := http.ListenAndServe(fmt.Sprintf(":%s", PORT), nil); err != nil {
		log.Fatalf("Failed to start server: %v", err)
	}
}
