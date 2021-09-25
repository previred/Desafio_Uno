# Desafío 1: Periodos perdidos

A continuacion se explican los procesos realizados para resolver al solucion propuesta

-   Primero se detallan los servicios desarrollados
-   Se presenta la estructura de directorios de la solucion
-   Luego se explica la forma en la cual se deben ejecutar

-   Servicios desarrollados

-   Servicio REST - DesafioUno.GDD.API
     -   Este servicio esta desarrollado en rtecnologia ASP.NET Core 5
     -   Expone un servicio con la siguiente ruta
          https://localhost:9000/api/GeneradorDeFechas/GeneraFechas
     -   Expone Swagger en la siguiente ruta
          https://localhost:9000/swagger/index.html
     -   El servicio Generafechas se compone de 2 parámetros (ambos fechas)

- Servicio REST - DesafioUno.Nivel3.API
    - Este servicio esta desarrollado en rtecnologia ASP.NET Core 5
    - Expone un servicio con la siguiente ruta
       https://localhost:9000/api/GeneradorDeFechas/GeneraFechas
    - Expone Swagger en la siguiente ruta
       https://localhost:9001/swagger/index.html
    - El servicio ObtieneRangoDeFechas se compone de 2 parámetros (ambos fechas)

- Estructura de directorios de la solucion

```
    root
        - binarios
            - DesafioUno.GDD.API
            - DesafioUno.Nivel3.API
        - src
```

-   Deploy y la forma de ejecutarlos
    Para el deploy se adjunta en una carpeta llamada "binarios", la cual se encuentra en la raiz del proyecto, esta carpeta contiene 2 subcarpetas
      
- Carpeta 1 : DesafioUno.GDD.API - Contiene el servisio REST que entrega una serie de fechas en forma aleatoria

```
INVOCACION:
    $ .\DesafioUno.GDD.API.exe --urls=https://localhost:9000
SALIDA (un archivo con el siguiente contenido) :
```

```json
{
    "id": 1,
    "fechaCreacion": "2021-09-23",
    "fechaFin": "2022-09-23",
    "fechasGeneradas": [
        "2021-09-01",
        "2021-10-01",
        "2022-08-01",
        "2022-09-01"
    ]
}
```

    
- Carpeta 2 : DesafioUno.Nivel3.API - Contiene el servisio REST que llama al servicio GDD y obtiene un objetos con fechas aleatorias, luego complementa el resultado con las fechas faltantes.

```
INVOCACION:
    $ .\DesafioUno.Nivel3.API.exe  --urls=https://localhost:9001
SALIDA (un archivo con el siguiente contenido) :
```

```json
{
    "id": 1,
    "fechaCreacion": "2021-09-23",
    "fechaFin": "2022-09-23",
    "fechasGeneradas": [
        "2021-10-01",
        "2022-01-01",
        "2022-02-01",
        "2022-03-01",
        "2022-05-01",
        "2022-06-01",
        "2022-08-01"
    ],
    "fechasFaltantes": [
        "2021-09-01",
        "2021-11-01",
        "2021-12-01",
        "2022-04-01",
        "2022-07-01",
        "2022-09-01"
    ]
}
```
