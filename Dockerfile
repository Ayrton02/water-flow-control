FROM maven:3.9.0 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM alpine:3.21

RUN apk add --no-cache openjdk17-jre

WORKDIR /app

COPY --from=builder /app/target/quarkus-app/ ./quarkus-app

EXPOSE 8080

CMD ["java", "-jar", "./quarkus-app/quarkus-run.jar"]