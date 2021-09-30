# Solucion encontrar fechas faltantes de GDD

Tomé la decisión de realizar el desafío de nivel 3, para ello realicé la construcción de un servicio API Rest que hace consumo del servicio disponibilizado por ustedes llamado “GDD”, mi aplicación realiza un checkeo de las fechas entregadas por GDD para así determinar cuáles fechas están faltando entre la fecha Iniciar y Final.

DETALLE RESPUESTA:

{
  "id": (identificador unico del consumo de GDD),
  "fechaCreacion": (corresponde a la fecha inicial de la secuencia a procesar),
  "fechaFin": (corresponde a la fecha final de la secuencia de procesar),
  "fechas": (corresponde al grupo de fechas que se encuentran entre "fechaCreacion" y "fechaFin"),
  "fechasFaltantes": (corresponde a las fechas faltantes del grupo anterior, estas fechas son obtenidas por la nueva API creada)
}

Compilar:
Este proyecto requiere que tengas instalado Java y Maven.
Dentro de la carpeta "PreviRedFechasFaltantes" se debe ejecutar el siguiente comando Maven.

mvn clean compile package

Luego mediante linea de comando debes entrar al directorio target (Desafio_Uno\PreviRedFechasFaltantes\target) y ejecutar el siguiente comando.

java -jar .\Fechas-Faltantes-Api.jar


Prerrequisito:
Debe estar disponible el puerto 8082 para poder utilizar la nueva API.
Debe estar ejecutandose Generador Datos Desafio "GDD" en el puerto 8080.


CURL:
curl -X GET "http://localhost:8082/periodos/obtFaltantes" -H "accept: */*"


SWAGGER:
http://localhost:8085/swagger-ui/


Detalle de API:

-springfox swagger
-spring-Boot 2.5.0
-JDK 8
-Maven 3
