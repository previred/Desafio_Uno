# Desafío 1: Periodos perdidos Por Nelson Villamizar


Este programa corresponde a la opcion Nivel 1:

Es una aplicacion stand alone que recibe como parametro un nombre de archivo con contenido Json,
opcionalmente recibe un nombre de archivo para guardar la salida;
si no hay parámetro de archivo de salida entonces la salida se imprime en la consola.

Ver Archivos archivoEntrada.json(generado http://127.0.0.1:8080/periodos/swagger-ui.html#!/periodos/periodos ) y archivoSalida.json. 

# Detalle de los sistemas

Java 8
Spring-Boot 1.5.9.RELEASE
Maven 3.2.2


# Compilar y ejecutar el proyecto

Para compilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *Desafio_Uno_NV* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* y ejecutar el siguiente comando *java*

```bash
java -jar .\desafio-uno-1.0.0.jar nombre_archivo_entrada
 
```

Ejemplo: Aquí el archivo de entrada es *D:\previred\previred.json*. Se puede poner la ruta completa.

```bash
D:\previred\Desafio_Uno_NV\target>java -jar .\desafio-uno-1.0.0.jar D:\previred\previred.json
{
  "id": 6,
  "fechaCreacion": "1969-03-01",
  "fechaFin": "1970-01-01",
  "fechasFaltantes": [
    "1969-04-01",
    "1969-06-01",
    "1969-07-01",
    "1969-08-01",
    "1969-10-01",
    "1969-11-01",
    "1969-12-01"
 ]
}

D:\previred\Desafio_Uno_NV\target>
```
La salida anterior se puede redireccionar a un archivo. No se mostrará nada en la consola.

Ejemplo:
```bash
D:\previred\Desafio_Uno_NV\target>java -jar .\desafio-uno-1.0.0.jar D:\previred\previred.json > salida.json
```


También se puede ejecutar así:
```bash
java -jar .\desafio-uno-1.0.0.jar nombre_archivo_entrada nombre_archivo_salida
 
```

Aqui el programa crea un archivo de salida y muestra en la consola también el Json generado. Se puede poner la ruta completa.

Ejemplo:
```bash
D:\previred\Desafio_Uno_NV\target>java -jar .\desafio-uno-1.0.0.jar D:\previred\previred.json D:\previred\salida.json
Archivo de entrada:D:\previred\previred.json
Archivo de salida:D:\previred\salida.json
Archivo de entrada leido correctamente
Se identificaron las fechas faltantes
El archivo de salida fue generado correctamente.

{
  "id": 6,
  "fechaCreacion": "1969-03-01",
  "fechaFin": "1970-01-01",
  "fechasFaltantes": [
    "1969-04-01",
    "1969-06-01",
    "1969-07-01",
    "1969-08-01",
    "1969-10-01",
    "1969-11-01",
    "1969-12-01"
 ]
}

D:\previred\Desafio_Uno_NV\target>
```

