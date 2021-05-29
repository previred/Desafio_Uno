# Desafío 1: Periodos perdidos

En la solución del ejercicio se opto por el Nivel 3.

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

Implementación:
- Se utilizo Java SpringBoot, se agrego la documentación con Swagger.
- Los archivos de entrada y salido se encuentran en la carpeta result.

## Instalación:

Para la instalción ejecutar el comando en la raiz del proyecto
```
    mvn clean install
```
El ejecutable de la aplicación se encuentra en la carpeta /target

## Ejecución:

```
    java -jar periodosperdidos-final.jar
```

## Swagger

Documentación: http://{url}/swagger-ui.html

Fechas: http://{url}/api/pedidos
