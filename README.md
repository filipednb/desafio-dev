# Financial Transactions API

## Aplicação responsável por salvar lista de transações através de upload de arquivos CNAB

### Técnologias e frameworks utilizados:
- Java 13
- Spring 
  - Spring Data JPA
  - Spring Web
  - SpringDoc OpenAPI (Swagger)
- MySQL 8
- Flyway (Versionamento do Banco de Dados)
- RestAssured (Testes de Integração)
- JUnit 5 (Testes unitários)
- Docker
- Node v14.18.1
- NPM v6.14.15
- React
- Redux
- Axios
- Webpack

Existem 2 componentes principais na aplicação: O back-end e o front-end. Ambos são construidos à partir do código fonte existente nas pastas `/src/main/java` e `/src/main/webapp` respectivamente. Eles são construidos através da execução da task `build` do gradle e iniciados à partir da task `bootRun`. O back-end tem o papel de servir a API e ao mesmo tempo a página (SPA com React) que contém o formulário de importação dos arquivos CNAB.

Foi pensado que a estrutura do banco de dados deveria ser versionada, portando criaremos 2 usuários, 
um para a aplicação, com permissão apenas de leitura e escrita e outro para o Flyway com
mais privilégios, podendo criar, apagar e alterar a estrutura das tabelas.  

## Preparando o ambiente:

### Java
Para preparar o ambiente corretamente eu utilizo o [Jabba](https://github.com/shyiko/jabba) para gerenciar as versões da JDK.

`jabba install zulu@1.13.0-0`

`jabba use zulu@1.13.0-0`

### Banco de dados:

Criar container do MySQL à partir da imagem padrão do docker hub:

`docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:8.0.27`

Executar/Conectar no bash do container MySQL:

`docker exec -it mysql mysql -uroot -p`

Após a conexão utilizando a senha `root`, estando dentro do bash do mysql (`mysql >`), executar os comandos à seguir:

```
CREATE DATABASE FINANCIAL_TRANSACTIONS;

CREATE USER 'ft_user'@'%' IDENTIFIED BY 'ft_pwd';
CREATE USER 'migration_user'@'%' IDENTIFIED BY 'migration_pwd';

GRANT ALL ON FINANCIAL_TRANSACTIONS.* TO 'migration_user'@'%' WITH GRANT OPTION;
GRANT INSERT, UPDATE, DELETE, SELECT ON FINANCIAL_TRANSACTIONS.* TO 'ft_user'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
```
Saia do mysql digitando `exit`:

`mysql> exit` 


### Rodando a API:

Para criar a estrutura inicial das tabelas, no terminal, na raiz do projeto, executar:
`./gradlew flywayMigrate`

Para rodar a aplicação:

`./gradlew bootRun`

Após isso abrir o navegador no endereço: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Ou para visualizar a página do front: [http://localhost:8080](http://localhost:8080)

### Rodando o Front-end

Instalar as dependencias:

`npm install`

Iniciar o ambiente de desenvolvimento

`npm start:dev`

A app fica acessível através do link [http://localhost:3000](http://localhost:3000) com hot-reload, onde ao salvar o novo código fonte o webpack rebuilda a app e atualiza o navegador.


### Features da APP
- Upload de arquivos CNAB, via [API](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/transaction-resource/uploadTransactionsFile) ou via [Interface](http://localhost:8080/)
- [Resumo de transações](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/transaction-resource/getTransactionsResumeByStoreName) por Loja

### TODOs
- Conteinerização
- Interface para visualização do resumo por loja
- Implementar componente de `Loading` na interface
- Implementar testes para o front-end
- Incluir mais asserts nos testes da API
- Implementar idempotência ou devolver alguma exception ao se deparar com duplicidade de registro
  Para isso pensei em persistir algum tipo de hash da transação e tratar da maneira que o cliente preferir.
