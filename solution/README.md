Services API
============
Api que entrega servicios de manera RESTful, solo recibe json y devuelve json.

-------------------------------
Servicio
-------------------------------

| Servicios   | Tipo       | Data                    | Definición                                 |
|-------------|------------|-------------------------|--------------------------------------------|
| fix_date    | GET        | json                    | Arregla las fechas de inicio y fin.        |
|             |            |                         | Descarga los datos del GGD.                |
| fix_date    | POST       | json                    | Arregla las fechas de inicio y fin.        |
|             |            |                         | Puede recibir un dato json.                |

-------------------------------
URI
-------------------------------
HOST = http://127.0.0.1:8000/ 
PD: podria variar en la configuración del setting

| Ruta                                                       |
|------------------------------------------------------------|
| services/util/fix_dates/                                   |

Ejemplo: http://127.0.0.1:8000/services/util/fix_dates/

-------------------------------
Requerimientos
-------------------------------
    - Virtualenv
    - Python >~ 3.6

-------------------------------
Paso a paso
-------------------------------
    Instalar virtualenv en ubuntu
    > pip3 install virtualenv

    Ejecutar virtualenv para entorno python3
    > virtualenv -p python3.6 .env

    En el caso que tengas el 3.7
    > virtualenv -p python3.7 .env

    Ahora activamos el entorno virtual
    > source {folder}/.env/bin/activate

    A continuación instalamos los requerimientos para hacer funcionar la aplicación web
    > pip install -r requirements.txt

    Por último encendemos el servidor
    > python manage.py runserver

-------------------------------
Lo último
-------------------------------
    Acceder a la uri completa para utilizar los servicios de la api (http://127.0.0.1:8000/services/util/fix_dates/)
    
    Se puede hacer un pedido GET o POST con Postman.

    Dentro del proyecto se encuentra el archivo de Entrada.json y de Salida.json para pruebas.
