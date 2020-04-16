# Solución desafio uno

Esta es mi implementación para el problema propuesto

* Nombre Completo: **Leonardo Andres Silva Bustos**
* Email: **leosilvabustos@gmail.com**

# Detalle de los sistemas

Java 8
Spring-Boot 2.2.6.RELEASE
Springfox-Swagger2 2.5.0 (OpenApi 2.0)
Maven 3


# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *mi-solucion* ejecutar el siguiente comando *maven*

```bash
mvn package
```
## Configuraciones
La aplicación tiene variables que pueden ser configuradas previa a la compilación en el archivo de properties o como variables de entorno posterior a la compilación

* **GDD_ENDPOINT** Endpoint para consumir el servicio GDD por defecto *http://127.0.0.1:8080/periodos/api*
* **GDD_TIMEOUT** Tiempo limite para la consulta al servicio GDD
* **RESULT_FILE** Nombre del archivo de resultado para el nivel 2

*Nota*:
Se asume que el servicio GDD es accesible desde el host donde se ejecuta la aplicación

## Solución nivel 1

```bash
java -jar target/mi-solucion-1.0.0.jar --mi-solucion --entrada=/ruta/al/archivo/de/entrada.json --salida=/ruta/del/archivo/de/salida.json
```

## Solución nivel 2

Genera un archivo de salida, por defecto en la ruta de ejecucion con el nombre de resultado.txt

```bash
java -jar target/mi-solucion-1.0.0.jar --mi-solucion
```


## Solución nivel 3

```bash
java -jar target/mi-solucion-1.0.0.jar
```

Una vez que está corriendo la aplicación se debe ejecutar una llamda http

```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8181/mi-solucion'
```


*Nota*:
Debe estar disponible el puerto *8181* en el PC donde se ejecute esta API

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://localhost:8181/swagger-ui.html

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8181/mi-solucion'
```
