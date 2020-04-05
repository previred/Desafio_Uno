# Resolución de Desafio Uno - Nivel 3

Este proyecto expone un API REST que entrega la siguiente información:

_id_: identificador
_fechaCreacion_: Fecha de inicio de la secuencia
_fechaFin_: Fecha de fin de la secuencia
_fechas_: Lista de fechas que están en el rango de la fecha que se encuentra entre “fechaCreacion” hasta la fecha “fechaFin”
_fechasFaltantes_: Lista de fechas restantes para completar el periodo comprendido entre "fechaCreacion" y "fechaFin"
Ejemplo.

```json
{
  "id": 6,
  "fechaCreacion": "1968-08-01",
  "fechaFin": "1969-06-01",
  "fechas": ["1968-08-01", "1969-03-01", "1969-05-01"],
  "fechasFaltantes": [
    "1968-09-01",
    "1968-10-01",
    "1968-11-01",
    "1968-12-01",
    "1969-01-01",
    "1969-02-01",
    "1969-04-01",
    "1969-06-01"
  ]
}
```

_Nota_:
El formato de las fechas es yyyy-MM-dd
El servicio entrega 1 periodo, el periodo contiene una fecha inicial una fecha final y una lista fechas.

# Compilar y ejecutar el proyecto

Para compilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio _ApiPeriodos_ ejecutar el siguiente comando _maven_

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio _target_ ejecutar el siguiente comando _java_

```bash
java -jar ./api-periodos-1.0.0.jar
```

_Nota_:
Debe estar disponible el puerto _8080_ en el PC donde se ejecute esta API

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://127.0.0.1:8080/periodos/swagger-ui.html#/

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8080/periodos/api/getAllPeriodsByRandom'
```

# Datos Postulante

_Nombre Completo_: Eduardo Ernesto Bustos Merino
_Correo Electrónico_: ebustosmerino@gmail.com
_Desafio enviado por_: Leonardo Miranda Pavez - Lech Consult
