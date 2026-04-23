# Design Patterns

## Service Layer

**O que é:** Centraliza as regras de negócio em classes específicas, servindo como uma ponte entre a camada de controle (Controllers) e a camada de acesso a dados (Repositories).

**Por que usar:** Evita que os Controllers fiquem "gordos" e cheios de lógica condicional. Ao delegar o processamento para os Services, o código fica mais fácil de ler, reutilizar e, principalmente, de ser testado isoladamente.

## Repository Pattern

**O que é:** Abstrai todo o acesso ao banco de dados utilizando a especificação do Spring Data JPA.

**Por que usar:** Oculta os detalhes de como os dados são persistidos ou consultados no SGBD (PostgreSQL ou H2). Isso significa que as regras de negócio não precisam saber escrever queries SQL; elas apenas chamam métodos semânticos como `findByClube` ou `save()`. Facilita imensamente a troca de tecnologias de banco de dados sem quebrar o sistema.

## DTO (Data Transfer Object)

**O que é:** Um padrão que consiste em criar classes simples usadas exclusivamente para transportar dados entre os processos (da requisição HTTP para a API e vice-versa).

**Por que usar:** Separa o modelo de domínio interno (as Entidades `@Entity` mapeadas no banco) do formato que a API expõe para o mundo exterior. Isso protege a aplicação de problemas de segurança (como *Mass Assignment*), evita loops infinitos em relacionamentos bidirecionais durante a serialização JSON e permite enviar/receber exatamente o que a tela do frontend precisa.

## Clean Architecture

**O que é:** Uma filosofia de design de software que divide o sistema em camadas concêntricas com uma regra estrita de dependência: as camadas externas dependem das internas, nunca o contrário.

**Por que usar:** Isola o núcleo da aplicação (regras de negócio) de frameworks, interfaces de usuário ou bancos de dados. Quando a tecnologia do banco ou o framework web mudar no futuro, as regras centrais permanecerão intactas.

## Benefícios

- **Código limpo e legível:** Responsabilidades bem definidas fazem com que novos desenvolvedores encontrem rapidamente onde alterar ou corrigir uma funcionalidade.
- **Escalabilidade:** Mudar um banco de dados ou trocar um Controller REST por um gRPC exige mexer apenas na camada de infraestrutura/web, sem reescrever a lógica inteira.
- **Testabilidade:** Como as regras de negócio (Services) não dependem diretamente de banco de dados real ou requests HTTP, é extremamente simples usar *mocks* e garantir que cada função do sistema funciona de forma independente e isolada.

