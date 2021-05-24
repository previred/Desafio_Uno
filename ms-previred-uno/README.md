# Desafio Uno Previred

Este proyecto expone un API REST que consume a ApiPeriodos y luego entrega la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
*fechasFaltantes*: Lista de fechas que NO están en “fechas”, considerando el rango dado desde “fechaCreacion” y hasta la fecha “fechaFin”
Ejemplo.
```json
{
  "id": 1,
  "fechaCreacion": "1995-02-01",
  "fechaFin": "1996-03-01",
  "fechas": [
    "1995-03-01",
    "1995-05-01",
    "1995-06-01",
    "1995-09-01",
    "1996-01-01"
  ],
  "fechasFaltantes": [
    "1995-02-01",
    "1995-04-01",
    "1995-07-01",
    "1995-08-01",
    "1995-10-01",
    "1995-11-01",
    "1995-12-01",
    "1996-03-01"
  ]
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd.
El servicio entrega un periodo el cual contiene fecha inicial, fecha final, lista de fechas y una lista con las fechas faltantes, las fechas faltantes son en base a las faltantes en la lista "fechas".

# Detalle de los sistemas

Java 8  
Spring-Boot 2.4.6  
Gradle 6.8

# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Gradle instalado.
Ingresar al directorio *ms-previred-uno* ejecutar el siguiente comando *gradle*

```bash
gradlew clean bootJar
```

Luego de limpiar y compilar el proyecto ejecutar el siguiente comando *java*

```bash
java -jar  build/libs/ms-previred-uno-0.0.1-SNAPSHOT.jar
```
*Nota*:
Debe estar disponible el puerto *8081* en el PC donde se ejecute esta API
Debe estar ejecutandose el proyecto *Generador_Datos_Desafio_Uno* en el puerto 8080 del mismo PC

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://localhost:8081/api/v1.0/uno/swagger-ui.html

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header "Accept: application/json" "http://127.0.0.1:8081/api/v1.0/uno/periodos"
```
