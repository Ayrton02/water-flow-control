# WaterFlowControl

This project has the objective to control the water flow consumption on domestic tasks.

## Requirements

- Java 17
- Docker
- Maven

## How to execute

Variables

```shell
export DB_USER=water-flow-control
export DB_PASSWORD=water-flow-control
export DB_URL=jdbc:postgresql://localhost:5432/water-flow-control
```

### Executing Locally

Compile

```shell
mvn clean package
```

Run
```shell
java -jar ./target/quarkus-app/quarkus-run.jar
```

### Docker

Build Image
```shell
docker build -t water-flow-control .
```

Run Container
```shell
docker run --env-file .env water-flow-control
```