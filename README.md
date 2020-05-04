
## Compilación ##

El ambiente para compilar es maven 3.3 y Java 1.8

    Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T13:41:47-03:00)
    Maven home: C:\jc\apps\apache-maven-3.3.9
    Java version: 1.8.0_101, vendor: Oracle Corporation
    Java home: C:\Program Files\Java\jdk1.8.0_101\jre
    Default locale: es_CL, platform encoding: Cp1252
    OS name: "windows 10", version: "10.0", arch: "amd64", family: "dos"


Se compila de la manera tradicional

    mvn clean package

## Ejecución ##

Se ejecuta el bigjar generado

    java -jar target\lagunas-jar-with-dependencies.jar <entrada11.txt >salida11.txt

ejemplo de archivo de entrada

    {
    "id": 11,
    "fechaCreacion": "2010-01-01",
    "fechaFin": "2012-03-01",
    "fechas": [
          "2010-05-01",
          "2010-06-01",
          "2010-07-01",
          "2011-04-01"]
    }

ejemplo de archivo de salida

    {
      "id" : 11,
      "fechaCreacion" : "2010-01-01",
      "fechaFin" : "2012-03-01",
      "fechasFaltantes" : [ "2010-01-01", "2010-02-01", "2010-03-01", "2010-04-01", "2010-08-01", "2010-09-01", "2010-10-01", "2010-11-01", "2010-12-01", "2011-01-01", "2011-02-01", "2011-03-01", "2011-05-01", "2011-06-01", "2011-07-01", "2011-08-01", "2011-09-01", "2011-10-01", "2011-11-01", "2011-12-01", "2012-01-01", "2012-02-01", "2012-03-01" ]
    }