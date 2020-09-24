# API desafio uno Nivel 3 

Este proyecto expone un API REST que invoca al servicio GDD y entrega la respuesta en formato JSON con las fechas recibidas y las fechas faltantes.

# Detalle de los sistemas

Swagger Codegen 2.3.1 (OpenApi 2.0)
Java 8
Spring-Boot 1.5.9.RELEASE
Maven 3


# Compilar y ejecutar el proyecto

Para compilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *ApiPeriodosF* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\api-periodos-1.0.0.jar
```

*Nota*:
Debe estar disponible el puerto *8084* en el PC donde se ejecute esta API
Debe estar disponible la API GDD bajo el puerto *8080*.


# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://127.0.0.1:8084/periodos/swagger-ui.html#/

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8084/periodos/api'
```
