API de Filmes e Séries
Esta API permite gerenciar um catálogo de filmes e séries, bem como a criação e autenticação de usuários. 
Abaixo estão os principais componentes e tecnologias utilizadas no projeto:

Tecnologias Utilizadas

Spring Boot
Spring Boot Starter Data JPA: Utilizado para persistência de dados com JPA.
Spring Boot Starter Data JDBC: Utilizado para operações de banco de dados com JDBC.
Spring Boot Starter Web: Utilizado para criação de endpoints RESTful.
Spring Boot DevTools: Facilita o desenvolvimento com reinicialização automática.
Spring Boot Starter Security: Fornece autenticação e autorização para a aplicação.
Spring Boot Starter Validation: Utilizado para validação de dados.


Funcionalidades

Cadastro de Usuários: Endpoint para cadastrar novos usuários.
Autenticação JWT: Sistema de login utilizando CPF e senha, gerando tokens JWT.
Gestão de Filmes e Séries: Endpoints para adicionar, atualizar, listar e excluir filmes e séries.
Validações Personalizadas 
Handlers de Exceção - GlobalExceptionHandler: Classe centralizada para manipulação de exceções, 
proporcionando respostas consistentes para diferentes tipos de erros. 
Validações Personalizadas
Swagger UI: Interface interativa para testar e visualizar os endpoints da API.


Dependências

SpringDoc OpenAPI: Gera automaticamente a documentação da API.
Spring Cloud Starter OpenFeign: Para realizar chamadas a outras APIs REST.
Hibernate Validator: Utilizado para validação de dados.
MySQL Connector: Driver JDBC para conexão com o banco de dados MySQL.
ModelMapper: Facilita a conversão de objetos DTO.
JavaFamily RestTemplate Starter: Simplifica o uso do RestTemplate.
JJWT (Java JWT): Utilizado para criação e validação de tokens JWT.
TheMovieDbAPI: Biblioteca para interagir com a API do The Movie Database.