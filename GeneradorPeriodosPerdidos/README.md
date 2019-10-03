## Nivel 3:

Implementar un nuevo servicio REST. Este servicio REST debe invocar al servicio GDD y entregar la respuesta en formato JSON con las fechas recibidas y las fechas faltantes.
Ejemplo de la respuesta que debería entregar:

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

INSTRUCCIONES:
-   Para copilar el proyecto se requiere Java y Maven instalado, ademas de un servidor web (Ej Tomcat). Ingresar al directorio GeneradorPeriodosPerdidos ejecutar el siguiente comando maven
```json

mvn package
```
- Una vez ejecutado el comando, dirigirse al directorio target y desplegar el war "GeneradorPeriodosPerdidos-0.0.1-SNAPSHOT.war" en el servidor web.

- Una vez desplegado el war, ejecutar desde un cliente REST (Ej, postman, soapUI, etc) la siguiente URL: http://ipServidor:puerto/GeneradorPeriodosPerdidos-0.0.1-SNAPSHOT/generadorPeriodos/generadorPeriodosRest/obtenerPeriodosFaltantes/

Ej(http://localhost:8080/GeneradorPeriodosPerdidos-0.0.1-SNAPSHOT/generadorPeriodos/generadorPeriodosRest/obtenerPeriodosFaltantes/) 

-** En caso de modificar la url del de la api rest de swagger, esta se puede modificar en el archivo config.properties y volver a compilar (mvn package), de igual manera la ruta por defecto configurada es la indicada en la descripcion del desafio (http://127.0.0.1:8080/periodos/api)


