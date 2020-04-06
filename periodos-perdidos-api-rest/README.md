Para resolver el ejercicio opte por la opción 3, que consiste en implementar un nuevo servicio
REST con Spring Boot, ocupe el IDE Spring Tool Suite con maven y SWAGGER


Indicaciones del proyecto:

Para iniciar swagger:

Url: http://localhost:8081/swagger-ui.html#/
(el proyecto esta configurado en el puerto 8081, ya que la API GDD levanta en el puerto 8080)

Instrucciones para iniciar el proyecto:

Ejecutar con maven los siguientes comandos:

mvn clean 
mvn package

en directorio target ejecutar:

java -jar periodos-perdidos-api-rest-0.0.1-SNAPSHOT.jar


Para obtener las fechas faltantes, sugiero utilizar Postman, se debe considerar lo siguiente:

Metodo: GET
Url: http://localhost:8081/api/periodos-faltantes










