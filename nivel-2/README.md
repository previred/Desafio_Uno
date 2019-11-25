
# Periodos Perdidos
Este proyecto disponibiliza una aplicacion en línea de comandos que consume el **Generador De Datos** y entrega un archivo con la siguiente información:

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
* El archivo se genera en el directorio donde se encuentra la aplicación, con el nombre **periodosPerdidos.json**, que contiene una fecha inicial una fecha final y dos listas de fechas, que corresponden a las fechas generadas por el Generador de Datos y las fechas faltantes.
* El formato de las fechas es yyyy-MM-dd
* El Generador de Datos se consume desde la IP http://127.0.0.1:8080/periodos/api bajo el supuesto que sencuentra en la misma máquina (se puede modificar en el archivo application.yml).

# Detalle de los sistemas
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
Al terminar la ejecución se habrá generado el archivo **periodosPerdidos.json**

