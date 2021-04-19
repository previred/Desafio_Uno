#Respuesta desafío 1: Periodos Perdidos

Respuesta a desafío de programación para ingresar a Previred.

La solución desarrollada corresponde al nivel 3.  Se construyó una API rest que consume la api GDD mediante Feign.

Para la construcción de la solución se utilizó:

	- swagger codegen 2.4.19 (OpenApi 2.0)
	- Java 8 Spring-Boot 2.3.10.RELEASE
	- Maven 3.6.3


Un ejemplo de la salida que entrega el servicio:

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

#Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *ApiPeriodosPerdidos* ejecutar el siguiente comando *maven*

```bash
mvn package

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\api-periodos-perdidos-1.0.0.jar
```
*Nota*:
Debe estar disponible el puerto *8081* en el PC donde se ejecute esta API

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://127.0.0.1:8081/swagger-ui.html#/

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8081/periodos-perdidos/api/'