### Desafio periodos perdidos Nivel 1

#### Introducción

La presente aplicación tiene como objetivo generar las fechas faltantes dentro de un rango de fecha contenidos en un archivo JSON. Como por ejemplo si el programa procesa un archivo con el siguiente contenido:


```html
{
  "id": 6,
  "fechaCreacion": "1999-03-01",
  "fechaFin": "2001-02-01",
  "fechas": [
    "1999-03-01",
    "1999-05-01",
    "2000-09-01",
    "2001-02-01"
  ]
}
```

Al ejecutar el programa generará otro archivo con todo el contenido del archivo de origen mas las fechas faltantes de dicho periodo

```html
{
  "id": 6,
  "fechaCreacion": "1999-03-01",
  "fechaFin": "2001-02-01",
  "fechas": [
    "1999-03-01",
    "1999-05-01",
    "2000-09-01",
    "2001-02-01"
  ],
  "fechasFaltantes": [
    "1999-04-01",
    "1999-06-01",
    "1999-07-01",
    "1999-08-01",
    "1999-09-01",
    "1999-10-01",
    "1999-11-01",
    "1999-12-01",
    "2000-01-01",
    "2000-02-01",
    "2000-03-01",
    "2000-04-01",
    "2000-05-01",
    "2000-06-01",
    "2000-07-01",
    "2000-08-01",
    "2000-10-01",
    "2000-11-01",
    "2000-12-01",
    "2001-01-01"
  ]
}
```

#### Generación del JAR y ejecución del programa

	Para ejecutar el programa se debe generar un archivo de tipo JAR Runnable con su clase principal GenFechasFaltantes. Desde el IDE Eclipse se deben seguir los siguientes pasos: Click derecho sobre el proyecto -> exportar -> Runnable Jar File ->Siguiente -> En el listado Launch configuration se debe seleccionar la clase GenFechasFaltantes -> Finish. Una vez generado el archivo JAR ya se puede ejecutar la aplicación desde la linea de comandos con la siguiente instrucción:

*java -jar DesafioPrevired.jar NombreArchivoOrigen NombreArchivoDestino*

Una vez ejecutado el programa se generará un archivo con el nombre del archivo de destino en la ruta especificada.

PD: El desarrollo de la aplicación se realizó con lenguaje Java versión 15.
