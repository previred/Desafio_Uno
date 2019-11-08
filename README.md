# Desafío 1: Periodos perdidos

Resolución del desafio Periodos perdidos. 

La solución escogida fue la del nivel 3 que consiste en lo siguiente:

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

Para ejecutar es proyecto es necesario:

- Tener corriendo el servicio GDD para que este proyecto pueda conectarse, preferiblemente tener el proyecto configurado en: "127.0.0.1:8080". Este se encuentra alojado en el siguiente repositorio -> https://github.com/previred/Generador_Datos_Desafio_Uno.
- Tener JDK 8
- Descargar este proyecto.
- Hacer maven clean, compile, install para descargar e instalar las dependecias.
- Ejecutar el proyecto.
- Se puede acceder al servicio por medio de "curl -X GET "http://127.0.0.1:8081/desafio" -H "accept: */*" "
- En caso de tener dudas usar la documentación de la API en "http://127.0.0.1:8081/swagger-ui.html#/"
- En la raíz del proyecto esta un archivo JSON llamado "response.json" con una de las respuestas posible del servicio, no es necesario ningún request o información de entrada para ejecutar el servicio, por eso NO existe un archivo similar al del response pero con el request.
- Solución en Django -> https://github.com/eilincastillo/Desafio_Python
