<p align="center">
  <h1 align="center">🎉 Eventify API</h1>
  <p align="center">
    <strong>API RESTful para gerenciamento de eventos — construída com Clean Architecture e Java 17.</strong>
  </p>
  <p align="center">
    <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17" />
    <img src="https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot 3.5" />
    <img src="https://img.shields.io/badge/PostgreSQL-15-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />
    <img src="https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker" />
    <img src="https://img.shields.io/badge/Flyway-Migrations-CC0200?style=for-the-badge&logo=flyway&logoColor=white" alt="Flyway" />
  </p>
</p>

---

## 📋 Sumário

- [Sobre o Projeto](#-sobre-o-projeto)
- [Arquitetura](#-arquitetura)
- [Stack Tecnológica](#-stack-tecnológica)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação e Execução](#-instalação-e-execução)
- [Banco de Dados](#-banco-de-dados)
- [Tipos de Evento](#-tipos-de-evento)
- [Roadmap](#-roadmap)
- [Licença](#-licença)

---

## 💡 Sobre o Projeto

O **Eventify API** é uma API REST desenvolvida em **Java 17** com **Spring Boot 3.5** cujo propósito é fornecer uma solução back-end robusta e escalável para o **gerenciamento completo de eventos**.

A aplicação permite criar, consultar, listar e remover eventos de diferentes categorias — como **shows musicais, palestras, seminários, workshops, cultos e encontros de networking** — armazenando informações essenciais como nome, descrição, local, datas de início e término, capacidade e tipo do evento.

### Principais objetivos

| Objetivo | Descrição |
|----------|-----------|
| **Organização** | Centralizar o cadastro e a gestão de eventos em uma única API. |
| **Escalabilidade** | Arquitetura desacoplada que permite evoluir cada camada de forma independente. |
| **Manutenibilidade** | Código limpo, separação de responsabilidades e baixo acoplamento entre domínio e infraestrutura. |
| **Portabilidade** | Banco de dados provisionado via Docker Compose — basta um comando para subir o ambiente. |

---

## 🏛 Arquitetura

O projeto segue os princípios da **Clean Architecture (Arquitetura Limpa)** combinados com o padrão **Ports & Adapters (Hexagonal)**, garantindo que as regras de negócio não dependam de frameworks, bancos de dados ou qualquer detalhe de infraestrutura.

```
┌─────────────────────────────────────────────────────┐
│                   CORE (Domínio)                    │
│                                                     │
│  ┌──────────┐  ┌───────────┐  ┌──────────────────┐  │
│  │ Entities │  │ Use Cases │  │ Ports (interfaces)│  │
│  │  Event   │  │CreateEvent│  │EventRepositoryPort│  │
│  │EventType │  │           │  │                   │  │
│  └──────────┘  └───────────┘  └──────────────────┘  │
│                                                     │
├─────────────────────────────────────────────────────┤
│               INFRA (Adaptadores)                   │
│                                                     │
│  ┌────────────┐  ┌──────────┐  ┌─────────────────┐  │
│  │ JPA Entity │  │  Mapper  │  │    Gateway       │  │
│  │EventEntity │  │EventEntity│ │EventRepository   │  │
│  │            │  │  Mapper  │  │   Gateway        │  │
│  └────────────┘  └──────────┘  └─────────────────┘  │
│                                                     │
│  ┌─────────────────────────────────────────────┐    │
│  │         JpaEventRepository (Spring Data)    │    │
│  └─────────────────────────────────────────────┘    │
│                                                     │
└─────────────────────────────────────────────────────┘
```

**Fluxo de dados:**

1. A requisição chega pela camada de infraestrutura (futuramente via Controller REST).
2. O **Use Case** orquestra a lógica de negócio usando entidades do domínio.
3. A comunicação com o banco de dados é feita pelo **Gateway**, que implementa a **Port** definida no core.
4. O **Mapper** converte entre a entidade de domínio (`Event`) e a entidade JPA (`EventEntity`).

---

## 🛠 Stack Tecnológica

| Tecnologia | Versão | Finalidade |
|------------|--------|------------|
| **Java** | 17 | Linguagem de programação |
| **Spring Boot** | 3.5.16 | Framework para criação da API |
| **Spring Data JPA** | — | Abstração de acesso a dados |
| **PostgreSQL** | 15 (Alpine) | Banco de dados relacional |
| **Flyway** | — | Versionamento e migração de schema |
| **Lombok** | — | Redução de boilerplate (getters, setters, construtores) |
| **Docker Compose** | 3.8 | Orquestração do container do banco de dados |
| **Maven** | — | Gerenciamento de dependências e build |

---

## 📁 Estrutura do Projeto

```
eventify-API/
├── docker-compose.yml                          # Container PostgreSQL
├── pom.xml                                     # Dependências Maven
├── mvnw / mvnw.cmd                             # Maven Wrapper
│
└── src/
    └── main/
        ├── java/com/example/eventify_API/
        │   │
        │   ├── EventifyApiApplication.java     # Classe de inicialização (Spring Boot)
        │   │
        │   ├── core/                           # 🧠 DOMÍNIO (regras de negócio)
        │   │   ├── entities/
        │   │   │   └── Event.java              # Record representando um Evento
        │   │   ├── Enuns/
        │   │   │   └── EventType.java          # Enum com os tipos de evento
        │   │   ├── ports/
        │   │   │   └── EventRepositoryPort.java# Interface (porta) do repositório
        │   │   └── useCases/
        │   │       ├── CreateEventCase.java     # Contrato do caso de uso
        │   │       └── CreateEventIpml.java     # Implementação do caso de uso
        │   │
        │   └── infra/                          # ⚙️ INFRAESTRUTURA (adaptadores)
        │       ├── dataBase/
        │       │   └── EventEntity.java         # Entidade JPA mapeada para o banco
        │       └── persistence/
        │           ├── gateway/
        │           │   └── EventRepositoryGateway.java  # Implementação da port
        │           ├── mapper/
        │           │   └── EventEntityMapper.java       # Conversão domínio ↔ JPA
        │           └── repository/
        │               └── JpaEventRepository.java      # Interface Spring Data JPA
        │
        └── resources/
            ├── application.yml                 # Configuração da aplicação
            └── db/migration/
                └── V1__create_table_eventos.sql # Migração inicial do Flyway
```

---

## ✅ Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **Java 17+** — [Download](https://adoptium.net/)
- **Maven 3.8+** (ou utilize o Maven Wrapper incluso)
- **Docker** e **Docker Compose** — [Download](https://www.docker.com/)

---

## 🚀 Instalação e Execução

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/eventify-API.git
cd eventify-API
```

### 2. Suba o banco de dados com Docker

```bash
docker compose up -d
```

Isso iniciará um container PostgreSQL 15 com as seguintes credenciais padrão:

| Variável | Valor |
|----------|-------|
| `POSTGRES_DB` | `eventify_db` |
| `POSTGRES_USER` | `postgres` |
| `POSTGRES_PASSWORD` | `postgres` |
| Porta | `5432` |

### 3. Execute a aplicação

```bash
# Com Maven Wrapper (recomendado)
./mvnw spring-boot:run

# Ou, se o Maven estiver instalado globalmente
mvn spring-boot:run
```

A API estará disponível em: **`http://localhost:8080`**

> **Nota:** O Flyway executará automaticamente a migração `V1__create_table_eventos.sql` na primeira inicialização, criando a tabela `Eventos` no banco.

---

## 🗄 Banco de Dados

### Schema — Tabela `Eventos`

| Coluna | Tipo | Restrição | Descrição |
|--------|------|-----------|-----------|
| `id` | `BIGSERIAL` | `PRIMARY KEY` | Identificador único auto-incrementado |
| `name` | `VARCHAR(255)` | `NOT NULL` | Nome do evento |
| `description` | `TEXT` | — | Descrição detalhada |
| `local` | `VARCHAR(255)` | — | Local de realização |
| `data_inicio` | `TIMESTAMP` | — | Data e hora de início |
| `data_fim` | `TIMESTAMP` | — | Data e hora de término |
| `capacity` | `INTEGER` | — | Capacidade máxima de participantes |
| `type` | `VARCHAR(50)` | — | Tipo do evento (enum) |

---

## 🎭 Tipos de Evento

O sistema suporta as seguintes categorias de evento:

| Tipo | Descrição |
|------|-----------|
| `Musica` | Shows, concertos e festivais musicais |
| `Palestras` | Apresentações e talks |
| `Seminarios` | Seminários acadêmicos e profissionais |
| `Cultos` | Eventos religiosos e celebrações |
| `Workshops` | Oficinas práticas e treinamentos |
| `Networking` | Encontros de networking e conexões profissionais |

---

## 🗺 Roadmap

- [x] Modelagem do domínio (`Event`, `EventType`)
- [x] Estrutura de Clean Architecture (core / infra)
- [x] Configuração do banco PostgreSQL via Docker Compose
- [x] Migrations com Flyway
- [x] Mapper domínio ↔ entidade JPA
- [ ] Implementação completa do Gateway (persistência)
- [ ] Controllers REST (endpoints HTTP)
- [ ] DTOs de request/response
- [ ] Validação de dados de entrada (Bean Validation)
- [ ] Tratamento global de exceções (`@ControllerAdvice`)
- [ ] Documentação da API com Swagger/OpenAPI
- [ ] Autenticação e autorização (Spring Security + JWT)
- [ ] Testes unitários e de integração
- [ ] CI/CD pipeline
- [ ] Containerização completa da aplicação (Dockerfile)

---

## 📄 Licença

Este projeto está sob desenvolvimento. Consulte o autor para mais informações sobre licenciamento.

---

<p align="center">
  Feito com ☕ e dedicação.
</p>
