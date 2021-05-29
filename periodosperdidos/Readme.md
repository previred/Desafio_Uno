# Desafío 1: Periodos perdidos

Para el desafio se opto por el Nivel 3 y se obtuvo la data de entrada del servicio indicado en el repositorio en GitHub:
https://github.com/previred/Generador_Datos_Desafio_Uno

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

Implementacion:
-   Se utilizo Java Spring-boot, con los pluggin: Swagger, Lombok, con una arquitectura de Micro-Servicios 
-   Aunque al acceder al end-point del servicio tambien se generan los archivos .json se incluiran en la carpeta resultado

## Instalacion:

Para la instalacion se debe hacer lo siguiente (Si desea cambiar el puerto lo puede hacer en el archivo application.properties):

```
    mvn clean install
```
Esto generara los artefactos en la carpeta /target

## Ejecucion:

Para la Ejecucion se debe hacer lo siguiente:

```
    java -jar periodosperdidos-0.0.1-SNAPSHOT.jar
```
## End-Points:
```
Documentacion: /swagger-ui.html
Servicio fechas: /api/periodosperdidos
```