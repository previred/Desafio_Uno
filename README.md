# Generador Datos Desafio

Este proyecto se compone de varios servicios y expone un API REST que entrega la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
*fechasFaltantes*: Lista de fechas que están en el rango de fechas “fechaCreacion” hasta la fecha “fechaFin” pero no en *fechas*
Ejemplo (Nota: no se está aplicando funcionalidad en la siguiente respuesta).
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
    "fechasFaltantes": [
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

api-periodos
Swagger Codegen 2.3.1 (OpenApi 2.0)
Java 8
Spring-Boot 1.5.11.RELEASE
Maven 3

Resto de Aplicaciones
Java 8
Sring-Boot 2.3.11.RELEASE



# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar a los directorios de las aplicaciones (prueba1 api-periodos eurkaserver configserver y zuulserver) ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*
	En cada uno de los proyectos lanzar el arhivo jar que se encuentra en cada carpeta 

```bash
java -jar .\api-periodos-1.0.0.jar
```
*Nota IMPORTANTE LEER*:
A continuación se indican los puertos disponibles en la red a utilizar y orden de ejecución de los empaquetados jar generados previamente

configserver	puerto 8888
eurekaserver	puerto 8761
zuulserver		puerto 8050
api-periodos 	puerto 8080
prueba1			puerto 8090



Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8050/prueba1/Periodos-v1-0-0/PeriodosPerdidos/fechasAleatorias'
```