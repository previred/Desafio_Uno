# Resolución del Desafío Uno

El proyecto permite resolver el desafío uno, que consiste en encontrar las fechas faltantes para un período determinado por el Generador De Datos creado para este desafío.
El proyecto solo expone 1 endpoint, el cual no recibe datos de entrada, y complementa la respuesta del Generador de Datos con las fechas faltantes.

*id*: identificador.  
*fechaCreacion*: Fecha de inicio de la secuencia.  
*fechaFin*: Fecha de fin de la secuencia.  
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en "fechaCreacion" hasta la fecha "fechaFin"
*fechasFaltantes*: Lista de fechas que no se encuentran en el rango anterior. 
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
    "fechasFaltantes": [
      "1969-04-01",
      "1969-06-01",
      "1969-07-01",
      "1969-08-01",
      "1969-10-01",
      "1969-11-01",
      "1969-12-01"]
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd

# Detalle de los sistemas

Java jdk 8  
Spring-Boot 2.5.1  
Gradle 7.0.2  


# Compilar y ejecutar el proyecto

Para compilar y ejecutar el proyecto se debe tener instalado previamente gradle y java, en las versiones descritas anteriormente, y luego ejecutar el siguiente comando en la raiz del proyecto.

```bash
gradlew bootRun
```
*Nota*:  
- Debe estar disponible el puerto *8080* en el PC donde se ejecute esta API
- Por defecto la url del Generador de Datos apunta a http://localhost:8081/periodos/api, si se desea modificar, se debe ejecutar el comando de la siguiente forma, especificando la url del Generador de Datos:

```bash
gradlew bootRun --args="--api.periodos=http://127.0.0.1:8080/periodos/api"
```

# Consumir la API

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET 'http://localhost:8080/v1/getFechasFaltantes'
```
