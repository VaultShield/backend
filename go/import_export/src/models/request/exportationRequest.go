package request

type ExportationRequest struct {
	Username        string   `json:"username"`
	CredentialsType []string `json:"credentials_type"`
	FormatToExport  string   `json:"format_to_export"`
}
