# API Fechas Faltantes

Este proyecto expone un API REST que entrega la siguiente información:

- **id**: identificador
- **fechaCreacion**: Fecha de inicio de la secuencia
- **fechaFin**: Fecha de fin de la secuencia
- **fechas**: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
- **fechasFaltantes**: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin” y que no se encontraron en la lista de fechas.
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
	  "1968-10-01",
	  "1968-11-01",
	  "1968-12-01",
	  "1969-01-01",
	  "1969-02-01",
      "1969-04-01",
      "1969-06-01",
      "1969-07-01",
	  "1969-08-01",
	  "1969-10-01",
	  "1969-11-01",
	  "1969-12-01",
	  "1971-01-01",
	  "1971-02-01",
	  "1971-03-01",
      "1971-04-01",
	  "1971-06-01"]
}
```
###### Notas:
- El formato de las fechas es yyyy-MM-dd
- El servicio entrega 1 periodos, el periodo contiene una fecha inicial una fecha final, una lista fechas dentro del periodo y una lista de fechas faltantes.

# Detalle de los sistemas
Java 8
Spring-Boot 2.1.3.RELEASE
Maven 3


# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *api-fechas-faltantes* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\api-fechas-faltantes-1.0.0.jar
```
###### Información adicional:
- Debe estar disponible el puerto *8081* en el PC donde se ejecute esta API.

- Debe estar disponible el servicio http://127.0.0.1:8080/periodos/api para una respuesta deseada.

- Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8081/periodofaltante/api'
```