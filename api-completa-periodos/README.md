# Solucion Desafio uno

Este proyecto expone un API REST que consume el Generador Datos Desafio (GDD) para entregar la informacion de ese servicio mas el calculo de las fechas faltantes

en detalle los datos son:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
*fechasFaltantes*: Lista de fechas faltantes que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
Ejemplo.
```json
{
    "id": 1,
    "fechaCreacion": "1996-08-01",
    "fechaFin": "2016-02-01",
    "fechas": [
        "1996-09-01",
        "1996-11-01",
        "1997-03-01",
        "1997-04-01",
        "1997-05-01",
        "1997-10-01",
        "1997-11-01",
        "1998-01-01",
        "1998-02-01",
        "1998-03-01",
        "1998-07-01",
        "1998-08-01",
        "1999-01-01",
        "1999-04-01",
        "1999-06-01",
        "1999-08-01",
        "1999-12-01",
        "2000-01-01",
        "2000-03-01",
        "2000-09-01",
        "2000-10-01",
        "2000-11-01",
        "2001-03-01",
        "2001-04-01",
        "2001-07-01",
        "2001-08-01",
        "2001-10-01",
        "2002-02-01",
        "2002-06-01",
        "2002-07-01",
        "2002-08-01",
        "2002-11-01",
        "2003-02-01",
        "2003-03-01",
        "2003-04-01",
        "2003-06-01",
        "2003-09-01"
    ],
    "fechasFaltantes": [
        "1996-08-01",
        "1996-10-01",
        "1996-12-01",
        "1997-01-01",
        "1997-02-01",
        "1997-06-01",
        "1997-07-01",
        "1997-08-01",
        "1997-09-01",
        "1997-12-01",
        "1998-04-01",
        "1998-05-01",
        "1998-06-01",
        "1998-09-01"
    ]
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd
El servicio entrega 1 periodos, el periodo contiene una fecha inicial una fecha final y una lista fechas.

# Detalle de los sistemas

* springfox swagger
* JDK 8
* Spring-Boot 2.5.0
* Maven 3

# Prerrequisitos

se debe estar ejecutando el Generador Datos Desafio (GDD) en su puerto 8080

# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *api-completa-periodos* ejecutar el siguiente comando *maven*

```bash
mvn clean compile package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\api-completa-periodo.jar
```
*Nota*:
Debe estar disponible el puerto *8085* en el PC donde se ejecute esta API

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

[Definicion api](http://localhost:8085/swagger-ui/)

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET "http://localhost:8085/periodos/full" -H "accept: */*"
```