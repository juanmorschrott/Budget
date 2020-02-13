# Budget Project

A Set of applications for Budgets creation

### Diagram

![Diagram](https://github.com/juanmorschrott/Budget/blob/master/information/img/Diagram.png)

### app

Android app

### backend

Backend Spring Boot server

- port: 8081

```
mvn spring-boot:run
```
BASE-URL:   http://localhost:8080/api/v1/budgets

|OPERATION|METHOD|URI|
|---|---|---|
|CREATE|POST|http://localhost:8080/api/v1/budgets|
|READ|GET|http://localhost:8080/api/v1/budgets/{id}|
|UPDATE|PUT|http://localhost:8080/api/v1/budgets|
|DELETE|DELETE|http://localhost:8080/api/v1/budgets/{id}|

API Documentation:

[http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

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

### information

Project information:
 - db: Database Information
 - postman: Requests Collection
 - img: Project Diagram
