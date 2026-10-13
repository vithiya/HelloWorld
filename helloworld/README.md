# Hello World API

A simple REST API built with Java and Spring Boot. 
The API accepts a name as a query parameter and returns a message when the name starts with a letter from A to M. 
Names starting with N to Z, as well as invalid inputs, return HTTP 400 Bad Request.

## Technologies

- Java 17
- Spring Boot
- Maven
- JUnit 5
- Spring MockMvc

## Endpoint

`GET /hello-world?name={name}`

### Successful response

Request: `/hello-world?name=alice`

HTTP status: `200 OK`

```json
{
  "message": "Hello Alice"
}
```

### Invalid response

Request: `/hello-world?name=Narmatha`

HTTP status: `400 Bad Request`

```json
{
  "error": "Invalid Input"
}
```

Missing, empty, whitespace-only, or invalid starting characters are rejected with HTTP 400.

## Prerequisites

- Java 17 or later
- Maven

## Run the application

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The API will be available at `http://localhost:8080`.

## Run the tests

On Windows PowerShell:

```powershell
.\mvnw.cmd test
```

The tests cover valid names, alphabet boundary cases, missing and empty parameters, whitespace-only values, 
and invalid starting characters.

## Assumptions

- Validation is limited to the letters A–Z, case-insensitively.
- Leading and trailing whitespace is trimmed.
- The name is converted to title case before generating the message.
- Invalid or missing input returns HTTP 400 with a consistent JSON error response.