# Desafío Uno - Nivel 3

## Requerimientos

- Java 11 
- Maven 
- Servicio Generador de Datos (GDD) corriendo en localhost:8080

## Compilación y Ejecución

Ejecutar en la raíz del proyecto:

```
mvn package
java -jar .\target\uno-0.0.1-SNAPSHOT.jar

```

## Como probar

Acceder desde el navegador a:

localhost:8081/swagger-ui.html

o bien, a la siguiente url:

http://localhost:8081/api/gdd


## Entrada y salida en formato JSON

Se ubican en la raíz del proyecto los archivos input.json con la entrada y output.json con la salida.
