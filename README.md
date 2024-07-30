![Capa do projeto](./assets/images/literalura-banner.png)
# LiterAlura

![GitHub License](https://img.shields.io/github/license/thatimoreira/literalura) 
![GitHub Issues](https://img.shields.io/github/issues/thatimoreira/literalura) 
![GitHub Forks](https://img.shields.io/github/forks/thatimoreira/literalura) 
![GitHub Stars](https://img.shields.io/github/stars/thatimoreira/literalura)
<br>
<br>

## Índice
  * [Descrição do Projeto](#descrição-do-projeto)
  * [Status do Projeto](#status-do-projeto)
  * [Funcionalidades](#funcionalidades)
  * [Demonstração da Aplicação](#demonstração-da-aplicação)
    * [Rotas para teste](#rotas-para-teste)
  * [Acesso ao Projeto](#acesso-ao-projeto)
    * [Pré-requisitos](#pré-requisitos)
    * [Instalação](#instalação)
  * [Tecnologias Utilizadas](#tecnologias-utilizadas)
  * [Desenvolvido por](#desenvolvido-por)
  * [Licença](#licença)
<br>

## Descrição do Projeto

Literalura é uma plataforma para catalogar livros desenvolvida durante a trilha Back-end Java do programa One Next Education (Oracle e Alura). Utiliza Java com Spring Boot, integrando banco de dados e a API Gutendex. Seu objetivo é facilitar a busca de livros e exibir estatísticas sobre os títulos cadastrados.

LiterAlura é uma ótima oportunidade para aplicar conceitos importantes de persistência de dados, integração com APIs externas, recursos avançados do Spring Framework, além de boas práticas de desenvolvimento e documentação de software.<br>
<br>

## Status do Projeto

As funcionalidades básicas do projeto foram implementadas e melhorias poderão ser feitas futuramente.
<br>
<br>

## Funcionalidades

- **Menu inicial**<br>
<img src="./assets/images/menu-inicial.png" alt="Tela de menu inicial do LiterAlura" width="400"/>
<br>

- **Buscar livros por título**: Permite buscar livros pelo título na API Gutendex
<img src="./assets/images/buscar-livros-por-titulo.png" alt="Tela de busca de livros por título" width="700"/>
<br>

- **Listar livros por idioma**: Lista todos os livros disponíveis no idioma escolhido
<img src="./assets/images/buscar-livros-por-idioma.png" alt="Tela de busca de livros por idioma" width="700"/>
<br>

- **Contar livros por idioma**: Exibe a quantidade de livros disponíveis em um idioma específico
<img src="./assets/images/listar-total-de-livros-por-idioma.png" alt="Tela de consulta do total de livros por idioma" width="300"/>
<br>

- **Listar todos os livros pesquisados**: Mostra todos os livros que foram buscados e salvos no banco de dados
<img src="./assets/images/listar-todos-os-livros-pesquisados.png" alt="Tela de consulta de todos os livros pesquisados" width="700"/>
<br>

- **Listar todos os autores pesquisados**: Mostra todos os autores que foram buscados e salvos no banco de dados
<img src="./assets/images/listar-todos-os-autores-pesquisados.png" alt="Tela de consulta de todos os autores pesquisados" width="700"/>
<br>

- **Listar autores vivos em um determinado ano**: Lista todos os autores que estavam vivos no ano especificado
<img src="./assets/images/listar-autores-vivos-em-um-determinado-ano.png" alt="Tela de consulta de lista de autores vivos em determinado ano" width="700"/>
  <br>
  <br>

## Demonstração da Aplicação

Para visualizar as funcionalidades, você pode clonar e testar o LiterAlura localmente, na IDE da sua preferência, ou utilizar as rotas disponibilizadas para testes com ferramentas como Insomnia ou Postman.

#### Rotas para teste

[incluir mais rotas]

* Buscar livros por título:```sh``````GET /books?search=titulo```

* Listar livros por idioma: GET /books?languages=pt
  
* Contar livros por idioma: GET /books/countByLanguage?language=pt
 <br> 

## Acesso ao Projeto

#### Pré-requisitos

* Java 17
* PostgreSQL
* Maven

#### Instalação

1. Clone o repositório:git clone https://github.com/thatimoreira/literalura.git
2. Entre no diretório do projetocd literalura
3. Configure o banco de dados PostgreSQL com as variáveis de ambiente:export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/literaluraexport SPRING_DATASOURCE_USERNAME=seu-usuarioexport SPRING_DATASOURCE_PASSWORD=sua-senha
4. Rode a aplicação:./mvnw spring-boot:run
<br>

## Tecnologias Utilizadas

* Java 17
* Spring Boot 3.3.2
* PostgreSQL 16
* Hibernate
* Jackson
* Maven
* IDE: Intellij IDEA
<br>

## Desenvolvido por

Thatiana Moreira
<br>
<br>

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](./LICENSE) para mais detalhes.
