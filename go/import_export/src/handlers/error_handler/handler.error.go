package error_handler

import (
	"fmt"
)

var (
	Err_NOT_AUTHORIZED error = NewErrorHandler("unauthorized access")
	Err_NOT_FOUND      error = NewErrorHandler("a user has not been found or does not have passwords")
)

type ErrorHandler struct {
	msg string
}

func NewErrorHandler(message string) *ErrorHandler {
	return &ErrorHandler{msg: message}
}

func (e *ErrorHandler) Error() string {
	return e.msg
}

func (e *ErrorHandler) ToString() string {
	return fmt.Sprintf("Error: %s", e.msg)
}
