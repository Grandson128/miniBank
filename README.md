# Getting Started

## How to install

    To install the application maven and JDK 21 are required.
    Execute the following to install:
        mvn clean install
    Execute the following to start the server:
        ./mvnw spring-boot:run

    It runs on localhost:8080

    You can open the client/index.html to test the functionalities.


## API Endpoints

| **Method** | **Endpoint**                          | **Description**                          |
|------------|---------------------------------------|------------------------------------------|
| `GET`     | `/api/users`                          | Get all active users                     |
| `GET`     | `/api/users/all`                      | Get all users, including inactive ones   |
| `POST`    | `/api/users`                          | Create a new user                        |
| `GET`     | `/api/users/{id}`                     | Get user details by ID                   |
| `GET`     | `/api/users/{id}/balance`             | Get user balance                         |
| `GET`     | `/api/users/{id}/transactions`        | Get user transaction history             |
| `DELETE`  | `/api/users/{id}`                     | Deactivate a user by ID                  |
| `PATCH`   | `/api/users/{id}/activate`            | Reactivate a user                        |
| `PATCH`   | `/api/users/{id}/deposit`             | Deposit money into a user’s account      |
| `PATCH`   | `/api/users/{id}/withdraw`            | Withdraw money from a user’s account     |
| `PATCH`   | `/api/users/{fromId}/{toId}/transfer` | Transfer money between two users |
