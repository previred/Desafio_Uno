# Solución Desafio 1 

El JSON de salida de ejemplo esta en el directorio *ms-previred-periodosperdidos* , nombre: SalidaPeriodosPerdidos.json .
El servidor de API debe estar disponible en el puerto *8080* y responder al endpoint http://127.0.0.1:8080/periodos/api

# Compilar y ejecutar el proyecto

Para compilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *ms-previred-periodosperdidos* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\ms-previred-periodosperdidos-0.0.1.jar
```
*Nota*:
Debe estar disponible el puerto *8081* en el PC donde se ejecute esta API


# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://127.0.0.1:8081/swagger-ui.html#/Periodos/PeriodosPerdidos/

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8081/v1/peridodosperdidos/'
```
