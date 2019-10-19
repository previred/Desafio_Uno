# Fechas Faltantes Desafio

Este proyecto expone un API REST que entrega la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
*fechasFaltantes*: Lista de fechas faltantes entre el rango "fechaCreacion" y "fechaFin" no incluidas en la Lista de "fechas"
Ejemplo.
```json
{
    "id": 1,
    "fechaCreacion": "1985-04-01",
    "fechaFin": "1986-01-01",
    "fechas": [
        "1985-05-01",
        "1985-07-01",
        "1985-11-01"
        ],
    "fechasFaltantes": [
        "1985-04-01",
        "1985-06-01",
        "1985-08-01",
        "1985-09-01",
        "1985-10-01",
        "1985-12-01"
    ]
}
```
*Nota*:
El formato de cada fechas Faltante es yyyy-MM-dd
El servicio Invoca al servicio de Generacion de Datos Desafio donde este
entrega 1 periodos, el periodo contiene una fecha inicial una fecha final y una lista fechas, para luego
esta API REST completara la Lista fechasFaltantes

# Detalle de los sistemas

Java 8
Spring-Boot (v2.2.1.BUILD-SNAPSHOT)
Maven 3


# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *ApiPeriodos* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\desafioCayo-0.0.1.jar

```
*Nota*:
Debe estar disponible el puerto *8081* en el PC donde se ejecute esta API


# Visualizar donde Consumir la API

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8081/api/faltantes'
```

# Codigos de Respuestas

200 OK - Devuelve el Json con todos los campos (enviados por GDD y fechasFaltantes)
500 FALLO - Devuelve un Json con la siguiente estructura
{
    "mensaje":"Error al llamar al servicio Generador DD",
    "error":"I/O error on GET request for \"http://127.0.0.1:8080/periodos/api\": 
              Connection refused: connect; nested exception is java.net.ConnectException: 
              Connection refused: connect: Connection refused: connect"
}


# Elaborado por:

Christian Ayo Roca
christian.ayo@gmail.com
Tech Consult