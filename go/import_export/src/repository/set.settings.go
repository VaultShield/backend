package repository

import (
	"os"
	"strconv"
)

func GetEnvSettings() Settings {

	hostname := os.Getenv("HOSTNAME")
	username := os.Getenv("USERNAME")
	password := os.Getenv("PASSWORD")
	database := os.Getenv("DATABASE")
	port := os.Getenv("PORT")

	p, err := strconv.ParseUint(port, 10, 16)
	if err != nil {
		panic(err)
	}

	settings := Settings{
		Hostname: hostname,
		User:     username,
		Password: password,
		Database: database,
		Port:     p,
	}

	return settings
}
