package repository

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/lib/pq"
)

func ConnectionDB() *sql.DB {
	settings := setSettings()

	if settings == nil {
		log.Println(&settings)
		panic("clean settings")
	}

	dsn := fmt.Sprintf("host=%s port=%d user=%s password=%s dbname=%s sslmode=disable",
		settings.Hostname, settings.Port, settings.Username, settings.Password, settings.Database)

	db, err := sql.Open("postgres", dsn)
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	err = db.Ping()
	if err != nil {
		log.Fatal(err)
	}

	log.Println("connected")

	return db
}
