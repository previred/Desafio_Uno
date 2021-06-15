# Busqueda de Periodos perdidos para servicio GDD

Proyecto expone 3 API REST con la finalidad de probar los servicios por separado o en conjunto

*POST /api/periodos-faltantes*

Este servicio recibe un JSON dispuesto por GDD y lo complementa para devolver el mismo JSON complementado con el campo fechasFaltantes con las fechas que fueron omitidas aleatoriamente al invocar al GDD

Como entrada tendriamos la siguiente estrutura:

```json
{
    "id": 6,
    "fechaCreacion": "1968-01-01",
    "fechaFin": "1968-09-01",
    "fechas": [
      "1968-03-01",
      "1968-05-01",
      "1968-06-01",
      "1971-08-01"]
}
```

Como resultado entrega la siguiente estrutura (datos son de ejemplo):

```json
{
    "id": 6,
    "fechaCreacion": "1968-01-01",
    "fechaFin": "1968-09-01",
    "fechas": [
      "1968-03-01",
      "1968-05-01",
      "1968-06-01",
      "1971-08-01"],
	"fechasFaltantes": [
	  "1968-01-01",
	  "1968-02-01",
	  "1968-04-01",
	  "1968-07-01",
	  "1968-09-01"
	]
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd
El servicio entrega un objeto periodo, el periodo contiene un id, una fecha inicial, una fecha final, una lista de fechas aleatorias entre las fechas inicial y final, y una lista de fechas faltantes que complementan el campo anterior.


*GET /api/periodos-gdd*

Este servicio devuelve un JSON con un rango de fechas y las fechas aleatorias entre ellas entregadas por el sistema GDD, es una invocacion al sistema GDD solo para obtener un JSON de prueba para ejecutar el servicio anterior.

Como resultado entrega la siguiente estrutura (datos son de ejemplo):

```json
{
    "id": 6,
    "fechaCreacion": "1968-01-01",
    "fechaFin": "1968-09-01",
    "fechas": [
      "1968-03-01",
      "1968-05-01",
      "1968-06-01",
      "1971-08-01"]
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd
El servicio entrega un objeto periodo, el periodo contiene un id, una fecha inicial, una fecha final y una lista de fechas aleatorias entre las fechas inicial y final.


*GET /api/periodos-gdd-faltantes*

Este servicio devuelve un JSON con un rango de fechas y las fechas aleatorias entre ellas entregadas por el sistema GDD, a su vez retorna el campo fechasFaltantes con la completitud de las fechas que fueron omitidas aleatoriamente al invocar al GDD

Como resultado entrega la siguiente estrutura (datos son de ejemplo):

```json
{
    "id": 6,
    "fechaCreacion": "1968-01-01",
    "fechaFin": "1968-09-01",
    "fechas": [
      "1968-03-01",
      "1968-05-01",
      "1968-06-01",
      "1971-08-01"],
	"fechasFaltantes": [
	  "1968-01-01",
	  "1968-02-01",
	  "1968-04-01",
	  "1968-07-01",
	  "1968-09-01"
	]
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd
El servicio entrega un objeto periodo, el periodo contiene un id, una fecha inicial, una fecha final, una lista de fechas aleatorias entre las fechas inicial y final, y una lista de fechas faltantes que complementan el campo anterior.


# Detalle de los sistemas

Swagger 2.9.2 (Spring Fox 2)
Java 8
JKuke 1.0.2
Spring-Boot 2.4.0.RELEASE
Maven 3.6.3


# Compilar y ejecutar el proyecto (Ambiente Linux)

# Compilar y ejecutar el proyecto con Docker

Para copilar el proyecto se requiere 
*Java 1.8, 
*Maven 3.6.3 y 
*Docker Engine como parte de Linux.

Para comprobar que los productos requeridos estan instalados y corriendo, ejecute las siguientes instrucciones

```bash
systemctl status docker
** en caso de estar detenido ejecutar **
systemctl start docker

mvn -version
java -version
```

Ingresar al directorio *periodos-faltantes* ejecutar el siguiente comando *maven*

```bash
mvn clean package k8s:build -Pkubernetes
```

Esto generará una imagen en el Docker que se puede visualizar y ejecutar con las siguientes instrucciones

```bash
docker image ls
** Deberia existir una imagen con la siguiente descripcion **
REPOSITORY                      TAG     IMAGE ID            CREATED...
periodos/periodos-faltantes		1.0.0	5722e458d3fe		56 minutes ago...

docker run -p 8080:8080 -e ENDPOINT_GDD=http://192.1.10.22:8080/periodos/api -d periodos/periodos-faltantes:1.0.0

```

**Donde:**
*ENDPOINT_GDD* corresponde a la url donde se encuentre publicado el servicio GDD  
*-p 8080:8080* es el puerto donde se va a exponer el proyecto, en caso que el servicio GDD este ejecutandose en la misma maquina se debe cambiar el puerto de exposicion para que no colisione, ejemplo *-p 8081:8080*

*Nota*:
Debe estar disponible el puerto *8080* o en el que se expuso en el PC donde se ejecuto el Docker

# Compilar y ejecutar el proyecto solo con Java

Para copilar el proyecto se requiere Java y Maven.

Para comprobar que los productos requeridos estan instalados y corriendo, ejecute las siguientes instrucciones

```bash
mvn -version
java -version
```

Ingresar al directorio *periodos-faltantes* ejecutar el siguiente comando *maven*

```bash
mvn clean package
```

Luego de compilar el proyecto ejecutar el siguiente comando *java*

```bash
java -jar target/periodos-faltantes-1.0.0.jar
```
*Nota*:
Debe estar disponible el puerto *8080* o en el PC donde se ejecute esta API

En caso de requerir su ejecucion en otro puerto, para este caso debe descomentar la linea #port: ${APP_PORT:8081} del archivo application.yml, luego volver a ejecutar el proceso de package y ejecución


# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://localhost:8080/swagger-ui.html#/

Para consumir los servicios REST se pueden hacer desde swagger, este tambien generar el comando CURL, en caso que se quiera consumir desde la consola de comando

Ejemplo para el *GET /api/periodos-gdd-faltantes*

```bash
curl -X GET "http://localhost:8080/api/periodos-gdd-faltantes" -H "accept: application/json"
```

*Nota*:
Se ha definido por Alcance del requerimiento omitir la seguridad OAuth 2.0 de los servicios por no contar con un OpenID configurado para la seguridad. 