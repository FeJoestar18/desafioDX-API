# Como Rodar

## 1. Pré-requisitos
- Java 17+
- Maven
- Docker (opcional)
- PostgreSQL local (opcional, caso não queira usar Docker ou H2)

---

## 2. Configurando o ambiente (`.env`)

Crie um arquivo `.env` na raiz do projeto com as variáveis abaixo. Preste atenção às portas: normalmente o **PostgreSQL local usa a porta 5432**, enquanto a versão rodando via **Docker expõe a porta 5433** para evitar conflitos.

### Exemplo de `.env` para PostgreSQL local
```env
DB_HOST=localhost
DB_PORT=5432
DB_URL=jdbc:postgresql://localhost:5432/duxus
DB_USERNAME=postgres
DB_PASSWORD=sua_senha
SPRING_PROFILES_ACTIVE=postgres

# H2 profile (Banco em memória)
H2_URL=jdbc:h2:mem:duxus;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
H2_DRIVER=org.h2.Driver
H2_USERNAME=sa
H2_PASSWORD=
H2_DIALECT=org.hibernate.dialect.H2Dialect
H2_DDL_AUTO=create-drop
H2_SHOW_SQL=true

# Test profile
TEST_DB_URL=jdbc:h2:mem:duxus-test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
TEST_DB_DRIVER=org.h2.Driver
TEST_DB_USERNAME=sa
TEST_DB_PASSWORD=
TEST_DB_DIALECT=org.hibernate.dialect.H2Dialect
TEST_DB_DDL_AUTO=create-drop
TEST_SHOW_SQL=false
```

### Exemplo de `.env` para Docker Compose
```env
DB_HOST=localhost
DB_PORT=5433
DB_URL=jdbc:postgresql://localhost:5433/duxus
DB_USERNAME=postgres
DB_PASSWORD=sua_senha
SPRING_PROFILES_ACTIVE=docker

# H2 profile (Banco em memória)
H2_URL=jdbc:h2:mem:duxus;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
H2_DRIVER=org.h2.Driver
H2_USERNAME=sa
H2_PASSWORD=
H2_DIALECT=org.hibernate.dialect.H2Dialect
H2_DDL_AUTO=create-drop
H2_SHOW_SQL=true
```

> **Importante:** quando o projeto roda no Docker, o host mapeado expõe o PostgreSQL em `localhost:5433` para evitar conflito com um PostgreSQL local rodando em `5432`. O container da aplicação em Docker ainda acessa o banco pelo serviço interno `postgres:5432`.

---

## 3. Troubleshooting de PostgreSQL local

Se você usar `SPRING_PROFILES_ACTIVE=postgres` e receber um erro como:
- `Connection refused`
- `Connection to localhost:5433 refused`
- `UnknownHostException: postgres`

então provavelmente o banco PostgreSQL não está escutando no host/porta configurados.

**Verifique:**
- `DB_HOST` deve ser `localhost` ou o host correto do PostgreSQL local.
- `DB_PORT` deve ser a porta do PostgreSQL local.
- `DB_URL` deve refletir `DB_HOST` e `DB_PORT`, por exemplo:
  ```env
  DB_URL=jdbc:postgresql://localhost:5432/duxus
  ```
- `SPRING_PROFILES_ACTIVE=postgres` para usar `application-postgres.properties`.

Se estiver usando PostgreSQL local padrão na porta `5432`, atualize no `.env`:
```env
DB_HOST=localhost
DB_PORT=5432
DB_URL=jdbc:postgresql://localhost:5432/duxus
SPRING_PROFILES_ACTIVE=postgres
```

Se a conexão na porta correta cair com erro de senha (`FATAL: autenticação do tipo senha falhou`), o Postgres está ativo, mas a senha de `postgres` está incorreta. Nesse caso, redefina a senha do usuário PostgreSQL local antes de rodar a aplicação:

```bash
sudo -u postgres psql
ALTER USER postgres WITH PASSWORD 'sua_senha';
\q
```
Depois atualize `DB_PASSWORD` no `.env` com a senha correta.

---

## 4. Perfis suportados

- `h2`: usa banco H2 em memória.
- `postgres`: usa PostgreSQL local configurado via `DB_URL` (normalmente na porta 5432).
- `docker`: usa PostgreSQL dentro do Docker Compose (exposto localmente na porta 5433).

---

## 5. Como executar

### Opção A: H2 local
```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=h2
```
Ou altere no `.env`:
```env
SPRING_PROFILES_ACTIVE=h2
```

### Opção B: PostgreSQL local
```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=postgres
```
Ou altere no `.env`:
```env
SPRING_PROFILES_ACTIVE=postgres
```

### Opção C: Docker Compose com PostgreSQL
```bash
docker-compose up -d --build
```

---

## 6. Detalhes importantes

- O perfil `h2` é ideal para desenvolvimento rápido, pois não exige banco externo.
- O perfil `postgres` conecta a aplicação ao PostgreSQL local definido em `DB_URL`.
- O perfil `docker` conecta a aplicação ao serviço `postgres` definido em `docker-compose.yml`.
- Se nenhuma variável `SPRING_PROFILES_ACTIVE` estiver definida, o perfil padrão é `h2`.