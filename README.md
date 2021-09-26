# SOLUCION Desafío 1: Periodos perdidos (Nivel 3)
Este es el instructivo realizado para ejecutar solución al Desafío 1 - Nivel 3. La solución consiste en un proyecto maven con tecnología Springboot que consume el servicio GDD para luego determinar la lista de fechas restantes, para finalmente devolverlo en formato JSON. 
La solución fue diseñado respetando la arquitectura Layered (Controller->Service->Repository). Como indica el requerimiento, este servicio no solicita parámetro de entrada alguna al usuario, solo entrega una respuesta.

Entre las dependencias usadas por el proyecto, están:
- Springboot 2.4.11
- OpenFeign (Cliente rest, parte de SpringCloud 2020.0.0)
- springdoc-openapi-ui 1.5.9 (librería para la generación del documento swagger con especificación OpenAPI 3.0, además de contar con la interfaz gráfica)

## 1. Prerequisitos
- Java 8.x
- Apache Maven 3.x

## 2. Compilación y Ejecución
Una vez descargado la solución, lo sgte. será compilarlo, a atravéz de una consola o terminal, vaya a la raíz del repositorio, ejecute los sgtes. comandos:

0. el servicio GDD debe estar operativo (localhost:8080/periodos/api)
1. mvn clean
2. mvn install
3. java -jar target\desafio-uno-1.0.0.jar

### 2.1 Cliente Rest
Si el servicio GDD se encuentra no se encuentra ejecutando en la dirección antes mencionada, dirígase al archivo **ApiPeriodosProxy** para que pueda modificar con los valores adecuados.
Aplicados los cambios, recuerde ejecutar los pasos anteriores (del 1 al 3)

Ya ejecutado la secuencia de comandos indicada, usted podrá ver el microservicio operativo, con un mensaje como el sgte.: **Started DesafioUnoApplication in ... seconds**

## 3. Pruebas
Esta solución cuenta con la interfaz swagger para realizar pruebas, para testearlo haga lo sgte.:
1. Dirigase a la sgte. url: http://localhost:8004/swagger-ui-custom.html
2. Usted podrá vizualizar el endpoint GET desafiuno/nivel3, de click en el botón **Try it out**, después de click en **Execute**

Otra forma de verificar el funcionamiento, es desde una consola usando el comando curl.
- curl http://localhost:8004/desafiouno/nivel3

## Adicionales
Si gusta puede descargarse el documento swagger de la API Rest, a travéz de la interfaz gráfica mencionado en el paso anterior, o usando las sgtes. urls:
- http://localhost:8004/v3/api-docs (formato JSON)
- http://localhost:8004/v3/api-docs.yaml (Lenguaje YAML)
