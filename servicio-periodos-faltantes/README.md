# Generador Datos Desafio

Este proyecto expone un API REST que entrega la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
Ejemplo.
```json
{
    "id": 6,
    "fechaCreacion": "1968-08-01",
    "fechaFin": "1971-06-01",
    "fechas": [
      "1969-03-01",
      "1969-05-01",
      "1969-09-01",
      "1971-05-01"]
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd
El servicio entrega 1 periodos, el periodo contiene una fecha inicial una fecha final y una lista fechas.

# Detalle de los sistemas

Java 8
Spring-Boot 2.2.6
Maven 3.6.3 EMBEDDED


# Cómo Compilar y ejecutar el proyecto

Para el funcionamiento del proyecto debe estar en ejecución el proyecto *ApiPeriodos*.
Esto es debido a que el proyecto *servicio-periodos-faltantes* ejecuta una llamada REST a este servicio.

## Según la documentación de compilación del proyecto *ApiPeriodos*

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *ApiPeriodos* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\api-periodos-1.0.0.jar
```
*Nota*:
Debe estar disponible el puerto *8080* en el PC donde se ejecute esta API

## Compilación del proyecto *servicio-periodos-faltantes*

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *servicio-periodos-faltantes* ejecutar el siguiente comando *maven* o *maven wrapper*

```bash
mvnw clean package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\servicio-periodos-faltantes 0.0.1-SNAPSHOT
```
*Nota*:
Debe estar disponible el puerto *8081* en el PC donde se ejecute esta API


# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://localhost:8001/swagger-ui.html

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8001/periodos-faltantes'
```