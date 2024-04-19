package handlers

import (
	"encoding/csv"
	"encoding/json"
	"errors"
	"fmt"
	"log"
	"os"
	"reflect"
	"time"

	"github.com/VaultShield/models/request"
	"github.com/VaultShield/models/response"
	"github.com/VaultShield/services"
	"github.com/gofiber/fiber/v3"
)

func ToExportHandler() fiber.Handler {
	return func(c fiber.Ctx) error {
		body := c.Body()
		var rqst request.ExportationRequest

		if err := json.Unmarshal(body, &rqst); err != nil {
			log.Println("ERROR:", err)
			return c.Status(fiber.StatusBadRequest).JSON(response.StandardHttpResponse{
				Message: fiber.ErrBadRequest.Message,
				Status:  fiber.StatusBadRequest,
				Data:    nil,
			})
		}

		dataByte, err := services.ToJSONExport(rqst.Username, rqst.FormatToExport, rqst.CredentialsType)
		if err != nil {
			return c.Status(fiber.StatusInternalServerError).JSON(response.StandardHttpResponse{
				Message: "Error generating export",
				Status:  fiber.StatusInternalServerError,
				Data:    nil,
			})
		}

		attachment := fmt.Sprintf("attachment; filename=%s.json", rqst.Username)

		c.Set("Content-Disposition", attachment)
		c.Set("Content-Type", "application/json")

		err = exportation(rqst.Username, rqst)
		if err != nil {
			panic(err)
		}

		return c.Status(fiber.StatusCreated).JSON(dataByte)
	}
}

func exportation(account string, rqst request.ExportationRequest) error {
	if rqst.CredentialsType == nil || rqst.FormatToExport == "" {
		return errors.New("error")
	}

	for i, v := range rqst.CredentialsType {
		if v == "cards" || v == "passwords" {

			switch v {
			case "passwords":
				//db connect
				var passwordsContainer string

				if rqst.FormatToExport == "json" {
					err := jsonExport(account, v, passwordsContainer)
					if err != nil {
						return err
					}
				} else if rqst.FormatToExport == "csv" {
					csvExport(account, v, passwordsContainer)
				}

			case "cards":
				var cardsContainer string
				//db connect

				if rqst.FormatToExport == "json" {
					err := jsonExport(account, v, cardsContainer)
					if err != nil {
						return err
					}
				} else if rqst.FormatToExport == "csv" {
					err := csvExport(account, v, cardsContainer)
					if err != nil {
						return err
					}
				}
			default:
				log.Printf("invalid type credentials: index %d with value %s", i, v)
				return errors.New("invalid type credentials or empty")
			}
		} else {
			log.Printf("invalid type credentials: index %d with value %s", i, v)
			return errors.New("invalid type credentials or empty")
		}

	}

	return nil

}

func jsonExport(account, typeExport string, container any) error {
	filename := fmt.Sprintf("%v-%v.json", account, typeExport)
	containerBytes, err := json.MarshalIndent(container, "", "\t")
	if err != nil {
		log.Println("ERROR", err)
		return errors.New("error")
	}

	err = os.WriteFile(filename, containerBytes, 0664)
	if err != nil {
		log.Println(err)
		return errors.New("error")
	}

	return nil
}

func csvExport(account, typeExport string, container any) error {
	filename := fmt.Sprintf("%v-%v.csv", account, typeExport)
	file, err := os.Create(filename)
	if err != nil {
		log.Println("ERROR", err)
		return err
	}
	defer file.Close()

	writer := csv.NewWriter(file)
	defer writer.Flush()

	rv := reflect.ValueOf(container)
	if rv.Kind() != reflect.Slice {
		log.Println("ERROR: container is not a slice")
		return err
	}

	if rv.Len() > 0 {
		elemType := rv.Index(0).Type()
		header := []string{}
		for i := 0; i < elemType.NumField(); i++ {
			if elemType.Field(i).PkgPath == "" { // PkgPath is empty for exported fields
				header = append(header, elemType.Field(i).Name)
			}
		}
		if err := writer.Write(header); err != nil {
			log.Println("ERROR writing header:", err)
			return err
		}
	}

	for i := 0; i < rv.Len(); i++ {
		elem := rv.Index(i)
		record := []string{}
		for j := 0; j < elem.NumField(); j++ {
			field := elem.Field(j)
			if elem.Type().Field(j).PkgPath != "" { // Skip unexported fields
				continue
			}

			var value string
			switch field.Kind() {
			case reflect.String:
				value = field.String()
			case reflect.Uint, reflect.Uint8, reflect.Uint16, reflect.Uint32, reflect.Uint64:
				value = fmt.Sprintf("%d", field.Uint())
			case reflect.Struct:
				if t, ok := field.Interface().(time.Time); ok {
					value = t.Format(time.RFC3339)
				} else {
					value = fmt.Sprintf("%v", field.Interface())
				}
			default:
				value = fmt.Sprintf("%v", field.Interface())
			}

			record = append(record, value)
		}
		if err := writer.Write(record); err != nil {
			log.Println("ERROR writing record:", err)
			return err
		}
	}

	return nil
}
