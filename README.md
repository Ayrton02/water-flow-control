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

Compile

```shell
mvn compile quarkus:build
```

Run
```shell
mvn quarkus:run
```