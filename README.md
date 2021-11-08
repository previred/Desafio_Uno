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

### Contacto ###

* Anibal Abello - a.abellohernandez@gmail.com
* Nombre completo: Anibal Abello, Correo Electronico: a.abellohernandez@gmail.com, Via desafio, TechConsult: Leonardo Miranda
