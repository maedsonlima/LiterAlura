# Gutendex API Java App

Este projeto é uma aplicação Java com Spring Boot que consome dados da API pública [Gutendex](https://gutendex.com/) para registrar livros e autores em um banco de dados PostgreSQL. Ele permite buscas por título, listagem de autores e livros, além de consultas específicas como livros por idioma e autores vivos em determinado ano.

## Funcionalidades

- 🔍 Buscar livros por título diretamente da API Gutendex
- 📚 Registrar livros e autores no banco de dados
- 👤 Evitar duplicação de autores já registrados
- 🌐 Listar livros por idioma
- 🧓 Listar autores vivos em determinado ano
- 📖 Listar livros e autores registrados (em desenvolvimento)

## Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.5.0
- Spring Data JPA
- PostgreSQL
- API externa (Gutendex)
- Maven

## Como Executar

### Pré-requisitos

- Java 17+
- PostgreSQL em execução
- Maven instalado

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
