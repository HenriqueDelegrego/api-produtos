# API de Produtos

API REST para gestão de produtos, desenvolvida em Java com Spring Boot. A aplicação expõe endpoints para cadastro, listagem, consulta, edição e remoção de produtos.

## Visão geral

- Base URL local: `http://localhost:8080`
- Contexto da API: `/produtos`
- Formato de resposta: JSON
- CORS: habilitado globalmente para permitir consumo por front-end
- Persistência: MySQL
- Documentação interativa: Swagger/OpenAPI

## Requisitos

- Java 21
- Maven
- MySQL 8+
- Opcional: Docker e Docker Compose

## Configuração do banco

A aplicação usa a base `produtos` e a tabela `produto`.

Arquivo de configuração principal:

- `src/main/resources/application.properties`

Configurações esperadas:

```properties
spring.application.name=api-produtos

spring.datasource.url=jdbc:mysql://localhost:3306/produtos
spring.datasource.username=root
spring.datasource.password=

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

Schema da tabela:

```sql
CREATE DATABASE IF NOT EXISTS produtos;

USE produtos;

CREATE TABLE IF NOT EXISTS produto (
    id INT AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    descricao TEXT NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    url_imagem TEXT NOT NULL,
    PRIMARY KEY(id)
);
```

## Como executar localmente

### Opção 1: Maven

1. Crie o banco MySQL localmente com o nome `produtos`.
2. Ajuste as credenciais no arquivo `application.properties`.
3. Execute:

```bash
./mvnw spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

### Opção 2: Docker Compose

A partir da raiz do projeto:

```bash
docker compose up --build
```

A API ficará disponível em:

```text
http://localhost:8080
```

## Documentação interativa

Com a aplicação em execução, a documentação Swagger pode ser acessada em:

- `http://localhost:8080/swagger-ui/index.html`
- `http://localhost:8080/v3/api-docs`

## Endpoints

Todos os endpoints abaixo ficam sob o prefixo:

```text
/api-produtos
```

Na prática, a aplicação mapeia os endpoints com `/produtos`, então o prefixo base para consumo é:

```text
http://localhost:8080/produtos
```

---

### 1) Cadastrar produto

`POST /produtos`

Cria um novo produto.

#### Body

```json
{
  "nome": "Notebook Gamer",
  "descricao": "Notebook com processador i7, 16GB e SSD 512GB",
  "preco": 4899.90,
  "urlImagem": "https://images.example.com/notebook-gamer.jpg"
}
```

#### Validações

- `nome`: obrigatório, no máximo 50 caracteres
- `descricao`: obrigatório
- `preco`: obrigatório e não pode ser negativo
- `urlImagem`: obrigatório

#### Resposta de sucesso

Status: `201 Created`

```json
{
  "id": 1,
  "nome": "Notebook Gamer",
  "descricao": "Notebook com processador i7, 16GB e SSD 512GB",
  "preco": 4899.9,
  "urlImagem": "https://images.example.com/notebook-gamer.jpg"
}
```

#### Possíveis erros

- `400 Bad Request`: payload inválido ou campos vazios
- `500 Internal Server Error`: erro inesperado do servidor

---

### 2) Listar produtos

`GET /produtos`

Lista todos os produtos. Também aceita filtro por nome.

#### Consulta

```http
GET /produtos
```

```http
GET /produtos?nome=notebook
```

- `nome` (opcional): filtro parcial, case-insensitive

#### Resposta de sucesso

Status: `200 OK`

```json
[
  {
    "id": 1,
    "nome": "Notebook Gamer",
    "preco": 4899.9,
    "urlImagem": "https://images.example.com/notebook-gamer.jpg"
  },
  {
    "id": 2,
    "nome": "Mouse Sem Fio",
    "preco": 129.9,
    "urlImagem": "https://images.example.com/mouse.jpg"
  }
]
```

---

### 3) Buscar produto por ID

`GET /produtos/{id}`

Retorna um produto específico.

#### Exemplo

```http
GET /produtos/1
```

#### Resposta de sucesso

Status: `200 OK`

```json
{
  "id": 1,
  "nome": "Notebook Gamer",
  "descricao": "Notebook com processador i7, 16GB e SSD 512GB",
  "preco": 4899.9,
  "urlImagem": "https://images.example.com/notebook-gamer.jpg"
}
```

