# Desafío 1: Periodos perdidos Nivel 3

Autor: César Antonio Torres Gonzalez
Email: cesartorres.cl@gmail.com
Vía por la cual me entere: Mediante Leonardo Miranda de Tech Consult.

Este proyecto corresponde a la solución del desafío 1/nivel 3
Esta solución expone un servicio GET http://localhost:8081/fechas/faltantes

Antes de ejecutar el proyecto se debe configurar la url en la cual se invocara el servicio GDD el cual se encuentra en la variable del application.yml urlPeriodos como se puede ver en el ejemplo:

```yaml
server:
 port: 8081
urlPeriodos: http://localhost:8080/periodos/api
```
Para compilar este proyecto se deben ejecutar los siguientes comandos  

```bash
mvn clean package
java -jar target/nivel3-0.0.1-SNAPSHOT.jar
```
El proyecto esta configurado para levantarse en el puerto 8081, si este puerto ya se encuentra en uso editar el archivo application.yml o ejecutar el jar indicando el puerto que se desea utilizar como lo muestra el siguiente ejemplo:

```bash
java -jar target/nivel3-0.0.1-SNAPSHOT.jar --server.port=7788
```

La entrada del servicio obtiene desde el servicio GDD es la siguiente:

```json
{
    "id": 6,
    "fechaCreacion": "1969-03-01",
    "fechaFin": "1970-01-01",
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

La salida del servicio es la siguiente:

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