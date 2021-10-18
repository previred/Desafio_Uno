### Problematica a resolver:

### Desafío 1: Periodos perdidos

El desafío consiste en lo siguiente:

-   Existe un servicio REST que llamaremos Generador De Datos o GDD.
    -   El servicio responde con una lista de fechas generadas aleatoriamente. Estas fechas se encuentran en un lapso definidos por dos valores: fechaCreacion y fechaFin.
    -   Cada fecha generada corresponde al primer día de un mes.
    -   La respuesta contienen un máximo de 100 fechas. 
    -   El servicio no entrega todas las fechas dentro del periodo, omite algunas de forma también aleatoria.
-   El objetivo de este ejercicio es que determines cuáles son los periodos que faltan.

### Implementación de la solucion (Nivel 3):

Se construye servicio REST que invoca a servicio GDD y entregar la respuesta en formato JSON con las fechas recibidas y las fechas faltantes

### Ejecución pruebas unitarias:

`mvn clean test`

### Generación reporte de cobertura JaCoCo
`mvn jacoco:report`

### Levantar aplicación 

* Por defecto la API se levanta en el puerto 8080
* Por defecto el servicio GDD lo ubica en `http://localhost:8081/periodos/api`

Se puedn utilizar alguna de las siguientes opciones para levantar la aplicación:

#### - Maven 
`mvn spring-boot:run`

Se puede cambiar la URL del servicio GDD pasandole por parametro la URL al levantar de la siguiente manera:

  `mvn spring-boot:run -Dspring-boot.run.arguments="--url.service.gdd=XYZ"`
  
Donde XYZ es la URL que se quiere utilizar.

  
#### - JAR 

`mvn package & java -jar target/*.jar`

O si se desea cambiar la URL del servcio GDD

`mvn package & java -DurlServiceEdg=XYZ -jar target/desafiouno-0.0.1-SNAPSHOT.jar`

Donde XYZ es la URL que se quiere utilizar.

#### - Docker

Se debe construir la imagen con 

`docker build --tag=desafiouno:latest .`

Y luego levantar el contenedor con la IP del servicio GDD

`docker run -p8080:8080 -e urlServiceEdg=http://10.8.1.9:8081/periodos/api desafiouno:latest`

Donde la IP y Puerto `10.8.1.9:8081`(de ejemplo) deben ser la IP y Puerto donde este ubicado el servicio GDD.

### Swagger con documentación de servicio
`http://localhost:8080/desafiouno/swagger-ui.html`

### Llamadas a servicio que obtiene fechas faltantes de servicio GDD

`curl --location --request GET 'http://localhost:8080/desafiouno/periodos/faltantes'`



##### Autor: Fabián Arias