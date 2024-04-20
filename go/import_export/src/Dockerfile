FROM golang:1.22

WORKDIR /usr/src/app

COPY . .

RUN go mod download && go mod verify

COPY . .
RUN go build -v -o /usr/local/bin/app ./...

CMD ["app"]