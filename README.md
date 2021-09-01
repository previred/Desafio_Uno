#Solución propuesta para el Desafio 3

Esta solución contempla lo siguiente para probar el funcionamiento:

Configurar la URL del servicio servicio GDD:
- archivo application.properties
	- configurar el key *"endpoint.service.gdd"*

El proyecto entregado corresponde a uno del tipo spring boot.

Ejecutar mediante la consola de comandos
#./mvnw spring-boot:run

Pueden ver la documentación de la API en la siguiente URL:
#http://localhost:9091/periodosfaltantes/v2/api-docs

El servicio lo ejecutando mediante metodo GET ejecutando la siguiente URL:
#http://localhost:9091/periodosfaltantes/api

El servicio no necesita datos de entrada, ya que el se comunica con el servicio de GDD.

y la salida es un JSON con lo consultado al servicio de GDD más la lista de periodos faltantes:

Ejemplo de respuesta:
```json
{
    "id": 1,
    "fechaCreacion": "1988-11-01",
    "fechaFin": "1989-10-01",
    "fechas": [
        "1988-11-01",
        "1989-02-01",
        "1989-07-01"
    ],
    "fechasFaltantes": [
        "1988-12-01",
        "1989-01-01",
        "1989-03-01",
        "1989-04-01",
        "1989-05-01",
        "1989-06-01",
        "1989-08-01",
        "1989-09-01",
        "1989-10-01"]

}
```

- Por ultimo en el detalle del commit debes indicar los siguientes datos
   - Nombre Completo.
   - Correo Electrónico.
   - Vía por la que te entérate del desafío. Estas pueden ser: Empresa de outsourcing (indicar cuál), twitter, LinkedIn, etc.


