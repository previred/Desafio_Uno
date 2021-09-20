Aplicación api-desafio1

    El aplicativo fue desarrollado con la version 8_131 del JRE y JDK Oracle, el cual debe estar instalado en el equipo como tambien configurado en el IDE como Runtime

  * Alternativa 1 para ejecutar el API Desafio1

    - Utilizar IDE Eclipse enterprise Java and Web Developers 03-2021 (4.19.0)
    - Desde el explorador de proyectos, seleccionar la opción IMPORT desde el botion derecho del raton
    - Seleccionar la opcion Proyecto Existe del tipo Maven
    - Seleccionar la carpeta en donde se encuentra el proyecto y luego presionar el boton finish
    - una ver cargado el proyecto en el explorador de proyectos pararse con el raton sobre el nombre del proyecto api-desafio1 y con el boton derecho del rato seleccionar RUN AS y Spring Boot
    - Una vez desplegado o arrancado el servicio utilizar la siguiente URL para el consumo 
    
      URL para ser utilizada por un cliente POSTMAN o REST
      http://localhost:8080/desafio/generar 
      
      Se debe especificar las siguientes cabeceras para poder realizar la petición
      content-type: application/json
      accept:application/json

  * Alternativa 2 para ejecutar el API desafio1
  
     Por consola de comandos, se debe ejecutar el JAR del proyecto con el siguiente comando: java -jar api-desafio1-0.0.1-SNAPSHOT.jar