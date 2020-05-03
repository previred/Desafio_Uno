# Desafio Nivel 3 para previred Felipe Aillon Sanhueza
API Rest para obtener periodos faltantes segun arreglo de fechas.
Se eligió usar python para el servicio para mostrar la ventaja al poder levatar en
distintos contenedores servicios con diferentes lenguajes y ambientes.

## Instalación

Instalar [Docker](https://docs.docker.com/get-docker/) dependiendo del sistema operativo


Instalar [Docker Compose](https://docs.docker.com/compose/install/) dependiendo del sistema operativo

## Ejecución

Ejecutar dentro de la carpeta del proyecto el comando

```bash
docker-compose up
```

Ese comando levantará cada servicio en un contenedor independiente

# Documentación API

La API incluye documentation usando Swagger que estará disponible en http://localhost:5000/api/v1/ui/ .

La Documentación del servicio java estará en http://127.0.0.1:8080/periodos/swagger-ui.html