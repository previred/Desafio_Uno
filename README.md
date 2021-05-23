# Desafío 1: Periodos perdidos

Se resuelve para N2 y N3 usando maven, java 11 y SpringBoot
## Nivel 2

### Compilacion

```
 mvn compile assembly:single
```

### Ejecucion

```
 java -jar ./target/desafio-1.0.0.jar http://127.0.0.1:8080/periodos/api MissingDates.txt
 java -jar ./target/desafio-1.0.0.jar http://127.0.0.1:8080/periodos/api
 java -jar ./target/desafio-1.0.0.jar MissingDates.txt
 java -jar ./target/desafio-1.0.0.jar

```

## Nivel 3

### Levantar servicio

```
 mvn spring-boot:run
```

### Request de ejemplo

```
 curl 'http://localhost:8081/ws/api/desafio/missingDates'
```

