# Servicio fechas faltantes

Proyecto que implementa el "Desafio Uno" usado para la postulación a cargos dentro de Previred.


## Overview  

El proyecto ha sido generado con swagger-codegen, (de la misma manera que el servicio GDD).
El proyecto expone dos endpoints, en la misma URL: coando se corre en localhost la url es:
http://localhost:8081/periodosfaltantes/api

Al llamar a la url, utilizando GET se cumple el requerimiento establecido en el Nivel 3 del desafio 1.
esto es:
- Se llama al servicio GDD. (La url de este servicio se puede configurar en el archivo application.properties)
- Se procesa la respuesta del servicio de GDD y se retornan las fechas faltantes, ademas de las que fueron devueltas por el servicio GDD

Además se puede llamar a la URL utilizando el metodo POST, enviando el listado de fechas como lo retorna GDD. En este caso
el sistema no llamará al servicio GDD sino que procesará las fechas pasadas como parámetro.

La documentacion con swagger del proyecto se pude ver en:

http://127.0.0.1:8081/periodosfaltantes/swagger-ui.html#/



Para compilar y ejecutar el proyecto, se debe ejecutar lo siguiente, en la carpeta raiz del proyecto:
- mvn clean package
- cd target
- java -jar api-desafiouno-1.0.0.jar


Comentarios: 
- Se agregaron algunas dependencias de xmlbind despues de generar el proyecto con swagger codegen, esto se hizo
pues estas bibliotecas fueron eliminadas en Java 11 que es la versión con la que estoy trabajando.
- La url del servicio GDD esta apuntando a http://localhost:8080/periodos/api esto es porque ejecute el servicio GDD en local para
ejecutar las pruebas. Por esta razón tambien cambie el puerto de esta aplicación al 8081.
- Me quedo pendiente hacer validar que las fechas se encuentren ordenadas, y mejorar los mensajes de error cuando se envia una solicitud incorrecta (Bas Request)

