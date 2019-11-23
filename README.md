# Periodos Perdidos

Este proyecto expone un API REST que consume el API REST GDD que entrega la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
*fechasFaltantes*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin” pero que no esta en la lista de fechas proportcionadas por GDD
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
      "1971-05-01"],
    "fechasFatantes": [
          "1969-04-01",
          "1969-06-01",
          "1969-07-01",
          "1969-08-01"]
   
      
}
```

# Detalle de los sistemas

Swagger Codegen 2.9.2 
Java 8
Spring-Boot 2.2.1.RELEASE
Maven 3.6.2


# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *Desafio_Uno* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\periodosPerdidos-0.0.1-SNAPSHOT.jar
```
*Nota*:
Debe estar disponible el puerto *8081* en el PC donde se ejecute esta API

*Nota*:
Debe estar disponible el servicio GDD para su consumo en el purto 8080 o bien puedes cambiar la url del servicio en el archivo *application.properties*

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://127.0.0.1:8081/swagger-ui.html#/

Para consumir el servicio se debe invocar la siguiente URL

```bash
'http://127.0.0.1:8081/api/periodosPerdidos'
```
