#previred-test

## Instalación

Para compilar y ejecutar el servicio previred-test, se deben cumplir los siguientes requisitos:
- Tener instalado Java Runtime versión 1.8 o superior.
- Tener instalado Maven 

Una vez verificados estos requisitos, se debe ir al directorio del proyecto (se va a definir como <GDD_HOME> de aquí en adelante).
Ya estando ubicados en el directorio, se puede ejecutar de la siguiente forma:
   
- A través de la línea de comando, escribir el siguiente comando: <GDD_HOME>mvn clean package spring-boot:repackage
- Una vez ejecutado el comando, el cual compilará y generará un jar con dependencias embebidas, entrar al subdirectorio target/
- En el subdirectorio se encontrará un archivo llamado gdd-service-0.0.1-SNAPSHOT.jar; este archivo empaqueta todas las dependencias y clases ya compiladas, por lo que se puede ejecutar en cualquier directorio o ambiente con soporte para Java 1.8.
- Una vez copiado el archivo mencionado al directorio que el usuario quiera, ejecutar:
```bash
java -Dserver.port=<port> -jar gdd-service-0.0.1-SNAPSHOT.jar
```
donde <port> es un número de puerto que Ud. quiera especificar. Si no lo especifica, se asumirá el puerto 8080 como puerto en que se levantará el servicio.

- Una vez levantado el servicio, se debe dirigir a la sig. URL: http://<host>:<port>/swagger-ui.html. En este sitio se mostrará el catálogo de servicios, como invocarlos y el contrato de dichos servicios; si quiere probar el servicio desde este sitio,
puede hacerlo con el botón "Try it out".
- Si quiere invocar los servicios por una utilidad como Curl, puede hacerlo de la sig. forma:
    curl -X POST "http://<host>:<port>/rest/gdd" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"fechaCreacion\": \"2018-01-01\", \"fechaFin\": \"2021-01-01\", \"fechas\": [ \"2021-05-01\", \"2021-02-01\" ], \"id\": 0}"





