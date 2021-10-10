
# API Desafio Uno

API Rest desarrollada con Sprint Boot que obtiene una lista de periodos desde una fecha inicial hasta una fecha final y detecta que fechas han sido omitidas.

## Requerimientos

Para ejecutar esta API debe disponer de los siguiente requerimientos:

- [JDK 8 and JRE 8](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
- [Apache Maven 3.8.3](https://maven.apache.org/download.cgi)
- [Servicio API Generator](https://github.com/previred/Generador_Datos_Desafio_Uno) escuchando en http://127.0.0.1:8080/periodos/

## Uso de la API

- Ejecutar API con Maven

    En una terminal y ubicado en la raíz de la API debe ejecutar el siguiente comando:

    ```bat
    mvn  package
    java -jar .\target\api-periodos-1.0.0.jar
    ```

- Uso de la API en un Navegador

    Abrir en un navegador la siguiente dirección

    - [http://localhost:8070/periodos/swagger-ui.html#!/periodos/periodos](http://localhost:8070/periodos/swagger-ui.html#!/periodos/periodos)

## Creación del Código base

Para la creación del código base del servidor se ocupo la librería [https://github.com/swagger-api/swagger-codegen](https://github.com/swagger-api/swagger-codegen).

Para instalar la librería debe ejecutar el siguiente comando:

```bash
wget http://oss.sonatype.org/content/repositories/releases/io/swagger/swagger-codegen-cli/2.3.1/swagger-codegen-cli-2.3.1.jar -O swagger-codegen-cli.jar
```

Para actualizaciones del código base debe ejecutar los siguientes comandos:

API

```bat
java -jar .\swagger-codegen-cli.jar generate -i .\swagger\periodos.yaml -l spring -c .\swagger\config.json -o ApiPeriodos --ignore-file-override .\.swagger-codegen-ignore
```


Cliente para consultar al servicio generador de datos (Sólo se genera para importar el cliente en la API)

```bat
java -jar .\swagger-codegen-cli.jar generate -i .\swagger\periodos.yaml -l java  -o ApiPeriodosClient --library resttemplate  --model-package desafiocuatro.periodos.swagger.codegen.model
```
