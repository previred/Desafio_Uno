# README #

Proyecto para desafio uno.

### Para que es este Repositorio? ###

* Es para obtener los periodos faltantes que entrega el servicio: https://github.com/lmptechconsult/Generador_Datos_Desafio_Uno

### Como se Configura? ###

* Pasos:

1. Se debe descargar JDK 1.8.
2. Se debe descargar la version de gradle 6.8.2 y configurarlo como variable de entorno, usar esta para correr el aplicativo local y descargar dependencias.
3. se debe ejecutar primeramente el proyecto de https://github.com/lmptechconsult/Generador_Datos_Desafio_Uno, ejecutar tal cual como se tiene en las instrucciones
4. Para compilar se debe ejecutar el comando "gradle run --args='--spring.profiles.active=dev'"
5. para probar servicio se requiere de ejecutar el comando: "curl -X GET --header 'Accept: application/json' 'http://localhost:8082/PeriodosPerdidos'"
6. como metodo de entrada no posee atributos correspondientes.
7. metodo de salida (similar a salida de prueba Nivel 3):
 ```json
{
    "salida": {
        "id": 1,
        "fechaCreacion": "1999-11-01",
        "fechaFin": "2017-12-01",
        "fechas": [
            "2014-11-01",
            "2014-12-01",
            "2015-04-01",
            "2015-05-01",
            "2015-07-01",
            "2015-08-01",
            "2015-11-01",
            "2015-12-01",
            "2016-01-01",
            "2016-03-01",
            "2016-10-01",
            "2016-11-01",
            "2017-01-01",
            "2017-03-01",
            "2017-05-01",
            "2017-11-01"
        ],
        "fechasFaltantes": [
            "2016-04-01",
            "2016-05-01",
            "2016-06-01",
            "2016-07-01",
            "2016-08-01",
            "2016-09-01",
            "2016-12-01",
            "2017-02-01",
            "2017-04-01",
            "2017-06-01",
            "2017-07-01",
            "2017-08-01",
            "2017-09-01",
            "2017-10-01",
            "2017-12-01"
        ]
    }
}
```

### Contacto ###

* Anibal Abello - a.abellohernandez@gmail.com
* Nombre completo: Anibal Abello, Correo Electronico: a.abellohernandez@gmail.com, Via desafio, TechConsult: Leonardo Miranda
