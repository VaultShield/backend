package repository

import (
	"database/sql"
	"errors"
	"fmt"
	"log"

	_ "github.com/lib/pq"
)

var DB *sql.DB

func ConnectionDB() (*sql.DB, error) {
	settings := setSettings()

	if settings == nil {
		log.Println("Clean settings: ", &settings)
		return nil, errors.New("clean settings")
	}

	dsn := fmt.Sprintf("host=%s port=%d user=%s password=%s dbname=%s sslmode=disable",
		settings.Hostname, settings.Port, settings.Username, settings.Password, settings.Database)

	db, err := sql.Open("postgres", dsn)
	if err != nil {
		log.Println(err)
		return nil, err
	}

	err = db.Ping()
	if err != nil {
		log.Println(err)
		return nil, err
	}

	log.Printf("connected to %s:%v", settings.Hostname, settings.Port)

	return db, nil
}
