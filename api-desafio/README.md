# Desafío 1 de Previred
Esta solución al Desafío 1 de Previred está construida con Spring Boot y usa Apache Camel para su API REST.

Opté por elegir la solución Nivel 3



# Compilación 

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *api-desafio* ejecutar el siguiente comando *maven*

```bash
mvn package
```

# ejecutar el Jar del proyecto
Al término de la compilación del proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*.

```bash
java -jar Periodos-Perdidos.jar
```

*Nota*:
Debe estar disponible el puerto *9091* en el PC donde se ejecute esta API
o antes de compilar puede cambiar el puerto del servidor en el archivo *application.properties* en los fuentes
cambiar el valor de la propiedad *server.port*

*LOG*:
Una vez ejecutado el Jar del proyecto se va a Generar una carpeta para el registro de Logs, la carpeta es *logs-app* y dentro tendrá el archivo *log-apidesafio.log*


## Swagger

http://127.0.0.1:9091/swagger/index.html

## Consumir API

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:9091/api/periodosperdidos'
```


## Archivos con la entrada y con la salida en formato JSON.

Los archivos estan en la carpeta *docs* en la raiz del proyecto.
