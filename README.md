# README #

Proyecto para realizar Flujos de Venta a Distancia de Creditos.

### Para que es este Repositorio? ###

* Es para realizar control de flujo o pasos de Venta a Distancia de Credito de Coopeuch.

### Como se Configura? ###

* Pasos:

1. Se debe descargar JDK 1.8.
2. Se debe descargar la version de gradle 6.2.2 y configurarlo como variable de entorno, usar esta para correr el aplicativo local y descargar dependencias.
3. Para compilar se debe ejecutar el comando "gradle bootWar"
4. Para probar los endpoint del Proyecto es posible realizar llamada al servidor local con url: http://localhost:8080/swagger-ui.html para ver los endpoint y como se consume.
5. Se debe cambiar encoding a UTF-8 para mostrar acentos de valores constantes y valores de variables.

6. Para las configuraciones de Cloud este proyecto tiene como nombre consumo-planilla-web.

### Configuraciones para Zona Horaria de Base de Datos y Servidor ###

-Duser.timezone=America/Santiago
-Dfile.encoding=UTF-8

SET timezone TO 'America/Santiago';

### Contacto ###

* Celula Fenix 