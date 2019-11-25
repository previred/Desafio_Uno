# Introducción

Implementación de un nuevo servicio REST. Este servicio REST invoca al servicio Generador De Datos(GDD) y entrega la respuesta en formato JSON con las fechas recibidas y las fechas faltantes. Ejemplo de la respuesta:

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

El archivo "api-periodos-1.0.0.jar" del servicio GDD fue incluido en este directorio con fines practicos para ejecutar y probar este projecto.

# Compilar y ejecutar el proyecto

Primero debemos ejecutar el servicio "api-periodos-1.0.0.jar" para levantar el servicio GDD.

Seguidamente debemos compilar y ejecutar este projecto,
una vez ejecutado este projecto el servicio estará escuchando
por el puerto 4444.

A continuacion los Comandos:

> java -jar api-periodos-1.0.0.jar
> mvn package
> cd target
> java -jar invocar_gdd.jar

Para consumir el servicio se debe invocar la siguiente URL:
Si usas swagger 
http://localhost:4444/swagger-ui.html 

Por insomnia, SoapUi y otras plataformas:
http://localhost:4444/invocar
