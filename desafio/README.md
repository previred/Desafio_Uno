Respuesta desafío 2 y 3 previred
Desafío 3
Este proyecto expone un API REST que entrega la solución al desafio Previred, consume el servicio http://127.0.0.1:8081/periodos/api que entrega una secuencia de fechas:

id: identificador fechaCreacion: Fecha de inicio de la secuencia fechaFin: Fecha de fin de la secuencia fechas: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin” Ejemplo.

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

Desafío 2
Simultaneamente, se genera el archivo Resultado.txt en el classpath del proyecto con el JSON que responde el servicio. 


Detalle de los sistemas

Java 8 Spring-Boot 2.2.0.RELEASE Maven 4

Para copilar el proyecto se requiere Java y Maven instalado. Ingresar al directorio desafio ejecutar el siguiente comando maven

mvn package

Luego de compilar el proyecto ingresar al directorio target ejecutar el siguiente comando java

java -jar .\desafio-0.0.1.jar

Nota: Debe estar disponible el puerto 8080 en el PC donde se ejecute esta API
Nota: Se adjunto el archivo resultado con el json que contiene la entrada y la salida.
