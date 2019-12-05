Aplicatico que retorna los periodos faltantes entre dos fechas.

-Para ejecutar el aplicativo se debe importar el proyecto desde STS, una vez importado, debera correr el aplicativo.
-El aplicativo se ejecutara en el puerto 8080, en caso de querer configurar un puerto distinto, debera modificar el archivo propertier la propiedad [server.port = 8080]
-inicializado el aplicativo, desde un navegador deberar acceder a la siguiente url: http://localhost:8080/swagger-ui.html#!/, el cual desplegara la interfaz de documentacion swagger ui.

Contrato de datos de entrada :

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

Contrato de dato de salida:

{
    "id": 6,
    "fechaCreacion": "1969-03-01",
    "fechaFin": "1970-01-01",
    "fechas": ["1969-03-01", "1969-05-01", "1969-09-01", "1970-01-01"],
    "fechasFaltantes": ["1969-04-01", "1969-06-01", "1969-07-01", "1969-08-01", "1969-10-01", "1969-11-01", "1969-12-01"]
}
