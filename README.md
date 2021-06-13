#  Desafio Periodos Perdidos

Este proyecto expone un API REST que entrega la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
*fechasFaltantes*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin” pero que no existen en el arreglo de fechas
Ejemplo.
```json
{
  "id": 1,
  "fechaCreacion": "2001-01-01",
  "fechaFin": "2014-08-01",
  "fechas": [
    "2001-01-01",
    "2001-02-01",
    "2001-03-01",
    "2001-04-01",
    "2001-06-01",
    "2001-07-01",
    "2001-08-01",
    "2001-09-01",
    "2001-10-01",
    "2002-01-01",
    "2002-02-01",
    "2002-03-01",
    "2002-04-01",
    "2002-05-01",
    "2002-06-01",
    "2002-07-01",
    "2002-08-01",
    "2002-10-01",
    "2002-11-01",
    "2003-01-01",
    "2003-02-01",
    "2003-04-01",
    "2003-05-01",
    "2003-06-01",
    "2003-07-01",
    "2003-08-01",
    "2003-09-01",
    "2003-11-01",
    "2003-12-01",
    "2004-01-01",
    "2004-02-01",
    "2004-04-01",
    "2004-06-01",
    "2004-07-01",
    "2004-08-01",
    "2004-11-01",
    "2004-12-01",
    "2005-04-01",
    "2005-07-01",
    "2005-08-01",
    "2005-09-01",
    "2005-12-01",
    "2006-01-01",
    "2006-02-01",
    "2006-03-01",
    "2006-04-01",
    "2006-05-01",
    "2006-08-01",
    "2006-09-01",
    "2006-10-01",
    "2006-11-01",
    "2007-03-01",
    "2007-04-01",
    "2007-08-01",
    "2007-09-01",
    "2007-10-01",
    "2007-11-01",
    "2007-12-01",
    "2008-02-01",
    "2008-03-01",
    "2008-04-01",
    "2008-05-01",
    "2008-09-01",
    "2008-11-01",
    "2009-02-01",
    "2009-04-01",
    "2009-05-01",
    "2009-07-01",
    "2009-08-01",
    "2009-10-01",
    "2009-12-01",
    "2010-01-01",
    "2010-03-01",
    "2010-07-01",
    "2010-08-01",
    "2010-12-01",
    "2011-01-01",
    "2011-02-01",
    "2011-03-01",
    "2011-04-01",
    "2011-05-01",
    "2011-07-01",
    "2011-08-01",
    "2012-01-01",
    "2012-02-01",
    "2012-04-01",
    "2012-07-01",
    "2012-09-01",
    "2012-10-01",
    "2012-11-01",
    "2013-01-01",
    "2013-04-01",
    "2013-05-01",
    "2013-07-01",
    "2013-10-01",
    "2014-03-01",
    "2014-04-01",
    "2014-05-01",
    "2014-07-01"
  ],
  "fechasFaltantes": [
    "2001-05-01",
    "2001-11-01",
    "2001-12-01",
    "2002-09-01",
    "2002-12-01",
    "2003-03-01",
    "2003-10-01",
    "2004-03-01",
    "2004-05-01",
    "2004-09-01",
    "2004-10-01",
    "2005-01-01",
    "2005-02-01",
    "2005-03-01",
    "2005-05-01",
    "2005-06-01",
    "2005-10-01",
    "2005-11-01",
    "2006-06-01",
    "2006-07-01",
    "2006-12-01",
    "2007-01-01",
    "2007-02-01",
    "2007-05-01",
    "2007-06-01",
    "2007-07-01",
    "2008-01-01",
    "2008-06-01",
    "2008-07-01",
    "2008-08-01",
    "2008-10-01",
    "2008-12-01",
    "2009-01-01",
    "2009-03-01",
    "2009-06-01",
    "2009-09-01",
    "2009-11-01",
    "2010-02-01",
    "2010-04-01",
    "2010-05-01",
    "2010-06-01",
    "2010-09-01",
    "2010-10-01",
    "2010-11-01",
    "2011-06-01",
    "2011-09-01",
    "2011-10-01",
    "2011-11-01",
    "2011-12-01",
    "2012-03-01",
    "2012-05-01",
    "2012-06-01",
    "2012-08-01",
    "2012-12-01",
    "2013-02-01",
    "2013-03-01",
    "2013-06-01",
    "2013-08-01",
    "2013-09-01",
    "2013-11-01",
    "2013-12-01",
    "2014-01-01",
    "2014-02-01",
    "2014-06-01",
    "2014-08-01"
  ]
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd

# Detalle de los sistemas

Java 8
Spring-Boot 2.5.1
Maven 4


# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java, Maven y Eclipse(configurado con Spring Tools 4) instalado.
Abrir eclipse y cargar proyecto
ejecutar proyecto como "String Boot App"

*Nota*:
Debe estar disponible el puerto *9080* en la estación de trabajo donde se ejecute esta API

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://localhost:9080/swagger-ui.html

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: */*' 'http://localhost:9080/PeriodosPerdidos'
```
