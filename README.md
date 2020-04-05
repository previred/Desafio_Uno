# Generador Datos Desafio

ahora ambos proyectos aceptan entrada de datos a nivel de parametros

formato de parametros:
	inicio: 2018-08-01
	fin: 	2019-07-01

# Detalle de los sistemas

Swagger Codegen 2.3.1 (OpenApi 2.0)
Java 8
Spring-Boot 1.5.11.RELEASE y 2.2.6.RELEASE
Maven 3


# Compilar y ejecutar el proyecto ApiPeriodos

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *ApiPeriodos* ejecutar el siguiente comando *maven*

```bash
mvn clean compile package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\api-periodos-1.0.0.jar
```
*Nota*:
Debe estar disponible el puerto *8081* en el PC donde se ejecute esta API

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://127.0.0.1:8081/periodos/swagger-ui.html#/

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8081/periodos/api?inicio=2016-08-01&fin=2017-07-01'
```

# Compilar y ejecutar el proyecto ApiPeriodosAgrega

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *ApiPeriodosAgrega* ejecutar el siguiente comando *maven*

```bash
mvn clean compile package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\api-periodos-completa-1.0.0.jar
```
*Nota*:
Debe estar disponible el puerto *8083* en el PC donde se ejecute esta API

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://localhost:8083/periodosagregado/swagger-ui.html#

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8083/periodosagregado/api?inicio=2016-08-01&fin=2017-07-01'
```
