package repository

import "github.com/VaultShield/models"

func NewSettings(set Settings) *Settings {
	return &Settings{
		Hostname: set.Hostname,
		Port:     set.Port,
		User:     set.User,
		Password: set.Password,
		Database: set.Database,
	}
}

func RequireUserCredentials(username string) models.Credentials {
	sets := GetEnvSettings()
	set := NewSettings(sets)
}
