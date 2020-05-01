# Instrucciones
Instrucciones para la ejecucion del servicio:


* mvn package
* java -jar .\data-aggregator-0.0.1-SNAPSHOT.jar

Para consumir el servicio se debe invocar la siguiente URL

* curl -X GET "http://localhost:8081/api/1.0/periods" -H "accept: application/json"

### Documentacion
La documentación swagger del API (una vez que se levanta el API) queda disponible en:

* http://localhost:8081/swagger-ui.html
