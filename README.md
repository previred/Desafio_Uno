# Compilar y ejecutar el proyecto

Para compilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *fechas-faltantes* ejecutar el siguiente comando *maven*

```bash
mvn clean package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar fechas-faltantes.jar
```
*Nota*:
Debe estar disponible el puerto *8080* en el PC donde se ejecute esta API

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://localhost:8080/swagger-ui.html o http://localhost:8080/periodos/fechas-faltantes/apidocs

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/periodos/fechas-faltantes'
```