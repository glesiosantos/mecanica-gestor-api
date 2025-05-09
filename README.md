<div align="center">
    <img src="./src/main/resources/static/autorevise_1024x1024.png" width="250px"/>
</div>

## AUTOREVISE- API de Gestão para Oficinas Mecânicas (Carros, Motos e Bicicletas)

AUTOREVISE é uma API desenvolvida para gerenciar múltiplas oficinas mecânicas, abrangendo serviços para carros, motos e bicicletas. O sistema oferece funcionalidades completas para administração de pedidos, produtos, estoque, fluxo de caixa e gerenciamento de clientes e seus veículos.

## 🚀 Funcionalidades

    >> LEGENDA 🔵 CONCLUÍDO | 🔴 EM DESENVOLVIMENTO


- 🔵 **Autenticação e Autorização**: Autenticação de colaboradores de acordo a unidade.  
- 🔵 **Gerenciamento de Pedidos**: Cadastro, atualização e acompanhamento do status dos serviços prestados.  
- 🔵 **Controle de Produtos**: Administração de peças, acessórios e insumos utilizados nas oficinas.  
- 🔵 **Controle de Fornecedores**: Administração contatos dos fornecedores de seus produtos.  
- 🔵 **Cadastro de Clientes e Veículos**: Registro detalhado de clientes, incluindo dados de contato e informações sobre seus veículos.  
- 🔵 **Suporte a Múltiplas Oficinas**: Cada oficina pode operar de forma independente dentro da mesma plataforma.
- 🔴 **Controle de estoque**: Debitar od estoque a quantidade realizada nas vendas e registrar quantidades adquiridas pelo fornecedor.
- 🔴 **Controle de contas à pagar/receber**: Registrar receitas/despesa das oficinas

## 🛠 Tecnologias Utilizadas

- **Backend**: [Java](https://docs.oracle.com/en/java/) com [Spring Boot](https://spring.io/projects/spring-boot)  
- **Banco de Dados**: [PostgreSQL](https://www.postgresql.org/)  
- **Autenticação**: JWT com [AUTH0] (https://auth0.com/docs/secure/tokens/json-web-tokens)  
- **Documentação da API**: Swagger ou Postman  

## 📌 Como Executar o Projeto

### Pré-requisitos


- [Java 21](https://docs.oracle.com/en/java/) ou superior instalado;  
- Gerenciador de pacotes [maven](https://maven.apache.org/);  
- Banco de dados configurado (PostgreSQL de preferência) ou Docker com PostgreSQL;
- Caso use o Docker, existe um docker compose pré-configurado para ser executado;
- Mensageria [RabittMQ] (https://www.rabbitmq.com/);

### Instalação e Configuração

```bash
# Clone o repositório
git clone https://github.com/glesiosantos/mecanica-gestor-api.git

# Acesse a pasta do projeto
cd mecanica-gestor-api

# Instale as dependências
mvn install

# Configurar as váriaveis de ambiente 
    POSTGRESQL_HOST=
    POSTGRESQL_USER=
    POSTGRESQL_PASS=
    RABBITMQ_HOST=
    RABBITMQ_PORT=
    RABBITMQ_USER=
    RABBITMQ_PASS=
    API_CORS_ALLOWED_ORIGINS=
    API_SECRET=

# Inicie a aplicação
mvn spring-boot:run
```

## 📖 Documentação da API

A documentação da API pode ser acessada via Swagger na rota `/api-docs` após iniciar o projeto.

## 📜 Licença

Este projeto está licenciado sob a [MIT License](./LICENSE).

## 🤝 Contribuição

Contribuições são bem-vindas! Siga as diretrizes do projeto e envie seu PR.
