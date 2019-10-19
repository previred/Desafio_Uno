# Resolucion desafio 1
El desafio fue resuelto utilziando la versión mas reciente de node express.js

Formato de entrada

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

Formato de salida

```json
{
    "id": 6,
    "fechaCreacion": "1968-08-01",
    "fechaFin": "1971-06-01",
    "fechas": [
        "1969-03-01",
        "1969-05-01",
        "1969-09-01",
        "1971-05-01"
    ],
    "fechasFaltantes": [
        "1968-01-01",
        "1968-02-01",
        "1968-03-01",
        "1968-04-01",
        "..."
    ]
}
```

## Instrucciones de instalación
[sudo] npm install

## Instrucciones de compilación
[sudo] node index.js

REQUISITOS:
-  node v7.10.1 o superior

NOTA:
El servicio se encunetra corriendo en http://52.10.8.190:3000/get_date 
