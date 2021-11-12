# Implementacion de Desafio 1 de Previred

Este proyecto consiste en un script de Python que consume un archivo de entrada de formato JSON que contiene una lista de fechas generadas aleatoriamente en un lapso definido por dos valores: fechaCreacion y fechaFin. El archivo de entrada contiene la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”

Ejemplo.
```json
{
    "id": 6,
    "fechaCreacion": "1968-08-01",
    "fechaFin": "1971-06-01",
    "fechas": [
      "1969-03-01",
      "1969-05-01",
      "1969-09-01",
      "1971-05-01"]
}
```

El script de Python entrega, como respuesta, un archivo de salida de formato JSON con la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechasFaltantes*: Lista de fechas que no aparezcan en la lista de fechas de entrada, aunque están entre la fecha “fechaCreacion” y la fecha “fechaFin”

Ejemplo.
```json
{
    "id": 6,
    "fechaCreacion": "1968-08-01",
    "fechaFin": "1971-06-01",
    "fechasFaltantes": [
        "1968-10-01",
        "1968-09-01",
        "1971-05-01"
    ]
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd

# Detalle de los sistemas

Python 3.7 con los siguientes packages instalados:
json
pandas

# Ejecutar el proyecto
El proyecto consiste en un script de Python, y así no necesita compilación. Se requiere instalados Python y los packages ya mencionados. 

Ingresar al directorio que contiene el script *desafio.py* y ejecutar el siguiente comando *Python*

```bash
python desafio.py inputfile outputfile
```
*Nota*:
inputfile representa el nombre de un archivo de entrada ya existente
outputfile representa el nombre del archivo que produce el script como archivo de salida
