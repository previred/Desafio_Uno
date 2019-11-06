# Detalle de los sistemas

Swagger 2.7.0
Java 8
Spring-Boot 2.1.8.RELEASE
Maven 3


# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *ApiPeriodos* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar uno de los siguientes comandos *java*

```bash cuando el servicio CDD esta ejecutado en la siguiente ruta "http://127.0.0.1:8080"
java -jar ./previredTest-1.0.0.jar
```
```bash cuando el servicio CDD cambia la url
java -jar -Durl="http://127.0.0.1:9090" ./previredTest-1.0.0.jar
```
*Nota*:
Debe estar disponible el puerto *8081* en el PC donde se ejecute esta API

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://127.0.0.1:8081/swagger-ui.html

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8081/fechas'
```

