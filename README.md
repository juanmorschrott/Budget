# Budget Project

Set of applications for Budgets creation

### app

Android app

### backend

Backend Spring Boot server

- port: 8081

```
cd backend
mvn spring-boot:run
```
BASE-URL:   http://localhost:8080/api/v1/budgets

|OPERATION|METHOD|URI|
|---|---|---|
|CREATE|GET|http://localhost:8080/api/v1/budgets|
|READ|GET|http://localhost:8080/api/v1/budgets/{id}|
|UPDATE|PUT|http://localhost:8080/api/v1/budgets|
|DELETE|DELETE|http://localhost:8080/api/v1/budgets/{id}|

API Documentation:

[http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

### db

Database information

### frontend

Vuejs frontend application

- port: 8080

```
cd frontend
npm run serve
```

### proxy

Node js proxy server interface

- port: 8082

```
cd mocks
node ./App.js
```

### postman

Postman collection
