# Desafío 1: Periodos perdidos
# OBSERVACIÓN: Se opta por desafío Nivel 3!...

AMBIENTE: WINDOWS => Comillas simples deben ser reemplazadas por comillas dobles.

VERSIONES DE PRODUCTOS:
	Swagger Codegen 2.3.1 (OpenApi 2.0) 
	Java 8 
	Spring-Boot 1.5.11.RELEASE 
	Maven 3

# EJECUCIÓN DE GENERADOR DE DATOS REQUERIDA PARA NIVEL 3

COMPILAR PROYECTO ApiPeriodos
	Requisitos: 
		Instalar Java 1.8 JDK 1.4
		Instalar Maven 3
	Procedimiento:
		Acceder a directorio: ApiPeriodos
		Ejecutar comando maven: mvn package

EJECUTAR PROYECTO ApiPeriodos
	Procedimiento: 
		Acceder a directorio: ApiPeriodos/target
		Ejecutar comando: java -jar .\api-periodos-1.0.0.jar

CONSUMIR API ApiPeriodos
	Procedimiento:
		Ejecutar comando en cualquier consola: 
		curl -X GET --header "Accept: application/json" "http://127.0.0.1:8080/periodos/api"	

# EJECUCIÓN DE GENERADO DE DATOS REQUERIDA PARA NIVEL 3		

COMPILAR PROYECTO ApiPeriodosPerdidos
	Requisitos: 
		Instalar Java 1.8 JDK 1.4
		Instalar Maven 3
	Procedimiento:
		Acceder a directorio: ApiPeriodosPerdidos
		Ejecutar comando maven: mvn package

EJECUTAR PROYECTO ApiPeriodosPerdidos
	Procedimiento: 
		Acceder a directorio: ApiPeriodosPerdidos/target
		Ejecutar comando: java -jar .\api-periodosperdidos-1.0.0.jar

VISUALIZAR DOCUMENTACIÓN ApiPeriodosPerdidos
	Procedimiento:
		Ejecutar comando en browser: http://127.0.0.1:8081/periodosperdidos/swagger-ui.html#/

CONSUMIR API ApiPeriodosPerdidos
	Procedimiento 1:
		Se cambia puerto 8080 por 8081 para evitar conflicto con Generador de Datos
		Ejecutar comando en cualquier consola: 
		curl -X GET --header "Accept: application/json" "http://127.0.0.1:8081/periodosperdidos/api"
		
	Procedimiento 2:
		Se puede utilizar POSTMAN configurando los siguientes valores:
			HEADERS
				Accept 			application/json
				Content-Type 	application/json
			MÉTODO GET
				http://127.0.0.1:8081/periodosperdidos/api
			
		

