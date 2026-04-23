# Duxus Desafio - API de Gerenciamento de Escalação de Times

API REST desenvolvida com Spring Boot para gerenciamento de times, integrantes e análises de escalações.

## Visão Geral

Este projeto resolve um desafio técnico com foco em regras de negócio complexas, arquitetura limpa e boas práticas.

## Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA + Hibernate
- PostgreSQL
- H2 Database
- Docker
- Swagger (OpenAPI)
- JUnit 5 + Mockito

## &#x20;Documentação

A documentação foi separada para melhor organização:

- Arquitetura e Design: [Arquitetura](documentacao/arquitetura.md)
- Como rodar o projeto: [Como Rodar](documentacao/run.md)
- Design Patterns: [Design Patterns](documentacao/patterns.md)
- Endpoints, HTTP e Testes: [Endpoints](documentacao/http.md)
- Frontend: [Sobre o Front-End](documentacao/frontend.md)

## &#x20;Acesso rápido

- Swagger UI: <http://localhost:8080/swagger-ui/index.html>
- API Docs: <http://localhost:8080/v3/api-docs>

## Testes

```bash
mvn clean test
```

## Estrutura do Projeto

- WebApi
- Application
- Domain
- Infrastructure

## Licença

Uso educacional / desafio técnico
