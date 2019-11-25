
# Periodos Perdidos
Este proyecto expone un API REST que consume al servicio **Generador De Datos** y entrega la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
*fechasFaltantes*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”, y que no están en *fechas*.

Ejemplo.
```json
{
    "id": 1,
    "fechaCreacion": "1968-08-01",
    "fechaFin": "1971-06-01",
    "fechas": [
      "1969-03-01",
      "1969-05-01",
      "1969-09-01",
      "1971-05-01"],
    "fechasFaltantes": [
    "1968-08-01",
    "1968-09-01",
    ...
    "1969-02-01"
    "1969-04-01"
    ...
    "1971-06-01"]
}
```
*Nota*:
* El formato de las fechas es yyyy-MM-dd
* El servicio entrega 1 resultado, el periodo contiene una fecha inicial una fecha final y dos listas de fechas, que corresponden a las fechas generadas por el Generador de Datos y las fechas faltantes.
* El Generador de Datos se consume desde la ip http://127.0.0.1:8080/periodos/api bajo el supuesto que sencuentra en la misma máquina (se puede modificar en el archivo application.yml).

# Detalle de los sistemas

Swagger Codegen 2.3.1 (OpenApi 2.0)
Java 8
Spring-Boot 2.2.1.RELEASE
Maven 3.6.0


# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *periodos-perdidos* y ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\api-periodos-perdidos-1.0.0.jar
```
*Nota*:
Debe estar disponible el puerto *8081* en el PC donde se ejecute esta API

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://localhost:8081/periodos-perdidos/swagger-ui.html

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8081/periodos-perdidos/periodos-perdidos'
```
