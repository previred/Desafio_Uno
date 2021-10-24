# Generador de Datos Desafío Respuesta Con Fechas Faltanates

Este proyecto entrega la respuesta de la llamada al servicio api Generador de Datos GDD
más las fechas faltantes en el mismo mensaje. 

# Requiere

Este API requiere que esté cooriendo API de Generación de Datos ver en el siguiente link
https://github.com/previred/Generador_Datos_Desafio_Uno


# API

expone un API REST que entrega la siguiente información:

```json
{
  "id": 1,
  "fechaCreacion": "2000-03-01",
  "fechaFin": "2017-09-01",
  "fechas": [
    "2000-05-01",
    "2000-07-01",
    ............
    "2017-05-01",
    "2017-06-01",
    "2017-08-01"
  ],
  "fechasFaltantes": [
    "2000-03-01",
    "2000-04-01",
    "2000-06-01",
    "2000-11-01",
    .............
    "2017-04-01",
    "2017-07-01",
    "2017-09-01"
  ]
}
```

*Nota*: El formato de las fechas es yyyy-MM-dd El servicio entrega 1 periodos, el periodo contiene una fecha inicial una fecha final y una lista fechas.

# Detalle del sistema
Swagger Codegen 2.3.1 (OpenApi 2.0) 
Java 8 
Spring-Boot 1.5.9.RELEASE Maven 3

# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado. Ingresar al directorio ApiPeriodos ejecutar el siguiente comando maven

```bash
mvn package
```
Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\api-periodos-1.0.0.jar
```
*Nota*:
Debe estar disponible el puerto *8181* (no usa 8080 porque necesita de api GDD) en el PC donde se ejecute esta API



# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://localhost:8181/periodos/perdidos/swagger-ui.html#

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8181/periodos/perdidos/api'
```

# Regenerar API a partir de yaml

Las siguientes instrucciones solo son para re-escribir el API si es necesario agregar una nueva funcionalidad a partir del *YAML*.

Bajar SwaggerCodeGen 2.3.1 en la raíz del proyecto, ejecutando el siguiente comando por consola.

```bash
wget http://oss.sonatype.org/content/repositories/releases/io/swagger/swagger-codegen-cli/2.3.1/swagger-codegen-cli-2.3.1.jar -O swagger-codegen-cli.jar
```

Para re-crear el código a partir de la definición del *YAML* de swagger ejecutar el siguiente comando

```bash
java -jar swagger-codegen-cli.jar generate -i ./swagger/periodos-perdidos.yaml -l spring -c ./swagger/config.json -o api-periodos-perdidos --ignore-file-override ./.swagger-codegen-ignore
```

*Nota*:
Esto creara toda la estructura de swagger y re-escribirá todas las clases que no estén declaradas en el archivo *.swagger-codegen-ignore*

