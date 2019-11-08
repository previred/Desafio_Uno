# Desafío 1: Periodos perdidos

Este proyecto consiste en una API REST que entrega las fechas faltantes obtenidas desde la API REST de Generación de Datos Desafío.


## Configuración

Las configuraciones se realizan en el archivo **\src\main\resources\application.properties** dentro de la cartepa de fuentes del proyecto. 

Se puede configurar el puerto donde exponer el servicio modificando la clave **server.port**.

```
server.port=8081
```

La URI desde donde se consumen las fechas se puede configurar con la clave **app.gdduri**.

```
app.gdduri=http://localhost:9000/periodos/api
```

## Compilar y ejecutar


Prerequisito: Para copilar el proyecto se requiere Java 8 y Maven instalado.
 
Se debe ingresar al directorio del proyecto y ejecutar el siguiente comando maven:


```
mvn package
```

Luego de compilar el proyecto ingresar al directorio **target** y ejecutar el siguiente comando java:

```
java -jar .\DesafioNivel3-0.0.1-SNAPSHOT.jar
```

Debe estar disponible el puerto 8081, o el que haya sido configurado, en el PC donde se ejecute esta API, de lo contrario dará un error.

## Consumir

Para consumir el servicio se debe ingresar a la URL 

```
http://localhost:8081/api/fechasfaltantes
```

### Respuesta del servicio

Luego de consumido el servicio se obtendrá un resultado como el siguiente:

```
{
    "id": null,
    "fechaCreacion": "2002-12-01",
    "fechaFin": "2016-12-01",
    "fechas": [
        "2002-12-01",
        "2003-01-01",
        "2003-02-01",
        "2003-03-01",
        "2003-04-01",
        "2003-06-01",
        "2003-11-01",
        "2003-12-01",
        "2004-01-01",
        "2004-02-01",
        "2004-03-01",
        "2004-04-01",
        "2004-06-01",
        "2004-07-01",
        "2004-10-01",
        "2004-11-01",
        "2004-12-01",
        "2005-01-01",
        "2005-04-01",
        "2005-05-01",
        "2005-08-01",
        "2005-09-01",
        "2005-10-01",
        "2005-12-01",
        "2006-03-01",
        "2006-04-01",
        "2006-06-01",
        "2006-09-01",
        "2006-10-01",
        "2006-11-01",
        "2007-03-01",
        "2007-06-01",
        "2007-09-01",
        "2007-10-01",
        "2007-11-01",
        "2008-01-01",
        "2008-03-01",
        "2008-05-01",
        "2008-07-01",
        "2008-09-01",
        "2008-10-01",
        "2009-01-01",
        "2009-02-01",
        "2009-04-01",
        "2009-05-01",
        "2009-06-01",
        "2009-07-01",
        "2009-08-01",
        "2009-09-01",
        "2009-10-01",
        "2009-11-01",
        "2009-12-01",
        "2010-01-01",
        "2010-02-01",
        "2010-03-01",
        "2010-04-01",
        "2010-07-01",
        "2010-09-01",
        "2010-10-01",
        "2010-11-01",
        "2010-12-01",
        "2011-03-01",
        "2011-04-01",
        "2011-06-01",
        "2011-08-01",
        "2011-10-01",
        "2011-11-01",
        "2012-03-01",
        "2012-06-01",
        "2012-08-01",
        "2012-10-01",
        "2013-01-01",
        "2013-04-01",
        "2013-07-01",
        "2013-08-01",
        "2013-10-01",
        "2013-11-01",
        "2013-12-01",
        "2014-01-01",
        "2014-03-01",
        "2014-05-01",
        "2014-07-01",
        "2014-08-01",
        "2014-09-01",
        "2014-10-01",
        "2014-11-01",
        "2015-02-01",
        "2015-04-01",
        "2015-05-01",
        "2015-07-01",
        "2015-10-01",
        "2015-11-01",
        "2016-03-01",
        "2016-04-01",
        "2016-10-01",
        "2016-11-01"
    ],
    "fechasFaltantes": [
        "2003-05-01",
        "2003-07-01",
        "2003-08-01",
        "2003-09-01",
        "2003-10-01",
        "2004-05-01",
        "2004-08-01",
        "2004-09-01",
        "2005-02-01",
        "2005-03-01",
        "2005-06-01",
        "2005-07-01",
        "2005-11-01",
        "2006-01-01",
        "2006-02-01",
        "2006-05-01",
        "2006-07-01",
        "2006-08-01",
        "2006-12-01",
        "2007-01-01",
        "2007-02-01",
        "2007-04-01",
        "2007-05-01",
        "2007-07-01",
        "2007-08-01",
        "2007-12-01",
        "2008-02-01",
        "2008-04-01",
        "2008-06-01",
        "2008-08-01",
        "2008-11-01",
        "2008-12-01",
        "2009-03-01",
        "2010-05-01",
        "2010-06-01",
        "2010-08-01",
        "2011-01-01",
        "2011-02-01",
        "2011-05-01",
        "2011-07-01",
        "2011-09-01",
        "2011-12-01",
        "2012-01-01",
        "2012-02-01",
        "2012-04-01",
        "2012-05-01",
        "2012-07-01",
        "2012-09-01",
        "2012-11-01",
        "2012-12-01",
        "2013-02-01",
        "2013-03-01",
        "2013-05-01",
        "2013-06-01",
        "2013-09-01",
        "2014-02-01",
        "2014-04-01",
        "2014-06-01",
        "2014-12-01",
        "2015-01-01",
        "2015-03-01",
        "2015-06-01",
        "2015-08-01",
        "2015-09-01",
        "2015-12-01",
        "2016-01-01",
        "2016-02-01",
        "2016-05-01",
        "2016-06-01",
        "2016-07-01",
        "2016-08-01",
        "2016-09-01",
        "2016-12-01"
    ]
}
```