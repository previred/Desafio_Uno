# Desafío 1: Periodos perdidos

## Nivel 3

### Objetivo

> Implementar un nuevo servicio REST. Este servicio REST debe invocar al servicio GDD y entregar la respuesta en formato JSON con las fechas recibidas y las fechas faltantes. Ejemplo de la respuesta que debería entregar:

*Ejemplo JSON Salida*

```
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

### Librerías utilizadas

- IDE (STS) Spring Tool Suite 4
- Gradle
- Spring Boot 2.4.5
- SpringFox para Swagger 2
- OpenFeign para el cliente REST (Con muchas posibilidades de ser escalado a micro servicios)
- DevTool solo para ambiente de desarrollo

### Instrucciones para la ejecución

1. Ejecutar proyecto Api Periodos (Entregado por Previred)
2. Clonar el proyecto [Desafio_Uno](https://github.com/cristiansepulvedal/Desafio_Uno.git)
3. Abrir el proyecto con STS
4. Click Derecho sobre el proyecto
5. Run As -> Spring Boot App

### URLs Swagger

- [Swagger UI](http://localhost:9090/swagger-ui.html)
- [Documentacion API](http://localhost:9090/v2/api-docs)

### Datos de Contacto

- Nombre: Cristian Salvador Sepúlveda Lincheo
- Email: [c.sepulvedal.2016@gmail.com](mailto:c.sepulveda.2016@gmail.com)
- Origen Solicitud: Tech Consult (LinkedIn)


### knowledge issues
- En Swagger UI no reconoce la clase LocalDate.