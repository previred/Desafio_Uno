# pruebaServicioGDDRest

Servicio Rest que invoca a Servicio Rest GDD 


## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

Mira **Despliegue** para conocer como desplegar el proyecto.


### Pre-requisitos 📋
```
Java Version 8
```

### Instalación 🔧


_Ingresar a la carpeta del proyecto_

```
cd pruebaServicioGDDRest
```

_Ejecutar comando_

```
mvn clean package (linux)
```

```
mvnw clean package (Windows no es necesario tener instalado Maven) 
```
_Verificar que se genero en la ruta target el archivo_

```
pruebaServicioGDDRest-1.0.0.jar
```
## Despliegue 📦

_En la raiz del proyecto ir a la carpeta target
y ejecutar el siguiente comando:_ 

```
java -jar pruebaServicioGDDRest-1.0.0.jar
```
## Consumir API ⚙️

_Para consumir el servicio se puede invocar ls siguiente URL_
```
curl -X GET  http://localhost:9000/getfechas
```
## Documentacion
_La documentación swagger del API (una vez que se levanta el API) queda disponible en_
```
http://localhost:9000/swagger-ui.html#
```

## Construido con 🛠️

_Herramientas que utilizadas_

* [Spring boot](https://spring.io/projects/spring-boot) - El framework usado
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Swagger](https://swagger.io/) - Documentacion API


## Autores ✒️

* **Sebastian Aros** - *Trabajo Inicial* - 
 






