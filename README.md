#Desafio Uno / Nivel 3 

Autor: Claudio Marambio Cespedes.

Email: clamarambio85@gmail.com

Via por la cual me entere del desafio por medio de Leonardo Miranda / Tech Consult

##Consideraciones
Para el buen funcionamiento debe estar en ejecucion GDD para la obtencion de data.
En el caso que este servicio no este funcionando lanzara el siguiente mensaje de error: "Servicio de generacion de datos no disponible".

##Ejecucion de servicio GDD
Se debe segir la guia de ejecucion ubicada en https://github.com/previred/Generador_Datos_Desafio_Uno la cual fue proporcionada para resolver el "Desafio Uno" nivel 3.

####Observaciones [api-periodos]
Al momento de seguir la guia y compilar se detecto un problema por falta de dependencias, por lo cual 
se agrego el siguiente repositorio en el pom.xml de api-periodos:

'
<!-- https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api -->
<dependency>
	<groupId>javax.xml.bind</groupId>
	<artifactId>jaxb-api</artifactId>
	<version>2.2.6</version>
</dependency>'

Posteriormente compilar y ejecutar el jar generado que esta ubicado en la carpeta tar
que se encuentra dentro del projecto Generador_Datos_Desafio_Uno/ApiPeriodos/
como lo indica el instructivo.

**Instruccion desde la terminal **

$ java -jar api-periodos-1.0.0.jar

curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8080/periodos/api'
ò
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/periodos/api'


##Desafio Uno (Indicaciones de ejecucion)
* Descargar los el proyecto del pull request correspondiente.
* En el directorio base del proyecto [previred-desafio-uno] desde linea de comando ejecutar la siguiente instruccion: mvn clean install.

* Se generara una carpera "target" en la cual se encontrara el jar de despliegue con implementacion spring-boot.

### Configuracion de proyecto:
* El servidor de spring en puerto 9091.
* Ejecucion desde terminal [previred-desafio-uno/target/]: java -jar previred-desafio-uno-1.0.jar
* EndPoint : http://localhost:9091/desafioUno/nivel3 
* Tipo: Get
* Headers: [Accept: application/json]
* URl Swagger: http://localhost:9091/swagger-ui.html
* Ejecucion de servicio por terminal: 
curl -X GET --header 'Accept: application/json' 'http://localhost:9091/desafioUno/nivel3'

##Resultado de ejecucion en formato json:

{"id":1,"fechaCreacion":"2002-05-01","fechaFin":"2014-06-01","fechas":["2002-08-01","2002-09-01","2002-10-01","2002-12-01","2003-01-01","2003-02-01","2003-03-01","2003-04-01","2003-05-01","2003-06-01","2003-07-01","2003-08-01","2003-10-01","2004-02-01","2004-03-01","2004-05-01","2004-06-01","2004-07-01","2004-08-01","2004-09-01","2004-12-01","2005-01-01","2005-03-01","2005-05-01","2005-06-01","2005-08-01","2005-09-01","2005-11-01","2005-12-01","2006-02-01","2006-07-01","2006-09-01","2006-10-01","2007-02-01","2007-04-01","2007-05-01","2007-07-01","2007-09-01","2007-10-01","2007-11-01","2008-01-01","2008-02-01","2008-03-01","2008-05-01","2008-07-01","2008-08-01","2008-09-01","2008-10-01","2009-01-01","2009-03-01","2009-04-01","2009-05-01","2009-06-01","2009-07-01","2009-08-01","2009-10-01","2009-12-01","2010-01-01","2010-02-01","2010-03-01","2010-07-01","2010-08-01","2010-09-01","2010-10-01","2010-11-01","2010-12-01","2011-01-01","2011-02-01","2011-03-01","2011-04-01","2011-07-01","2011-08-01","2011-09-01","2011-10-01","2011-11-01","2011-12-01","2012-01-01","2012-02-01","2012-03-01","2012-04-01","2012-05-01","2012-07-01","2012-10-01","2012-12-01","2013-01-01","2013-04-01","2013-08-01","2013-10-01","2013-11-01","2013-12-01","2014-01-01","2014-03-01","2014-04-01","2014-05-01"],"fechasFaltantes":["2002-05-01","2002-06-01","2002-07-01","2002-11-01","2003-09-01","2003-11-01","2003-12-01","2004-01-01","2004-04-01","2004-10-01","2004-11-01","2005-02-01","2005-04-01","2005-07-01","2005-10-01","2006-01-01","2006-03-01","2006-04-01","2006-05-01","2006-06-01","2006-08-01","2006-11-01","2006-12-01","2007-01-01","2007-03-01","2007-06-01","2007-08-01","2007-12-01","2008-04-01","2008-06-01","2008-11-01","2008-12-01","2009-02-01","2009-09-01","2009-11-01","2010-04-01","2010-05-01","2010-06-01","2011-05-01","2011-06-01","2012-06-01","2012-08-01","2012-09-01","2012-11-01","2013-02-01","2013-03-01","2013-05-01","2013-06-01","2013-07-01","2013-09-01","2014-02-01","2014-06-01"]}
