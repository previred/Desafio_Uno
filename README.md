# Desafío 1: Periodos perdidos Nivel 3

El desafío consiste en lo siguiente:

-   Existe un servicio REST que llamaremos Generador De Datos o GDD.
    -   El servicio responde con una lista de fechas generadas aleatoriamente. Estas fechas se encuentran en un lapso definidos por dos valores: fechaCreacion y fechaFin.
    -   Cada fecha generada corresponde al primer día de un mes.
    -   La respuesta contienen un máximo de 100 fechas.
    -   El servicio no entrega todas las fechas dentro del periodo, omite algunas de forma también aleatoria.
-   El objetivo de este ejercicio es que determines cuáles son los periodos que faltan.

## Nivel 3:

Implementar un nuevo servicio REST. Este servicio REST debe invocar al servicio GDD y entregar la respuesta en formato JSON con las fechas recibidas y las fechas faltantes.
Ejemplo de la respuesta que debería entregar:

```json
{
    "id": 6,
    "fechaCreacion": "1969-03-01",
    "fechaFin": "1970-01-01",
    "fechas": [
      "1969-03-01",
      "1969-05-01",
      "1969-09-01",
      "1970-01-01"],
    "fechasFaltantes": [
      "1969-04-01",
      "1969-06-01",
      "1969-07-01",
      "1969-08-01",
      "1969-10-01",
      "1969-11-01",
      "1969-12-01"]

}
```
### Implementación de la solucion (Nivel 3):

Se construye servicio REST que invoca a servicio GDD y entregar la respuesta en formato JSON con las fechas recibidas y las fechas faltantes

### Generación reporte de cobertura JaCoCo
`mvn jacoco:report`

### Levantar aplicación

* Por defecto la API se levanta en el puerto 8080
* Por defecto el servicio GDD lo ubica en `http://localhost:8090/periodos/api`

### Swagger
`http://localhost:8080/swagger-ui.html#/`

### Llamadas a servicio que obtiene fechas faltantes de servicio GDD

`curl --location --request GET 'http://127.0.0.1:8080/api/periods' \
--header 'Accept: application/json'`

##### Autor: Kendall Navarro
##### Correo: kendall.navarro84@gmail.com