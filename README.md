# ViaCEP Consumer

ViaCEP Consumer é uma aplicação Spring Boot que consulta e armazena informações de endereço. O usuário fornece um CEP, e a aplicação verifica se os dados do endereço já estão cadastrados no banco de dados. Se não estiverem, a aplicação consulta a API ViaCEP para obter as informações do endereço e as armazena no banco de dados.

## Funcionalidades

- Receber um CEP do usuário
- Verificar se o CEP já está cadastrado no banco de dados
- Consultar a API ViaCEP para obter os dados do endereço caso o CEP não esteja cadastrado
- Armazenar os dados do endereço no banco de dados

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- API ViaCEP

## Requisitos

- JDK 17
- Maven 3.6 ou superior
- PostgreSQL

## Configuração do Projeto

### Clonar o Repositório

```bash
git clone https://github.com/gabrafo/viacep-consumer.git
cd viacep-consumer
```

### Configurar o Banco de Dados

1. Crie um banco de dados no PostgreSQL (ou MySQL).

2. Atualize as configurações no arquivo application.properties:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3. Compilar e Rodar a Aplicação
```bash
mvn clean install
mvn spring-boot:run
```

## Uso

A aplicação expõe uma API REST que permite ao usuário fornecer um CEP e obter as informações do endereço.

### Endpoints

- GET /endereco/{cep}: Consulta o endereço pelo CEP. Se o endereço não estiver cadastrado, consulta a API ViaCEP e armazena o resultado no banco de dados.

#### Exemplo de Requisição

```http
GET /endereco/01001000
```

#### Exemplo de Resposta

```json
{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP"
}
```
