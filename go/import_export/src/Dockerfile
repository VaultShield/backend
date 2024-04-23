FROM golang:1.22 as builder

WORKDIR /app

COPY . .

# Compilación!
#RUN CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o go-service1 .
#FROM scratch
#COPY --from=builder /app/myapp .

RUN go mod download

EXPOSE 4000
 
CMD ["go", "run", "."]
# CMD ["./myapp"]
