## periodos-perdidos
[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Proyecto **Springboot**, que contiene las API de servicio REST y toda la lógica de negocio e integración del con la api de periodos.

**Teconolía**
- Spring Boot 2.5.0
- JDK 1.8
- Maven

**Requisitos**
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Git](https://git-scm.com/)
- Editor de texto a elección. 
- [Spring Tool Suite 4 (STS)](https://spring.io/tools)

**Archivos de Configuración**
- periodos-perdidos/src/main/resources/application.properties

** Configurar en el archivo application.properties, puerto y url de api de servicios de periodos**
- server.port=8081
- server.servlet.context-path=/periodos
- api.periodos.url=http://127.0.0.1:8080/periodos/api


**Pasos para compilar en shell script**
Dentro del directorio **periodos-perdidos** ejecutar comandos
```console
foo@bar:~$ mvn clean package
```

**Pasos para ejecutar**
Ejecutar el comando en el directorio donde se encuentra el archivo periodos-perdidos-1.0.jar **periodos-perdidos/target**
```console
foo@bar:~$ java -jar periodos-perdidos-1.0.jar

**Consumir API de Servicio**
Para consumir el servicio se debe invocar la siguiente URL
```console
curl -X GET --header 'Content-Type: application/json' 'http://127.0.0.1:8081/periodos/perdidos'
```
**NOTA**
El servicio requiere que también esté funcionando la api de periodos.
- [api-periodos](https://github.com/previred/Generador_Datos_Desafio_Uno.git)

##Documentación de Servicio Swagger
- [url](http://localhost:8081/periodos/swagger-ui.htm)