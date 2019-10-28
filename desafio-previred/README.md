# Desafío Previred

Spring Boot Server 

Swagger


## Detalle de instalación y compilación

Esta aplicación fue desarrollada para el Desafío de Previred el cual implemente la solución Nivel 3

Pasos para instalación y compilación

**Levantar servicio REST GDD**
1. Para compilar servicio REST GDD al directorio ApiPeriodos y ejecutar el siguiente comando
    
    `mvn package`
  
2. Ingresar a directorio target y ejecutar el siguiente comando

    `java -jar .\api-periodos-1.0.0.jar` 
    
    Nota: Esta API se levantará en el puerto 8080 y debe estar disponible

**Levantar servicio REST Desafio Previred**

1. Para compilar el nuevo servicio desarrollado para este Desafío se deberá ingresar a la carpeta "desafio-previred" y 
ejecutar el siguiente comando

    `mvn package`

2. Luego ingresar a la carpeta "target" y ejecutar el comando:

    `java -jar .\desafio-previred-1.0.0.jar`
    
    Nota: La API está configurada para el puerto 9090 (si desea cambiar el puerto ir a src/main/resources y en el 
     archivo application.properties cambiar valor del puerto desde el parámetro "server.port")

3. Ejecutar API desde Postman u otra aplicación similar agregando los siguiente parámetros:

    Headers: 'Accept: application/json'
    Method: 'GET'
    URL: http://127.0.0.1:9090/desafio/api
    
    o desde `curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:9090/desafio/api'`
    
##Visualizar Documentación con Swagger

Ingresar a la siguiente url: http://127.0.0.1:9090/desafio/swagger-ui.html#/