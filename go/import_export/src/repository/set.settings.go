package repository

import (
	"strconv"
)

func setSettings() *Settings {
	// hostname := os.Getenv("HOSTNAME")
	// username := os.Getenv("USER")
	// password := os.Getenv("PASSWORD")
	// database := os.Getenv("DATABASE")
	// port := os.Getenv("PORT")

	hostname := "localhost"
	username := "postgres"
	password := "admin"
	database := "db"
	port := "5432"

	p, err := strconv.ParseUint(port, 10, 64)
	if err != nil {
		panic(err)
	}

	return &Settings{
		Hostname: hostname,
		Username: username,
		Password: password,
		Port:     p,
		Database: database,
	}
}
