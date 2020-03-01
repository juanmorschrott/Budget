FROM openjdk:8-alpine

RUN apk add maven

WORKDIR /app

COPY . .

EXPOSE 8081

ENTRYPOINT ["/bin/sh", "entrypoint.sh"]

CMD ["mvn", "spring-boot:run"]
