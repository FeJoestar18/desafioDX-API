# HTTP e Testes

## Testando endpoints

Use Swagger:
<http://localhost:8080/swagger-ui/index.html>

## Exemplo

GET /api/v1/escalacao/funcao-mais-recorrente

## 🧪 Testes

```bash
mvn clean test
```

## Ferramentas

- JUnit 5
- Mockito

## Boas práticas

- Mockar dependências
- Testar regras de negócio
- Evitar dependência de banco real

