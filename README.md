# Generador Datos Desafio

Este proyecto expone un API REST que entrega la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
Ejemplo.
```json
{
    "id": 6,
    "fechaCreacion": "1968-08-01",
    "fechaFin": "1971-06-01",
    "fechas": [
      "1969-03-01",
      "1969-05-01",
      "1969-09-01",
      "1971-05-01"]
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd
El servicio entrega 1 periodos, el periodo contiene una fecha inicial una fecha final y una lista fechas.

# Detalle de los sistemas

Swagger 2.9.2
Java 8
Spring-Boot 2.2.6.RELEASE
Gradle 5.4.1


# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Gradle instalado.
Ingresar al directorio *ApiPeriodos* ejecutar el siguiente comando *gradle*

```bash
gradle clean build
gradle bootrun
```

*Nota*:
Debe estar disponible el puerto *8081* en el PC donde se ejecute esta API
Para la consulta de la api externa es "http://localhost:8082/periodos/api" 
en caso de que exista una nueva url realizar el cambio en el archivo aplication.yml

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://localhost:8081/periodos/swagger-ui.html#/

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8081/periodos/perdidos'
```
