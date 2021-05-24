## Desafío Uno - Periodos Perdidos

Este proyecto API Rest responde al desafío planteado en el repositorio:

<https://github.com/previred/Desafio_Uno/>

## Tecnologías utilizadas

 - Java 1.8
 - Apache Maven 3
 - Spring Framework 5
 - Spring Boot 2.5 (Starter Web y Starter Data JPA)
 - H2 Database 1.4
 - SLF4J-LogBack
 - OpenAPI 3 Specification
 - Swagger UI


## Compilación y ejecución del proyecto

Es necesario tener instalado Java 1.8  y Maven versión 3.6.3.

Para compilar debe ubicarse en la raíz del proyecto y ejecutar:

```bash
mvn package
```

Luego ingresar al directorio target y ejecutar:

```bash
java -jar .\api-periodos-1.0.0.jar
```

Por defecto la aplicación se levanta en el puerto 8081, si decide cambiarlo indíquelo en la línea de comando:

```bash
java -jar .\api-periodos-1.0.0.jar
```

Para que el servicio responda correctamente debe estar disponible el servicio Generador de Datos, el cual por defecto se consume en la url:

<http://127.0.0.1:8080/periodos/api>

Si desea cambiar este endpoint indíquelo en la línea de comando:

```bash
java -jar .\api-periodos-1.0.0.jar
```

## Consumir el servicio API Rest

Desde Linux o Windows (PowerShell) ejecutar:

```bash
curl -X GET 'http://127.0.0.1:8080/periodos/api'
```

## Documentación API

La documentación de la aplicación queda disponible automáticamente una vez que entra en ejecución.

 - OpenAPI 3 Specification Docs formato JSON:

<http://localhost:8081/v3/api-docs>

 - OpenAPI 3 Specification Docs formato YAML:

<http://localhost:8081/v3/api-docs.yaml>

 - Swagger UI:

<http://localhost:8081/swagger-ui.html>


## Otros...

Para construir el cliente del servicio Generador De Datos se evaluó la opción de generar el jar cliente:

```bash
java -jar swagger-codegen-cli-2.4.9.jar generate -i http://127.0.0.1:8080/periodos/api-docs -l java --group-id com.previred.periodos --artifact-id api-periodos-client --artifact-version 1.0.0 -o api-periodos-client/1.0.0
```

Lo que significa tener que agregarlo a nuestro repositorio local (y eventualmente a Nexus) para poder incluirlo como dependencia en el proyecto:

```bash
mvn install:install-file -Dfile=./rest-clients/api-periodos-client/1.0.0/api-periodos-client-1.0.0.jar -DgroupId=com.previred.periodos -DartifactId=api-periodos-client -Dversion=1.0.0 -Dpackaging=jar
```

Pero se optó simplemente por traer el modelo directamente, por lo ligero y flexible que resulta:

```bash
java -jar swagger-codegen-cli-2.4.9.jar generate -i http://127.0.0.1:8080/periodos/api-docs -l java -Dmodels -DmodelTests=false -DmodelDocs=false -o api-periodos/1.0.0
```

Para reducir la complejidad del proyecto la persistencia en la DB H2 no se implementó en un componente Repository (se marcó con un TODO).