#### Possíveis erros

- `404 Not Found`: produto não encontrado

Mensagem:

```text
Produto não encontrado
```

---

### 4) Atualizar produto

`PUT /produtos/{id}`

Atualiza os dados de um produto existente.

#### Exemplo

```http
PUT /produtos/1
```

#### Body

```json
{
  "nome": "Notebook Gamer 2026",
  "descricao": "Notebook com processador i7, 16GB, SSD 1TB",
  "preco": 5299.90,
  "urlImagem": "https://images.example.com/notebook-gamer-2026.jpg"
}
```

#### Resposta de sucesso

Status: `200 OK`

```json
{
  "id": 1,
  "nome": "Notebook Gamer 2026",
  "descricao": "Notebook com processador i7, 16GB, SSD 1TB",
  "preco": 5299.9,
  "urlImagem": "https://images.example.com/notebook-gamer-2026.jpg"
}
```

#### Possíveis erros

- `400 Bad Request`: payload inválido
- `404 Not Found`: produto não encontrado

---

### 5) Deletar produto

`DELETE /produtos/{id}`

Remove um produto pelo ID.

#### Exemplo

```http
DELETE /produtos/1
```

#### Resposta de sucesso

Status: `204 No Content`

#### Possíveis erros

- `404 Not Found`: produto não encontrado

> Observação: o código atual não valida a existência antes do delete e pode devolver erro de banco se o produto não existir, dependendo do comportamento do repositório/BD.

---

## Estruturas de dados

### ProdutoRequest

Modelo enviado no cadastro e atualização.

```json
{
  "nome": "string",
  "descricao": "string",
  "preco": 0,
  "urlImagem": "string"
}
```

### ProdutoResponse

Modelo retornado em cadastro, consulta e atualização.

```json
{
  "id": 1,
  "nome": "string",
  "descricao": "string",
  "preco": 0,
  "urlImagem": "string"
}
```

### ProdutoListResponse

Modelo retornado na listagem.

```json
{
  "id": 1,
  "nome": "string",
  "preco": 0,
  "urlImagem": "string"
}
```

---

## Tratamento de erros

A API retorna mensagens simples em texto em caso de erro de validação ou recurso inexistente.

### Exemplo de erro de validação

```http
HTTP/1.1 400 Bad Request
```

```text
Nome é obrigatório
Descrição é obrigatória
Preço não pode ser negativo
```

### Exemplo de erro de produto não encontrado

```http
HTTP/1.1 404 Not Found
```

```text
Produto não encontrado
```

## Observações importantes para front-end

- O campo `preco` é um número decimal e deve ser tratado como valor monetário.
- A API aceita `double` em Java, então em JavaScript/TypeScript é comum usar `number`.
- O campo `urlImagem` deve receber uma URL válida de imagem.
- A listagem por nome usa busca parcial, sem diferenciação entre maiúsculas/minúsculas.
- A API está pronta para consumo por aplicações web com `fetch`, `axios` ou qualquer cliente HTTP.

## Estrutura do projeto

```text
api-produtos/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/delegrego/api_produtos/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── docs/
│   └── db/
│       └── schema.sql
├── compose.yaml
├── Dockerfile
├── pom.xml
├── mvnw
├── README.md
└── HELP.md
```

## Dicas de integração

- Para evitar problemas de CORS em desenvolvimento, garanta que o front-end esteja rodando em porta permitida ou tenha as configurações necessárias do navegador.
- Em ambiente de produção, use uma URL base configurada de forma adequada, por exemplo `https://api.seudominio.com/produtos`.
- Considere centralizar as chamadas da API em um serviço dedicado no front-end para padronizar erros e payloads.

## Resumo rápido

| Método | Endpoint | Descrição |
| --- | --- | --- |
| `POST` | `/produtos` | Cria um produto |
| `GET` | `/produtos` | Lista todos ou filtra por nome |
| `GET` | `/produtos/{id}` | Busca um produto pelo ID |
| `PUT` | `/produtos/{id}` | Atualiza um produto |
| `DELETE` | `/produtos/{id}` | Remove um produto |