# Desafío 1: Periodos perdidos

Levantar el servicio REST GDD:

```json
git clone https://github.com/previred/Generador_Datos_Desafio_Uno.git
cd Generador_Datos_Desafio_Uno-master/ApiPeriodos
mvn clean install
java -jar target/api-periodos-1.0.0.jar
```

Este proceso iniciará el tomcat embebido. El puerto configurado es 8080. La URL para validar es la siguiente:

```json
http://localhost:8080/periodos
```

A través de Swagger-ui se podrá ver resultado que se genera, éste tendrá un formato parecido a:
    
```json
{
    "id": 6,
    "fechaCreacion": "1969-03-01",
    "fechaFin": "1970-01-01",
    "fechas": [
      "1969-03-01",
      "1969-05-01",
      "1969-09-01",
      "1970-01-01"]
}
```



La solución implementada corresponde al Nivel 3:

"Implementar un nuevo servicio REST. Este servicio REST debe invocar al servicio GDD y entregar la respuesta en formato JSON con las fechas recibidas y las fechas faltantes."

Levantar el servicio REST Api-Periodos-Backend:

```json
git clone https://github.com/previred/Desafio_Uno.git
cd ApiPeriodosBackend
mvn clean install
java -jar target/api-periodos-backend-1.0.0.jar
```

Este proceso iniciará el tomcat embebido. El puerto configurado es 9090. La URL para validar es la siguiente:

```json
http://localhost:9090/periodos-faltantes
```
Los valores del puerto, path y otros pueden ser modificados, solo basta hacerlo en "application.properties"

```json
server.contextPath=/periodos-faltantes
server.port=9090

api.periodos=http://localhost:8080/periodos/api
api.header=Accept
api.header.value=application/json
```

A través de Swagger-ui se podrá ver resultado que se genera, éste tendrá un formato parecido a:

```json
{
    "id": 6,
    "fechaCreacion": "1969-03-01",
    "fechaFin": "1970-01-01",
    "fechas": [
      "1969-03-01",
      "1969-05-01",
      "1969-09-01",
      "1970-01-01"],
    "fechasFaltantes": [
      "1969-04-01",
      "1969-06-01",
      "1969-07-01",
      "1969-08-01",
      "1969-10-01",
      "1969-11-01",
      "1969-12-01"]

}
```

El resultado completo de una ejecución se puede visualizar en el archivo

```json
periodo.json
```
O bien desde la URL

```json
http://localhost:9090/periodos-faltantes/api
```