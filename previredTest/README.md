# Previred Test

* Este componente se desarrolló con:

- Spring Boot 2
- JDK 8
- Lombok
- Java bean validation
- WireMock
- Swagger 3
- Junit
- Mockito

* las clases java dentro de la aplicación están con una sintaxis y formato en lenguaje inglés para conservar el estándar global. Exceptuando la clase de respuesta del servicio GDD y javadoc

* Se utilizó la libreria lombok como anotaciones para generar getters setters y constructores de las entidades.

* Se abarcaron practicas SOLID como responsabilidad única, segregación de interfaces, intentando llevar a cabo una alta cohesión y bajo acoplamiento para cada componente.

* Se utilizó Java Bean Validator, lo que permite validar los datos de parametros a través de anotaciones. Y la respuesta de los servicios

* También se implementó un handler para el manejo de excepciones

### Enfoque de Domain Driven Desing.

Donde la capa interna mas baja es el paquete domain, esta capa se encuentra aislada como core del componente.
La siguiente capa en el esquema es la de presentación, donde puede interactuar con objetos del domain.
La ultima es la infraestructura, en donde se tratan clases de características que podrían ser modificadas o desacopladas por otras implementaciones de forma mas sencilla.

Orden de los paquetes:
- Presentation : La exposición de nuestro microservicio, Objetos de request y response
- Infraestructure : Infraestructura, configuraciones, logs, y la capa de conexion cliente con el servicio GDD
- Domain : Objeto de negocio, interfaces y servicios.

### Cliente GDD y Wiremock

Para tratar de forma mas simple la llamada al servicio se hizo un mock de este, utilizando la libre wiremock que funciona como un servidor embebido en las funciones de spring boot. Se le asignó el archivo json (Formato indicado, ubicado en la carpeta resources/stubs del componente) de respuesta que se indicó y además la configuración de endpoint de llamada a este servicio mock.

El cliente GDD se trató como un paquete desacoplado donde trata todas las características moldeables de la llamada

Además se implementaron excepciones, donde se puede verificar el caso positivo de respuesta (200), caso cuando hay error en llamada al cliente (400) y caso donde hay problema con error interno del servidor (500). Todos estos casos con las excepciones almacenadas en la capa de infraestructura y además el chequeo de casos a través de pruebas unitarias

### Test

* La app se puede ejecutar en el localhost en el puerto 8080
* Se agregaron test unitario con la librería de Junit y Mockito.
* Se agregó la librería Mockmvc para mejores test relacionados con los endpoints, controladores y las casuísticas que se puedan dar en validaciones de request.
* Se agregaron test de cobertura con Jacoco, para aumentar la confiabilidad de las pruebas y este se aprobó con un 81% de casos y cobertura de código ejecutada. Además se puede ver el reporte generado en la carpeta target/jacoco-report, luego de la compilación.
* Se habilitan pruebas al endpoint en forma local a través de la llamada con la libreria Curl ( O colocar url en el navegador, ya que es solo un método con verbo Get)
```
curl --location --request GET 'http://localhost:8080/v1/date_management'
```

### Ejecucion local

Limpiar e instalar dependencias de Spring
```
mvn clean install
```
Ejecutar el componente de Spring
```
mvn spring-boot:run
```
Los test unitarios y de integración de servicios, se pueden probar de forma local e independiente con el comando:
```
mvn clean test
```
Generar el informe de cobertura de forma unitaria
```
mvn clean verify
```

# Swagger ambiente local:

* Además se habilitó la interfaz de swagger para la documentación de todos los métodos y se puede acceder a través de la siguiente url:
```
http://localhost:8080/swagger-ui.html#/
```