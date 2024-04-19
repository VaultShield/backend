package services

import (
	"encoding/json"
	"log"

	"github.com/VaultShield/models"
)

func ToJSONExport(username, FormatToExport string, credentialsType []string) ([]byte, error) {
	password := models.Password{
		Title:    "test",
		Username: "test",
		Pwd:      "test",
	}

	dataByte, err := json.Marshal(password)
	if err != nil {
		log.Println("ERROR", err)
		return nil, err
	}

	return dataByte, nil
}
