FROM golang:1.22 as builder

WORKDIR /app

COPY go.mod go.sum ./
RUN go mod download

COPY . .

RUN CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o go-service .
FROM scratch
COPY --from=builder /app/go-service /go-service

EXPOSE 8085
 
CMD ["./go-service"]
