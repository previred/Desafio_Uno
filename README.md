Desafío 1: Periodos perdidos

Solución: Nivel 1 -> El programa se debe ejecutar de la siguiente manera: $ mi_solucion < nombre_archivo_entrada > nombre_archivo_salida

Detalles de las tecnologías

Java 11 (jre) sin Spring Boot y maven 3.6

Compilar y ejecutar el proyecto

Para compilar el proyecto se requiere Java y Maven instalado. Ingresar al directorio /Desafio1  ejecutar el siguiente comando maven:

$ mvn package

Luego de compilar el proyecto ingresar al directorio target ejecutar el siguiente comando java

$ java -jar Desafio1-1.0-SNAPSHOT-jar-with-dependencies.jar < input_file.json > output_file.json

donde:

input_file.json -> archivo de entrada, desde servicio GDD
input_file.json -> archivo de salida desde el jar-generado

Notas adicionales:

