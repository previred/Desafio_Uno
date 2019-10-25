# Instrucciones de instalación

## Requisitos

-Java 1.8
-Maven 3.5
-API de Periodos funcionando (Descargar el [repositorio] (https://github.com/previred/Generador_Datos_Desafio_Uno) 
y seguir las instrucciones)

## Compilación

En el directorio raìz, compilar con la siguiente instrucción:

`$ mvn clean install`

Una vez compilado, se generarán dos archivos JAR en los directorios `obtener-periodos/target`
y `obtener-periodos-faltantes/target` respectivamente.

## Ejecución

### Si usas Linux, Mac u otros sistema con Unix:

Primero hay que crear el archivo de entrada con las fechas en formato JSON. Para ello debemos
abrir una consola de comandos, ir a la reìz del proyecto y ejecutar:

`$ ./obtener-periodos.sh <nombre_archivo_entrada.json>`

Esta generación de archivo se obtiene mediante el consumo de la API REST 
`http://127.0.0.1:8080/periodos/api`

Ahora bien, para generar el archivo de salida JSON que contiene los periodos faltantes, se debe
ejecutar en la raíz del proyecto:

 `$ ./mi_solucion.sh <nombre_archivo_entrada.json> <nombre_archivo_salida.json>`

### Si usas Windows

Debes configurar la variable de entorno para la ejecución de JAVA.

Abrir una consola DOS y ejecutar en la raíz del proyecto para obtener el archivo de entrada:

`java -jar obtener-periodos\target\obtener-periodos-1.0.jar <archivo_entrada.json>`

Luego, para obtener los archivos de salida con las fechas faltantes hay que ejecutar:

`java -jar obtener-periodos-faltantes\target\obtener-periodos-faltantes-1.0.jar <archivo_entrada.json> <archivo_salida.json>`

### Importante: los archivos generados se encuentran en la carpeta `resultados`

## Ayuda

Si tienes dudas no olvides enviarme un correo a h.arriagada.mendez@gmail.com 