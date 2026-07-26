# Library
A full-stack library management system built with Spring Boot, React, and Docker.

## Desenvolvimento com Docker

O Docker Compose inicia o PostgreSQL e o backend Spring Boot:

```bash
docker compose up --build
```

O backend fica disponível em `http://localhost:8080` e o PostgreSQL em `localhost:5432`.


As credenciais padrão de desenvolvimento são `library` / `library` para o banco `library`.
Para alterá-las, copie `.env.example` para `.env` e edite os valores antes de iniciar os serviços.

```bash
cp .env.example .env
docker compose up --build
```

Para encerrar os serviços, use `docker compose down`. Para apagar também os dados locais do banco, use `docker compose down -v`.
