# Challenge

Este proyecto contiene un API REST que consume la API de GDD(Generador Datos Desafio) y entrega la siguiente información.

id: identificador fechaCreacion: Fecha de inicio de la secuencia fechaFin: Fecha de fin de la secuencia fechas: Lista de fechas que están en el rango de la fecha generadas por el GDD fechasFaltantes: Lista de fechas dentro del rango de fechas que no se encuentran en las generadas.

## Detalles del sistema

Java 8, Spring-Boot 2.2.0, springfox-swagger2 2.9.2, Maven 4

## Compilar y ejecutar el proyecto
Para copilar el proyecto se requiere Java y Maven instalado. Ingresar al directorio ApiPeriodos ejecutar el siguiente comando maven.

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio target ejecutar el siguiente comando java

```bash
java -jar .\challenge-0.0.1.jar
```

Nota: Debe estar disponible el puerto 8081 en el PC donde se ejecute esta API

## Visualizar Documentación y consumir la API
La documentación swagger del API (una vez que se levanta el API) queda disponible en
[http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8081/api'
```
 ## Datos personales
 Nombre: Anthony de Jesus Beltran Reyes
 Email: beltrananthony92@gmail.com
 Contacto: lmp.techconsult@gmail.com
 