## Project Name: **Cartório Management System**

### Description

A Spring Boot-based API for managing **cartórios** (notary offices), **situações** (statuses), and **atribuições** (assignments). This API allows users to:

- Register **cartórios** (notary offices).
- Register **situações** (statuses) for cartórios.
- Register **atribuições** (assignments) for cartórios.
- Manage the relationship between **cartórios** and their **atribuições**.

### Features

- CRUD operations for **cartórios**, **situações**, and **atribuições**.
- Link **cartórios** to multiple **atribuições**, and vice versa.
- Pagination for list endpoints.
- Data validation and error handling, including:
  - Duplicate name check for **situações**, **atribuições**, and **cartórios**.
  - Referential integrity validation on deletion.
  - Unique ID validation during record creation.

---

### Table of Contents

1. [Project Setup](#project-setup)
2. [Running the Project](#running-the-project)
3. [API Endpoints](#api-endpoints)
4. [Database Schema](#database-schema)
5. [Testing](#testing)

---

### Project Setup

#### Requirements

- **Java 11** (Ensure JDK 11 is installed)
- **Gradle** (Optional: You can use the Gradle wrapper provided in the project)
- **H2 Database** (In-memory database used for development and testing)
- **IDE**: Any Java IDE (IntelliJ, Eclipse, VSCode)

#### Installation

1. Clone the repository:

```bash
   git clone <repository-url>
```

2. Navigate to the project directory:

```bash
   cd <project-directory>
```

3. Build the project:

```bash
   ./gradlew build
```

4. Set up your database by configuring the **`application.properties`** (or `application.yml`) file.

5. Run the project:

```bash
   `./gradlew bootRun`
```

6. The server will start on `http://localhost:9564` (or the port specified in the properties file).

---

### Running the Project

By default, the application runs on **port 9564**. You can access the API at `http://localhost:9564`.

If you want to change the port, modify the `server.port` property in `src/main/resources/application.properties`:

```properties
server.port=9564
```

### API Endpoints

#### Cartórios (Notary Offices)

- **GET** `/api/cartorios` – Get a list of cartórios (with pagination).
- **GET** `/api/cartorios/{id}` – Get a specific cartório by ID.
- **POST** `/api/cartorios` – Create a new cartório.
- **PUT** `/api/cartorios/{id}` – Update an existing cartório.
- **DELETE** `/api/cartorios/{id}` – Delete a cartório by ID.

#### Situações (Statuses)

- **GET** `/api/situacoes` – Get a list of situações (with pagination).
- **GET** `/api/situacoes/{id}` – Get a specific situação by ID.
- **POST** `/api/situacoes` – Create a new situação.
- **PUT** `/api/situacoes/{id}` – Update an existing situação.
- **DELETE** `/api/situacoes/{id}` – Delete a situação by ID.

#### Atribuições (Assignments)

- **GET** `/api/atribuicoes` – Get a list of atribuições (with pagination).
- **GET** `/api/atribuicoes/{id}` – Get a specific atribuição by ID.
- **POST** `/api/atribuicoes` – Create a new atribuição.
- **PUT** `/api/atribuicoes/{id}` – Update an existing atribuição.
- **DELETE** `/api/atribuicoes/{id}` – Delete an atribuição by ID.

#### Cartório-Atribuição Relationship

- **POST** `/api/cartorios/{cartorioId}/atribuicoes/{atribuicaoId}` – Link a cartório to an atribuição.
- **GET** `/api/cartorios/{cartorioId}/atribuicoes` – Get all atribuições associated with a specific cartório.

### Testing

Unit and integration tests are included in the project. To run the tests, execute:

`./gradlew test`

#### Test classes include:

- **CartorioServiceTest** – Tests for the service layer of Cartorios.
- **CartorioControllerTest** – Tests for the controller endpoints of Cartorios.
- **SituacaoServiceTest** – Tests for the service layer of Situações.
- **SituacaoControllerTest** – Tests for the controller endpoints of Situações.
- **AtribuicaoServiceTest** – Tests for the service layer of Atribuições.
- **AtribuicaoControllerTest** – Tests for the controller endpoints of Atribuições.

Reports are generated in `build/reports/tests/test/index.html`.
