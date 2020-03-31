#Desafio previred
Postulante: Carolina Lobos

El servicio se comunica con una API REST, de quien recibe lo siguiente:
*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
Ejemplo.
```json
{
    "id": 6,
    "fechaCreacion": "1970-08-01",
    "fechaFin": "1971-06-01",
    "fechas": [
      "1970-08-01",
      "1970-09-01",
      "1971-02-01",
      "1971-05-01"]
}
```
De acuerdo al periodo entregado genera un objeto que replica la informacion recibida
y agrega una lista con todas las fechas faltantes ej:
```json
{
    "id": 6,
    "fechaCreacion": "1970-08-01",
    "fechaFin": "1971-06-01",
    "fechas": [
      "1970-08-01",
      "1970-09-01",
      "1971-02-01",
      "1971-05-01"],
    "fechasFaltantes": [
      "1970-10-01",
      "1970-11-01",
      "1970-12-01",
      "1971-01-01",
      "1971-03-01",
      "1971-04-01",
      "1971-06-01"]
}
```
#para compilar y ejecutar
1-debe encontrase activo el servicio GDD , para ejecutar el servicio GDD en su equipo realize lo siguiente:

***ESTE SERVICIO UTILIZA EL PUERTO 8080
Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *ApiPeriodos* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\api-periodos-1.0.0.jar
```
***ESTE SERVICIO UTILIZA EL PUERTO 8090
2-Abrir una nueva CMD, ubicarse dentro del proyecto (desafio) y ejecutar el comando 

```bash
mvn package
```

cuando termine de compilar, ejecutar el jar ubicandose en la carpeta /target

```bash
java -jar .\desafio-1.0.0.jar
```
#documentacion

Para ver detalles de metodos y endpoits, con el servicio activo ingresar a :

http://localhost:8090/swagger-ui.html

#consumir la api
http://localhost:8090/periodo/resultado

