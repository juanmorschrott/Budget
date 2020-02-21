FROM openjdk:8-alpine

RUN apk add maven

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:copy-dependencies

COPY . .

RUN mvn install

EXPOSE 8081

CMD ["mvn", "spring-boot:run"]
