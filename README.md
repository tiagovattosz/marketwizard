# Marketwizard 🧙‍♂️
> Este projeto foi desenvolvido como parte das matérias de Engenharia de Software e Banco de Dados. Trata-se de um gerenciador de estoque voltado para vendedores de marketplaces como Mercado Livre e Amazon.

> obs: como o projeto foi feito usando um servidor remoto com ip público, removemos as informações sensíveis e criamos outro repositório apenas com a ultima versão do projeto.

## ⚙️ Funcionalidades
- Relatórios de compra e venda
- Análise de produtos e categorias mais vendidas
- Gestão de produtos em falta e compras não recebidas
- Cotações de preço
- Documentação completa disponível no Swagger

# 🛠️ Tecnologias Utilizadas
## Api - Backend
- **Spring Boot**
- **JPA**
- **Hibernate** 
- **Lombok** 
- **Spring Security & JWT**
- **PostgreSQL** 
- **Swagger** 
- **GitHub** 
- **IntelliJ IDEA Premium:** Desenvolvimento remoto via SSH.
  
## 💻 Pré-requisitos

Antes de começar, verifique se você instalou os seguintes requisitos:

- Java 17
- PostgreSQL 16
- Dependências Maven

## 🚀 Instalando

Para instalar o projeto, siga estas etapas:

1. Clone o projeto
```
git clone https://github.com/tiagovattos/marketwizard
```
2. Criar um banco "marketwizard"
```
CREATE DATABASE marketwizard;
```
4. Configure o banco de dados no "application.properties" do backend
```
spring.datasource.url=jdbc:postgresql://localhost:5432/marketwizard
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

## ☕ Usando

Todas as funcionalidades da API estão documentadas e disponíveis no Swagger. Para acessar o Swagger, siga estas etapas:

1. Inicie a aplicação.
2. Abra seu navegador e vá para: `http://localhost:8080/swagger-ui/index.html#/`

Lá você encontrará a documentação completa dos endpoints disponíveis, exemplos de requisições, e muito mais.

## 🤝 Colaboradores

Agradecemos às seguintes pessoas que contribuíram para este projeto:

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/tiagovattos" title="link github tiagovattos">
        <img src="https://avatars.githubusercontent.com/u/103440481?s=400&u=588ec498f604cbdd2dc3a7b431289082f161362d&v=4" width="100px;" alt="Foto tiagovattos github"/><br>
        <sub>
          <b>Tiago Vattos- backend</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/rafaaconsoli" title="link github rafaaconsoli">
        <img src="https://avatars.githubusercontent.com/u/96840958?v=4" width="100px;" alt="Foto rafaaconsoli github"/><br>
        <sub>
          <b>Rafael Consoli - frontend</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

## 📞 Contato

Para dúvidas ou mais informações, entre em contato:

- Email: [tiagovattosz@gmail.com](mailto:tiagovattosz@gmail.com)
- LinkedIn: [Tiago Vattos Zamboni](https://www.linkedin.com/in/tiago-vattos-zamboni-680952232/)
