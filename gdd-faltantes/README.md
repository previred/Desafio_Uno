# Generador de datos faltantes de Desafío 1 (Previred)

Este desarrollo implementa un servicio RESTful para resolver el Desafío 1: Períodos perdidos. Para esto, el servicio
invoca el servicio GDD para recuperar los periodos generados, los analiza, crea los periodos faltantes y los retorna en
una estructura JSON similar a la respuesta que retorna el servicio GDD, pero le concatena una lista con los periodos
faltantes.

El servicio **/periodos-faltantes** de la API utiliza método GET para retornar los siguientes datos:

* _id_: Identificador.
* _fechaCreacion_: Fecha de inicio de la secuencia.
* _fechaFin_: Fecha de fin de la secuencia.
* _fechas_: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha
  “fechaFin”.
* _fechasFaltantes_: Lista fechas faltantes de la lista *fechas* entre las fechas “fechaCreacion” y “fechaFin”.

Ejemplo:
```json
{
  "id": 6,
  "fechaCreacion": "1969-03-01",
  "fechaFin": "1970-01-01",
  "fechas": [
    "1969-03-01",
    "1969-05-01",
    "1969-09-01",
    "1970-01-01"
  ],
  "fechasFaltantes": [
    "1969-04-01",
    "1969-06-01",
    "1969-07-01",
    "1969-08-01",
    "1969-10-01",
    "1969-11-01",
    "1969-12-01"
  ]
}
```

_Nota_:
El formato de las fechas es yyyy-MM-dd.

# Detalle de los sistemas
Java 8 Spring Boot 2.5.4 Maven 3

springdoc-openapi 1.5.10 (OpeAPI 3)

# Compilar y ejecutar el proyecto

La compilación y empaquetamiento de la API RESTful requiere de Java 8 y Maven 3. Se debe ingresar al directorio
de trabajo (**gdd-faltantes**) y ejecutar el siguiente comando:

```bash
mvn package
```

El comando anterior genera un paquete tipo _jar_ en el directorio _taget_ dentro del directorio de trabajo. Este
archivo _jar_ se puede copiar en otro directorio o ejecutar desde el directorio _target_ con el siguiente
comando:

```bash
java -jar gdd-faltantes-0.0.1.jar
```

La ejecución usará la configuración por defecto. Si se requiere cambiar parámetros de configuración, se debe
copiar el archivo _jar_ a otro directorio que contenga el directorio _config_ con un archivo _application.yml_
que contiene la configuración del servicio, la url de la API que genera los periodos y la ruta de acceso de
la documentación OpenAPI 3.

* Ejecución con archivo de configuración:
```bash
java -jar gdd-faltantes-0.0.1.jar --spring.config.location=config/
```
* Archivo de configuración _./config/application.yml_:
```yaml
server:
  port: 8081
springdoc:
  api-docs:
    path: /openapi
url-api-periodos: http://127.0.0.1:8080/periodos/api
```

*Notas*:
* La configuración por defecto utiliza el puerto 8081, pero se puede cambiar a través del archivo de
configuración.
* Se debe tener en ejecución el servicio GDD en el puerto 8080, por ello el puerto por defecto es 8081.

# Visualizar la documentación y consumir la API

La documentación OpenAPI, luego de iniciar el servicio, quedará disponible en la URL:
http://127.0.0.1:8081/swagger-ui.html

Para consumir el servicio se debe invocar la siguiente URL:
```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8081/periodos-faltantes'
```
