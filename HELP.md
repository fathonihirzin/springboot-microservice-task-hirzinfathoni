# Simple Book Management API

Spring Boot 4.1.1 · Java 17 · JPA · PostgreSQL · Lombok

## Requirements

- Docker with Compose v2

## Quick start

```bash
git clone <repo>
cd springboot-microservice-task-hirzinfathoni
cp .env
docker compose up --build -d
```

API is live at http://localhost:8080/api/books

## Endpoints

| Method | Path | Description |
|---|---|---|
| POST   | /api/books      | Create a book |
| GET    | /api/books      | List all books |
| GET    | /api/books/{id} | Get one book |
| PUT    | /api/books/{id} | Full update |
| PATCH  | /api/books/{id} | Partial update |
| DELETE | /api/books/{id} | Delete |

## Common commands

```bash
# Build and start
docker compose up --build -d

# Stop (keep data)
docker compose down

# Stop and wipe DB
docker compose down -v

# Follow app logs
docker compose logs -f app

# Restart just the app
docker compose restart app

# Open psql
docker compose exec postgres psql -U postgres -d book_db
```

## Local development (without Docker for the app)

```bash
# Start just the database
docker compose up -d postgres

# Run the app on your machine
./mvnw spring-boot:run
```

Requires JDK 17. With SDKMAN:

```bash
sdk env install
sdk env
```