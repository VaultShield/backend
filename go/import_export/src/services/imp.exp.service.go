package services

import (
	"database/sql"
	"encoding/json"
	"log"

	"github.com/VaultShield/models"
	"github.com/VaultShield/models/request"
	"github.com/VaultShield/repository"
)

func Exportation(db *sql.DB) {

}

func ToJSONExport(rqst request.ExportationRequest) ([]byte, error) {
	var passwords []models.Password

	tx, err := repository.DB.Begin()
	if err != nil {
		return nil, err
	}
	defer tx.Rollback()

	query := `
        			SELECT p.title, p.account, p.password, p.note
        			FROM User u
        			JOIN Credentials c ON u.id = c.user_id
        			JOIN Password p ON c.id = p.credentials_id
        			WHERE u.username = $1 AND u.active = true;
    					`

	rows, err := tx.Query(query, rqst.Account)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	for rows.Next() {
		var p models.Password
		if err := rows.Scan(&p.Title, &p.Account, &p.Pwd, &p.Note); err != nil {
			return nil, err
		}
		passwords = append(passwords, p)
	}

	if err = rows.Err(); err != nil {
		return nil, err
	}

	err = tx.Commit()
	if err != nil {
		return nil, err
	}

	dataByte, err := json.Marshal(passwords)
	if err != nil {
		log.Println("ERROR", err)
		return nil, err
	}

	return dataByte, nil
}
