package error_handler

import "errors"

var (
	Err_NOT_AUTHORIZED error = errors.New("unauthorized access")
	Err_NOT_FOUND      error = errors.New("a user has not been found or does not have passwords")
)
