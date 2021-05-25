# API Generador Datos Faltantes

Este proyecto expone un API REST que entrega la siguiente información:

*id*: identificador.
*fechaCreacion*: Fecha de inicio de la secuencia.
*fechaFin*: Fecha de fin de la secuencia.
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”.
*fechasFaltantes*: Lista de las fechas que no aparecen en la lista de fechas anterior.
Ejemplo.
```json
{
    "id": 6,
    "fechaCreacion": "1968-08-01",
    "fechaFin": "1969-02-01",
    "fechas": [
      "1968-08-01",
      "1968-09-01",
      "1968-10-01",
      "1969-01-01"]
     "fechasFaltantes": [
      "1968-11-01",
      "1968-12-01",
      "1969-02-01"]
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd

# Detalle de los sistemas

Swagger Codegen 2.9.2 
Java 1.8.0_191
Spring-Boot 2.0.5.BUILD-SNAPSHOT
Maven 3.8.1


# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *ApiPeriodos* ejecutar el siguiente comando *maven*

```bash
mvn clean package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\generador-periodos-faltantes-1.0.jar
```
*Nota*:
Debe estar disponible el puerto *8085* en el PC donde se ejecute esta API, sino, se puede cambiar en el parametro del archivo *application.properties* ubicado en la carpeta *\resources* del proyecto.

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://127.0.0.1:8085/swagger-ui.html

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8085/missingPeriods'
```
