# Respuesta Desafío 1
El proyecto requiere Python>=3.6.5 y Pipenv>=2018.11.26

- Instalar proyecto via Pipenv
`pipenv install`

- Activar entorno
`pipenv shell`

- Correr migraciones
`python desafio/manage.py migrate`

- Levantar servidor
`python manage.py runserver`

- Acceder a la API en cualquiera de los siguientes medios
    - Browser
    `http://127.0.0.1:8000/api/GDD`
    - Curl
    `curl -X GET http://127.0.0.1:8000/api/GDD`
    - Swagger
    `http://127.0.0.1:8000/swagger/`
