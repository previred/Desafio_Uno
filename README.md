**Postulante**: Rafael Mujica

**Email**: rafaeldanielmujica@gmail.com

**Outsourcing**: TechConsult

------------
# Generador Datos Desafio Full - GDDFull
 
 El proyecto GddFull expone un servicio API REST el cual consume el servico [GDD](https://github.com/previred/Generador_Datos_Desafio_Uno "GDD") para luego identificar las fechas faltantes y entregar una respuesta en formato JSON.
 
 Este proyecto esta construido usando:
 - Spring Boot 2.2.6.RELEASE
 - Maven 3
 - Java 8
 - swagger-codegen-maven-plugin-2.4.13

------------
 ### Compilar
 Antes de compilar valide la property *"gdd.service.url"* y modifique su valor si es necesario con la URL del servicio [GDD](https://github.com/previred/Generador_Datos_Desafio_Uno "GDD").
 
 Desde la raiz del proyecto editar application.properties.
 ```bash
vi src/main/resources/application.properties
```
Properties:
```bash
server.servlet.context-path=/gddfull
server.port=9090
gdd.service.url=http://localhost:8080/periodos/api
spring.jackson.date-format=com.previred.gddfull.swagger.invoker.RFC3339DateFormat

```
Luego desde linea de comando ejecutar lo siguiente:
 ```bash
mvn clean package
```
------------
 ### Ejecutar
 ##### Con Maven
 Ejecutar el siguiente comando.
  ```bash
mvn spring-boot:run
```
##### Ejecutar JAR
Antes de ejecutar el siguiente comando asegurese de haber realizado la compilación.
  ```bash
java -jar target/gddfull-*.jar
```
------------
### Request
##### Url
```json
GET   http://localhost:9090/gddfull/api/v1/periodos
```
#####  Header
```json
"Accept": "application/json"
```
#####  Curl
```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:9090/gddfull/api/v1/periodos'
```
------------
### Response
##### Header
```json
  "connection": "keep-alive",
  "content-type": "application/json",
  "date": "Wed, 08 Apr 2020 01:25:57 GMT",
  "keep-alive": "timeout=60",
  "transfer-encoding": "chunked"
```
##### Body
```json
{
  "id": 1,
  "fechaCreacion": "1980-02-01",
  "fechaFin": "1981-01-01",
  "fechas": [
    "1980-02-01",
    "1980-03-01",
    "1980-04-01",
    "1980-08-01",
    "1980-11-01",
    "1980-12-01"
  ],
  "fechasFaltantes": [
    "1980-05-01",
    "1980-06-01",
    "1980-07-01",
    "1980-09-01",
    "1980-10-01",
    "1981-01-01"
  ]
}
```
------------
### Swagger
La documentacion del servicio se encuenta en [swagger-ui.html](http://localhost:9090/gddfull/swagger-ui.html# "swagger-ui.html") y para ver el documento JSON generado acceder a la siguiente URL [api-docs](http://localhost:9090/gddfull/v2/api-docs "api-docs").

------------
### Swagger Codegen
Si se desea recrear el codigo de Swagger del API usando como fuente un archivo de configuracion YAML ejecutar el siguiente comando:
  ```bash
mvn clean package -Pswagger-source
```
Ubicacion de archivo YAML original:
 ```bash
vi src/main/resources/api.yaml
```
