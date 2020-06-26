# Generador Datos Desafío

Esta solución se implementó según la propuesta de nivel N°3 y utilizando Swagger. Es decir, sobre el mismo proyecto se implementó un
nuevo servicio rest llamado api2 que se consume desde la URL: http://127.0.0.1:8080/periodos/api2 Este servicio 
incova al servicio api en la URL: http://127.0.0.1:8080/periodos/api en la misma instancia de Spring Boot y entrega
como resultado la información de la ejecución del servicio api más el listado de la fechas faltantes como el siguiente 
formato:

```json
{
    "id": 1,
    "fechaCreacion": "1981-01-01",
    "fechaFin": "1981-01-01",
    "fechas": [
        "1981-05-01",
        "1981-08-01",
        "1981-09-01",
    ],
    "fechasFaltantes": [
		"1981-01-01",
		"1981-02-01",
		"1981-03-01",
        "1981-04-01",
        "1981-06-01",
        "1981-07-01",
        "1981-10-01",
        "1981-11-01",
        "1981-12-01"
    ]
}
```

# Detalle de los sistemas

Java 1.8.0_171  
Maven 3  
Spring-Boot 1.5.9.RELEASE  
Swagger Codegen 2.3.1 (OpenApi 2.0)  
IDE Spring Tool Suite 4.6.1.RELEASE  

# Compilar y ejecutar el proyecto

Mismas indicaciones entregadas por PreviRed; se repiten a continuación:

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *ApiPeriodos* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\api-periodos-1.0.0.jar
```
*Nota*:
Debe estar disponible el puerto *8080* en el PC donde se ejecute esta API

# Visualizar Documentación y consumir la API

También corresponden a indicaciones similares a las entregadas por PreviRed, sólo que en este caso
se incorpora el servicio adicional para entregar la fechas faltantes.

La documentación swagger del API (una vez que se levanta el API) queda disponible en:

http://127.0.0.1:8080/periodos/swagger-ui.html#/  
Servicio api: "periodos"  
Servicio api2: "periodoscompletos"  

Para consumir los servicios se deben invocar las siguientes URL:

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8080/periodos/api'
```

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8080/periodos/api2'
```