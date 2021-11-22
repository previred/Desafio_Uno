# Desafio nro 1

El siguiente programa se construyó con [Spring-tool-suite][df1].

Para compilar y generar el .jar:

```sh
importar proyecto dentro de idle (STS o Eclipse) 
click derecho sobre el proyecto
seleccionar Run As
seleccionmar Maven Build
en el input goals escribir: clean package
hacer click en run
el archivo jar generado se encontrará en la carpeta target dentro de la carpeta del proyecto
```
Se incluyen también 2 archivos json que representan la entrada y salida de datos.

** jar y archivos deben estar en la misma ruta

Para ejecutar el jar usar el comando:

Si se decide compilar, el .jar queda en 

```sh
mover archivos entrada_datos.json y salida_datos.json a la ruta donde esta el jar  (target si se compila desde un idle)
 <ruta_proyecto>/target/ java -jar desafio-0.0.1-SNAPSHOT.jar entrada_datos.json salida_datos.json
```

 
 Se incluye un jar pre compilado por cualquier eventualidad.
 
 ```sh
 verificar que esten los archivos entrada_datos.json y salida_datos.json en la raiz del proyecto
 <ruta_proyecto>/ java -jar desafio-0.0.1-SNAPSHOT.jar entrada_datos.json salida_datos.json
```
 
 [//]:  # (This may be the most platform independent comment)
  [df1]: <https://spring.io/tools>
 



