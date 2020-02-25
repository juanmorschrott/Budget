FROM openjdk:8-alpine

RUN apk add maven

WORKDIR /app

COPY . .

RUN mvn clean install

EXPOSE 8081

ENTRYPOINT ["mvn", "spring-boot:run"]
