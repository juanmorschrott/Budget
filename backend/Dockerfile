FROM openjdk:8-alpine

RUN apk add maven

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

EXPOSE 8081

ENTRYPOINT ["mvn", "spring-boot:run"]
