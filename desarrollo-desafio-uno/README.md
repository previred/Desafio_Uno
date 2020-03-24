# Jorge Esteban Satizabal Hoyos
## satihoyos@gmail.com
## LinkedIn, Tech Consult, Leonardo Miranda

###Instalación 

Se implemento nivel 3 del desafio:
- Requisitos: java 8, maven 3
- Descargar [Generador_Datos_Desafio_Uno](https://github.com/previred/Generador_Datos_Desafio_Uno), compilar y ejecutar proyecto

pasos para ejecutar proyecto:

```bash
mvn package
cd target
java -jar desarrollo-desafio-uno-0.0.1-SNAPSHOT.jar
```
*Nota*:
Debe estar disponible el puerto *8080* en el PC donde se ejecute esta API

### Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://127.0.0.1:8080/desafio
http://127.0.0.1:8080/desafio/swagger-ui.html#/

Para consumir el servicio se debe invocar la siguiente URL: *http://localhost:8081/desafio/periodo/aleatorio*

```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8081/desafio/periodo/aleatorio'
```
