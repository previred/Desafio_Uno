api-fechas servicio Spring Bootm
====================

Autor : Jorge Bravo Aliaga - (9 63 93 3428 , jhbravoa@gmail.com)
	
	Servicio rest Spring Boot. Consume servicio http://127.0.0.1:8080/periodos/api que genera un listado
	 de fecha entres una fecha de inicio y fecha de fin. Las fechas generadas, en forma aleatoria, 
	 corresponden a las del primer día del mes. Existen fechas que no se listan y el servicio 
	 desarrollado, api-fechas, informa las fechas generadas y las fechas faltantes.
	
	Listado de operaciones y su descripción

	http://127.0.0.1:8080/periodos/api 	GET		:	Provee json con fechas generads en forma aleatoria.
	api/salida				GET     :	Genera json con fechas generadas y fechas faltantes.

## Registro de Log De errores

	En carpeta Log se crean los archios con los errores del servicio cuando estos ocurre. Se podria 
	agragar tambien un log de operación normal con comentarios breves.

## Descripción archivo YML

	endpoint	: http://127.0.0.1:8080/periodos/api. Servicio que genera las fechas en forma aletoria
	port		: 8090. Puerto donde se consume el serrvicio api-fechas.	
	name		: api-fechas. Nombre de Aplicación desarrollada.

## Consumo del Servicio desde SWAGGER.

	Para ver la documentación y prueba del servicio desde Swagger se debe proceder de la siguiente forma:
	1.- Verificar que el servicio de Swagger este habilitado.
	2.- Desde el Browser acceder a la siguiente dirección:
		 http://localhost:8090/swagger-ui.html
		 
		 En package config se encuentra la clase SwaggerConfig, y en el pom.xml se encuentran las 
		 componentes que se inyectan al servicio.

## Consumo del Servicio desde JAR.

	Para la ejecución del servicio se debe proceder de la siguiente manera:

	1.- Ingresar a la carpeta del proyecto mediante command.com o en su defecto powershell.
	2.- Ejecutar el siguientes comandos MAVEN.
		a. mvn clean install.
	3.- Verificar que la el proceso concluye con Success.
	4.- Ingresar a carpeta target
	5.- Ejecutar comando
		 a. java -jar .\api-fechas.jar
	6.- Si la ejecución del punto 5. es exitosa se puede consumir el servicio de dos maneras:
		a. Directamente desde el Browser accediendo a la url : http://localhost:8090/api/salida
		b. A traves de postman mediante la importación del archivo contenido en la carpeta 
		resources/postman cuyo nombres es: 	Previred.postman_collection.json.
		
	La ejecución del servicio presentara los json que se describen en el siguiente punto.
		  
## JSON's con entradas y salidas para cada operación

	Entradas. EL servicio provisto por previred genera el siguiente Json con fechas entre el periodo 
	definido por la fechaCreacion y fechaFin, notese que no estan todos los inicios de mes del periodo.
	
	{
	    "id": 1,
	    "fechaCreacion": "2002-12-01",
	    "fechaFin": "2003-06-01",
	    "fechas": [
	        "2002-12-01",
	        "2003-01-01",
	        "2003-04-01",
	        "2003-06-01"
	    ]
	}
    
    Salidas. El servicio api-fechas genera Json con los datos de entrada e incluye las fechas faltantes.   	
    {
	    "id": 1,
	    "fechaCreacion": "2002-12-01",
	    "fechaFin": "2003-06-01",
	    "fechas": [
	        "2002-12-01",
	        "2003-01-01",
	        "2003-04-01",
	        "2003-06-01"
	    ],
	    "fechasFaltantes": [
	        "2003-02-01",
	        "2003-03-01",
	        "2003-05-01"
	    ]
	}


