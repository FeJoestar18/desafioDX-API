# Regras de Negócio e Processos

Este documento detalha as regras de negócio implementadas no sistema e como funcionam os processos de criação das entidades principais (Cargos, Integrantes e Times). A aplicação garante a integridade dos dados através de validações estritas na camada de Domínio e de Serviço.

## 1. Processo de Criação de Entidades

A criação de registros no sistema deve seguir uma ordem hierárquica devido aos relacionamentos do banco de dados: `Cargo -> Integrante -> Time`.

### 1.1 Criar um Cargo
O Cargo define a função que um integrante exerce (ex: Atacante, Goleiro, Suporte).
- **Regra de Negócio:** O nome do cargo **deve ser único** no sistema.
- **Processo:**
  1. A API recebe o nome do cargo desejado.
  2. O `CadastroService` verifica no banco de dados (`cargoRepository.findByNomeIgnoreCase`) se o nome já existe (ignorando letras maiúsculas e minúsculas).
  3. Se já existir, lança uma `IllegalArgumentException` ("Já existe um cargo com o nome X").
  4. Caso contrário, o cargo é salvo e um ID é gerado.

### 1.2 Criar um Integrante
Um integrante é uma pessoa que pode ser escalada para um ou mais times.
- **Regra de Negócio:** Todo integrante deve estar obrigatoriamente associado a um `Cargo` válido.
- **Processo:**
  1. A API recebe o nome do integrante e o `cargoId`.
  2. O serviço busca o Cargo no banco de dados através do ID fornecido.
  3. Se o cargo não for encontrado, a criação é abortada com erro.
  4. Se encontrado, a entidade `Integrante` é instanciada com a relação `@ManyToOne` com o `Cargo` e salva no banco.

### 1.3 Criar um Time
Um Time representa a escalação de um Clube em uma data específica.
- **Regras de Negócio:**
  - **Unicidade de Nome:** Não podem existir dois times com o mesmo nome de clube no sistema. Essa validação é feita pelo `TimeValidator`.
  - **Integrantes Únicos:** Um time não pode conter o mesmo integrante listado mais de uma vez. O `TimeValidator` checa se há IDs duplicados no payload da requisição.
  - **Existência de Integrantes:** Todos os IDs de integrantes fornecidos devem existir no banco de dados.
- **Processo:**
  1. A API recebe o nome do clube, a data do time e uma lista de `integranteIds`.
  2. O validador garante que o nome do clube é único e que a lista de IDs não contém duplicatas.
  3. O `Time` é salvo no banco (inicialmente vazio).
  4. O sistema busca todos os integrantes pelos IDs informados. Se algum ID for inválido, a operação falha e o *rollback* transacional ocorre.
  5. O sistema cria as entidades de ligação `ComposicaoTime` associando cada integrante encontrado ao Time recém-criado.
  6. A composição é salva em lote no banco.

---

## 2. Consultas e Análises (ApiService)

O sistema possui uma série de regras complexas para analisar o histórico de escalações. Todas essas regras utilizam o poder da API de **Java Streams** para processamento declarativo e em memória das entidades mapeadas.

### 2.1 Time da Data
- Retorna o primeiro time encontrado que foi escalado exatamente na data fornecida.
- Caso não exista time para a data, a API retorna uma mensagem estruturada com HTTP 404 (Not Found).

### 2.2 Integrante Mais Usado
- Avalia todos os times formados dentro de um período (data de início e data de fim).
- Agrupa todas as composições de times desse período por `Integrante`.
- Conta a frequência de cada integrante e retorna aquele que participou do maior número de times.

### 2.3 Time Mais Recorrente
- Identifica qual combinação exata de integrantes (escalação completa) se repetiu mais vezes no período.
- Retorna a lista de nomes dos integrantes que compõem essa escalação vitoriosa.

### 2.4 Função (Cargo) Mais Recorrente
- Avalia todas as escalações do período.
- Conta quantas vezes cada função (`Cargo.getNome()`) apareceu no total.
- Retorna o nome do cargo que teve maior participação somando todos os times.

### 2.5 Contagens Agrupadas
- **Contagem de Clubes:** Retorna um dicionário (`Map`) contendo o nome de cada clube e a quantidade de times que ele montou no período.
- **Contagem por Função:** Retorna um dicionário contendo o nome de cada cargo e o total de vezes que esse cargo foi escalado no período.