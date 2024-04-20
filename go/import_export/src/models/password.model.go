package models

type Password struct {
	Title   string `json:"title"`
	Account string `json:"account"`
	Pwd     string `json:"password"`
	Note    string `json:"note"`
}
