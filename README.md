Este proyecto API Rest responde al desafio planteado en el repositorio https://github.com/previred/Desafio_Uno/

## Tecnologias utilizadas

 - Java 1.8
 - Apache Maven 3
 - Spring Framework 5
 - Spring Boot 2.5 (Starter Web y Starter Data JPA)
 - H2 Database 1.4
 - LogBack
 - OpenAPI 3 Specification
 - Swagger UI


## Compilacion y ejecucion del proyecto

Es necesario tener instalado Java 1.8  y Maven version 3.6.3

Para compilar debe ubicarse en la raiz del proyecto y ejecutar

```bash
mvn package
```

Luego ingresar al directorio target y ejecutar

```bash
java -jar .\api-periodos-1.0.0.jar
```

Por defecto la aplicacion se levanta en el puerto 8081, si decide cambiarlo indiquelo en la linea de comando

```bash
java -jar .\api-periodos-1.0.0.jar
```

Para que el servicio responda correctamente debe estar disponible el servicio Generador de Datos, el cual por defecto se consume en la url 

<http://127.0.0.1:8080/periodos/api>

Si desea cambiar este endpoint indiquelo en la linea de comando

```bash
java -jar .\api-periodos-1.0.0.jar
```

## Documentación API

La documentación de la aplicacion queda disponible automaticamente una vez que entra en ejecucion

 - OpenAPI 3 Specification Docs JSON format:

<http://localhost:8081/v3/api-docs>

 - OpenAPI 3 Specification Docs YAML format:

<http://localhost:8081/v3/api-docs.yaml>

 - Swagger UI

<http://localhost:8081/swagger-ui.html>


## Otros...

Para construir el cliente del servicio Denerador De Datos
