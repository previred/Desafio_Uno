Se habré el proyecto (yo lo trabaje con IDE IntelliJ), ideal borrar carpeta target, compilar proyecto (pueden ajustar la ruta del Log de manera opcional en el archivo log4j.properties), esto genera una nueva carpeta target, el cual también genera dentro de nuestra carpeta un archivo con extensión .war, este es el proyecto compilado para subir a un servidor tomcat 
<br>Este archivo .war se puede levantar en cualquier servidor tomcat desde la version 8 en adelante.
<br><br>
Importante<br>
Si tiene problemas levantando el ambiente mencionado, deje un ambiente en Amazon con un servidor tomcat 8.5 y la aplicacion cargada en la siguiente url:
<br><br>
Previredprueba1-env.eba-ekhmsjs2.us-east-1.elasticbeanstalk.com/fechas/detalle

<br>
Yo en lo personal utilizo Postman para realizar pruebas REST, dejare el archivo json.collection de postman incluido en el proyecto (carpeta postman) por si lo quieren cargar de forma directa.

<br><br>
***
API REST<br><br>
URL servicio servido local<br>
Tipo PUT: {url local}/fechas/detalle

JSON formato parametros de entrada (body):

Ejemplo:
<pre><code>{
    "fechaCreacion": "1969-03-01",
    "fechaFin": "1970-01-01",
    "fechas": [
      "1969-03-01",
      "1969-05-01",
      "1969-09-01",
      "1970-01-01"]
}
</code></pre>


Salida:

Ejemplo:
<pre><code>{
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
}</code></pre>

