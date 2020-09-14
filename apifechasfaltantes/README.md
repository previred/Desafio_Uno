# Solución de Desafio 1

Este proyecto expone un API REST que entrega la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
*fechasFaltantes*: Lista de fechas faltantes en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”, respecto a “fechas”
Ejemplo.
```json
{
    "id": 1,
    "fechaCreacion": "1990-01-01",
    "fechaFin": "1990-10-01",
    "fechas": [
      "1990-01-01",
      "1990-03-01",
      "1969-05-01",
      "1971-07-01"],
    "fechasFaltantes": [
      "1990-02-01",
      "1990-04-01",
      "1990-06-01",
      "1990-08-01",
      "1990-09-01",
      "1990-10-01"]
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd

# Detalle de los sistemas

Swagger-UI 2.9.2
Java 8
Spring-Boot 2.3.3.RELEASE
Maven 3


# Compilar y ejecutar el proyecto

Para compilar el proyecto se requiere que Java y Maven esten instalados.
Ingresar al directorio *apifechasfaltantes* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar ./apifechasfaltantes-1.0.0-SNAPSHOT.jar
```
*Nota*:
Debe estar disponible el puerto *8087* en el PC donde se ejecute esta API, de lo contrario modifique el
registro server.port ubicado en el archivo application.properties presente en el classpath

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://localhost:8087/fechasFaltantes/swagger-ui.html/

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET "http://localhost:8087/getFechasFaltantes" -H "accept: */*"
```