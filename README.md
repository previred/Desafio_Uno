# Instalación y uso del Microservicio

## Contexto de la Solución

Primero mencionar que opté por la solución nivel 3, ya que me parecio el contexto mas realista donde se  podria dar una problematica similar.

Se generó un microservicio spring-boot que expone un endpoint REST, este endpoint es de tipo GET, no recibe parametros de entrada(se puede revisar el swagger si se desea).

Probando el servicio GDD, me di cuenta que genera aleatoreamente fechas para poder trabajarlas, por lo que decidí levantarlo y llamarlo a través de Feign desde el microservicio que desarrolle para la solución.



Como punto inicial debemos instalar en nuestra maquina local lombok

## Instalación lombok

Ir a la sección de descargas de la paágina de lombok [lombol-project](https://projectlombok.org/setup/overview).

Descargar lombok.jar

Ejecutar Lombok.jar  como en el siguiente ejemplo.

```bash
java -jar lombok.jar
```

La interfaz te pedirá examanar la ubicación de tu IDE.

Luego le das a Install.

## Instalación servicio GDD 

Se adjunta en el proyecto  el componente api-periodos-1.0.0.jar

Se obtubo desde su repositorio:
[github - desafio uno](https://github.com/previred/Generador_Datos_Desafio_Uno).

En mi local no funcionó tal como esta. Agregué la dependencia **jaxb-impl**. Por ellos envio el ejecutable del generador como parte del entregable.

        <dependency>
            <groupId>com.sun.xml.bind</groupId>
            <artifactId>jaxb-impl</artifactId>
            <version>2.3.3</version>
        </dependency>


Ejecutar api-periodos-1.0.0.jar  como en el siguiente ejemplo.

```bash
java -jar api-periodos-1.0.0.jar
```

Además modifique el puerto donde levanta el generador al puerto **9191**

## Microservicio desafio-fechas


### Requisitos de desarrollo

- Maven
- STS4
- Java 11

### Librerias Importantes

- Lombok: para Logs y construcción de objetos
- Feign: para comunicación entre servicio GDD y MS desafio-fechas
- Swagger: para documentación de endpoints

### Compilación y Arranque

La solución se desarrollo sobre STS4, por lo que daré indicaciones para dicho IDE.

* Importar código Fuente

* Ajustar el puerto en el que se desea levantar el microservicio en archivo **application.properties**, por defecto viene configurado en el puerto **8585** 

* Una vez finalizado el import actualizar maven dando click derecho en el proyecto y seleccionando **Maven -> update Project... -> OK**

* Para instalar y compilar el proyecto hacer click derecho sobre el proyecto y seleccionar **Run As -> maven install**

* Una vez finalizado el Install arrancamos el proyecto dando click derecho sobre el proyecto y seleccionando **Run As -> Spring Boot App**



### Verificar arranque

Se puede verificar que el servicio esta corriendo en la ruta de su swagger:
- Swagger-ui : [http://localhost:8585/swagger-ui.html](http://localhost:8585/swagger-ui.html)
- Api-docs : [http://localhost:8585/v2/api-docs](http://localhost:8585/v2/api-docs)


### Prueba del servicio

prueba en consola

**curl**
```bash
curl -X GET "http://localhost:8585/periodos/obtenerPeriodosPerdidos" -H "accept: application/json"
```
Prueba en navegador

**URL**
```bash
http://localhost:8585/periodos/obtenerPeriodosPerdidos
```


