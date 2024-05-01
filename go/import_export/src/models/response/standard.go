package response

type StandardHttpResponse struct {
	Message string      `json:"message"`
	Data    interface{} `json:"data"`
}
