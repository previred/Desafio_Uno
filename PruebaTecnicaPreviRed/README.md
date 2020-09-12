# Proyecto-PruebaTecnicaPreviRed
  * Proyecto que obtiene las fechas que falta del listado entregado
  
# Requisitos para el Proyecto:
  * IDE: sts-4.7.0.RELEASE o El que más Ocupe. 
  * JAVA: se utilizó la version 11.
  * LIBRERIA: Se ocupo el banco de librerias de MVN-Repository.
  * API Externa: Api Entregada por la empresa para el desafio.
  * Spring Boot: tecnologia ocuapda.
  * Swagger: tecnologia ocuapda para documentar el desarrollo.
  * Junit y Mockito: librerias para realizar las Pruebas Unitarias.


# Bajar y levantar el proyecto desde Linux (Consola):

  * Bajar el proyecto por console:
     - copiar la url "CODE" => https://github.com/previred/Desafio_Uno.git
     - En pc o notebook abrir un Terminal
     - posicionar se en la carpeta donde bajara el proyecto.
     - clonar proyecto => git clone https://github.com/previred/Desafio_Uno.git "nombre propio"
     
  * Abrir el IDE:
     - Abrir un worksapce, lo ideal dejar el proyecto dentro de la carperta workspace.
     - Importar el proyecto: Import -> General -> Proyects From Folder o Archive -> ir a la carpeta donde se bajó el proyecto.
     - Limpiar el proyecto: Proyect -> Clean...
     - Bajar las Dependencia:  Botón derecho del mouse sobre el proyecto -> Maven -> Update Proeyct...
     - Corregir el path Java por el que tengan instalado en la máquina.
      
# Bajar y levantar el proyecto desde Windows:

  * Bajar el proyecto empaquetado:
     - Ir al botón "CODE" => Download ZIP
     - El archivo empaquetado queda en la carpeta Descarga
     - Copiar y pegar en el workspaces de trabajo
     - Descomprimir el proyecto.
     
  * Abrir el IDE:
     - Abrir un worksapce, lo ideal dejar el proyecto dentro de la carperta workspace.
     - Importar el proyecto: Import -> General -> Proyects From Folder o Archive -> ir a la carpeta donde se bajó el proyecto.
     - Limpiar el proyecto: Proyect -> Clean...
     - Bajar las Dependencia:  Botón derecho del mouse sobre el proyecto -> Maven -> Update Proyect...
     - Corregir el path Java por el que tengan instalado en la máquina.
     
# Ejecutar el Proyecto:

  * pasos:
     - Levantar Proyecto: Botón derecho del mouse sobre el proyecto -> Run as -> Spring Boot App
       ** Si el proyecto no presenta errores de librerias, levantará sin problema

# Caracteristica Generales:
  * API Externa: Se consulta una api externa que entrega la información requerida para el trabajo del desafio.
     - URL: http://{domino o localhost}:8080/periodos/api
     - JSON Response:
   	  {
	    "id": 1,
	    "fechaCreacion": Fecha inicial del Rango,
	    "fechaFin": Fecha de Termino del Rango,
	    "fechas": [Listado de fechas que estan dentro del rango Inicial y Final, las fechas son aleatorias y no secuenciales]
	}
     
# Url del Proyecto:
  * Consultar: http://{domino o localhost}:8090/app/consulta-global  Método: GET 
    - JSON de Respuesta: Response
	{
	    "id": 1,
	    "fechaCreacion": "2003-04-01",
	    "fechaFin": "2015-06-01",
	    "fechas": [
		"2003-04-01",
		"2003-08-01",
		"2003-11-01",
		"2003-12-01",
		"2004-01-01",
		"2004-02-01",
		"2004-06-01",
		"2004-07-01"
	    ],
	    "fechasFaltante": [
		"2003-05-01",
		"2003-06-01",
		"2003-07-01",
		"2003-09-01",
		"2003-10-01",
		"2004-03-01",
		"2004-04-01",
		"2004-05-01",
		"2004-08-01"
	    ]
	}

    - Obs: La respuesta del la API fue cortada, para el ejemplo de la documentación.
     

 
   * Error API: 
    - JSON de Respuesta: ERROR
	{
	    "hora": "2020-09-11T23:04:53.080+00:00",
	    "mensaje": "Mensaje acorde al error capturado en la API",
	    "error": "ErrorConexcionApiException" o "ErrorGlobalSistema",
	    "status": 404,500,406
	}


# Url del la Documentación del Proyecto:
  * DocumentaCión: http://{domino o localhost}:8090/swagger-ui.html


# Conclusión de la API Restfull
  * Conexión con una Api.
  * Se trabajó bajo el concepto de bajo acoplamiento y alta cohesión. (DI = Inyección de Dependecia).
  * Manejo de Excepciones.
  * Documentación del Desarrollo.
  * Pruebas Unitarias.
    
 # Fin
