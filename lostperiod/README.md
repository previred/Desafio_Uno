# README
Esta API, es un nuevo servicio REST que permite obtener los periodos perdidos dado los datos entregados por el servicio GDD. El servicio GDD entrega:
* id
* fechaCreacion
* fechaFin
* fechas

Posteriormente esta API obtiene todos los periodos que estan dentro del rango de fecha de creación y
fecha fin, incluyendo estas y deja los periodos que no estan en "fechas" en el dato de salida 
"fechasFaltantes".
Quedando de esta forma el JSON resultante:
```json
{
  "id": 1,
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
```

### PASOS PARA COMPILAR E INSTALAR
1. Descargue la app del repositorio.
2. Dentro de la carpeta "lostperiod", construya el proyecto Spring Boot con Maven.
```
mvn install
```
3. Ejecute la aplicación Spring Boot con Maven:

```
mvn spring-boot:run
```
4. [OPCIONAL] Ejecute la aplicación Spring Boot con el comando Java -jar, si es que tiene el jar generado.

```
Java -jar target/lostperiod-0.0.1-SNAPSHOT.jar
```
 
### PASOS PARA PROBAR
Para poder probar esta aplicación, el servicio Rest Generador de Datos (GDD), debe estar corriendo (en el puerto 8080),
ya que esta API consume ese servicio y lo necesita como entrada (request) para luego obtener los periodos perdidos.

Una forma de probarlo es a traves del swagger:

* http://127.0.0.1:8082/swagger-ui.html

