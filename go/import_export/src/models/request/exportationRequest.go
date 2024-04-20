package request

type ExportationRequest struct {
	Account         string   `json:"account"`
	CredentialsType []string `json:"credentials_type"`
	FormatToExport  string   `json:"format_to_export"`
}
