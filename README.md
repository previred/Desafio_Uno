# Desafio Uno

Este proyecto expone un API REST que entrega la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
*fechasFaltantes*: Lista de fechas faltantes segun informacion recibida de API: "Generador Datos Desafio"
Ejemplo.

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
*Nota*:
El formato de las fechas es yyyy-MM-dd
El servicio entrega 1 periodo con la información de la API consumida donde el periodo contiene una fecha inicial una fecha final, una lista fechas y una lista de fechas faltantes.

# Detalle de los sistemas

Java 8
Spring-Boot 2.5.6
Maven 3


# Compilar y ejecutar el proyecto
Como requisito se compilar y ejecutar el proyecto: "Generador Datos Desafio", para ello seguir las intrucciones indicadas
en el Readme del proyecto ubicado en

https://github.com/lmptechconsult/Desafio_Uno


Para copilar el proyecto se requiere Java y Maven instalado.
```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar GeneradorDeDatos-0.0.1-SNAPSHOT.jar
```
*Nota*:
Debe estar disponible el puerto *8080* 


Ejecutar en PostMan
Enviar con post con la siguiente url *localhost:8080/api/gdd*
y en body agregar
```
{
    "fechaCreacion":"1968-08-01",
    "fechaFin":"1982-06-01",
    "fechas": [
      "1969-03-01",
      "1969-05-01",
      "1969-09-01",
      "1970-01-01"]
}
```
Para ejecutar swagger dirigirse a la siguiente url
http://localhost:8080/swagger-ui.html#/gdd-controller
y ocupar el body descrito anteriormente
