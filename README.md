# Desafío 1: Periodos perdidos, NIVEL 3.


Datos de Postulante
```bash
Nombre: Claudio Galleguillos Iturrieta 
Correo: claudiogalleguillositurrieta@gmail.com 
Teléfono: +569 8903 0127
Vía: Tech Consult, Leonardo Miranda.
```

INSTALACION:
-   El nuevo servicio cumple con los parámetros de instalación y ejecución del servicio Generador de Datos GDD.
-   Acceder al directorio de ApiPeriodosFaltantes, y ejecutar el siguiente comando:

```bash
mvn clean package
```
-   Luego, de la compilación, ejecutar el siguiente comando que levantará el nuevo servicio y será disponibilizado desde la url adjunta (tener en cuenta que el servicio GDD corre en puerto 8080 y api-periodosfaltantes en puerto 8081):

```bash
java -jar target/api-periodosfaltantes-1.0.0.jar  (MacOSX)
java -jar .\target\api-periodosfaltantes-1.0.0.jar  (Otro)
```
```bash
URL: http://127.0.0.1:8081/periodosfaltantes/
```
-   Por último, activar desde swagger-ui directamente con "Try it out", o bien, por comando:

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8081/periodosfaltantes/periodosFaltantes'
```
