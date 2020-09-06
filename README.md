#Desafio Previred Nivel 3

Pre-requisitos:
* JDK 1.8
* Maven 3.6.2

Para correr el proyecto:

Se debe desplegar local el servicio Generador De Datos que se encuentra en el siguiente link:
https://github.com/previred/Generador_Datos_Desafio_Uno

En la carpeta del proyecto ejecutar en Terminal los siguientes comandos:
* mvn clean
* mvn install

En la misma carpeta, en CLI se debe ejecutar después:  
* mvn spring-boot:run



Al desplegar este servicio local, este en su application.properties se define el puerto 8081.

Se debe hacer una petición de tipo GET sin parámetros al siguiente URL:
* http://localhost:8081/genDates

URL para ver documentación en Swagger-UI:
* http://localhost:8081/swagger-ui.html