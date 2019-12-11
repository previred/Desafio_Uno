# Respuesta Desafío 1: Periodos Faltantes

Instalacion de la solucion

Se debe descargar el codigo fuente que se encuentra en este repositorio en GitHub:

Una vez descargado los fuentes del proyecto, se debe abrir el IDE de desarrollo JAVA que mas le acomode, en caso de no contar con una herramienta
de desarrollo JAVA, puede descargar Eclipse desde la siguiente URL:

https://www.eclipse.org/downloads/

Una vez instalado el IDE Eclipse, debe abrir la herramienta y desde el menu de opciones que se ve en la parte superior de la pantalla de Eclipse
debe ir a la opcio "help" y seleccionar la opcion "Eclipse Market Please" desde donde debe buscar en la pestaña "Buscar" ó "search" y buscar STS
que son los complementos de Spring para Eclipse

Una vez instalado los complementos de Spring, en Eclipse debe seleccionar la opcion Import Projects, donde debe seleccionar MAVEN y luego la opcion
Existing Maven Projects

Una vez cargado todos los componentes, librerias, etc del proyecto api-periodos-faltantes, se debe Inicializar el proyecto en la pestaña DashBoard
haciendo click con el boton derecho sobre "api-periodos-faltantes" y haciendo click en la opcion (Re)start donde se inicializará la aplicacion

Luego en el navegador de internet que mas le guste, puede acceder al proyecto en la siguiente URL:

http://localhost:7085/periodos-faltantes/

donde se desplagara el interfaz del servicio, donde para realizar una prueba de funcionamiento derá agregar la siguiente estructura JSON como 
parametro de entrada de acuerdo al siguiente ejemplo


```json
{
  "fechaCreacion": "1968-08-01",
  "fechaFin": "1971-06-01",
  "fechas": [
	  "1969-03-01",
      "1969-05-01",
      "1969-09-01",
      "1971-05-01"
  ],
  "id": 0
}
```
Una vez ingresada la estructura de entrada, se debe presionar el botón "Try it out"

## Respuesta: 
    A través de la salida estándar, como respuesta, un archivo Json con las fechas faltantes se entrega con este contenido:
Ejemplo:
    
```json
{
  "id": 0,
  "fechaCreacion": "1968-08-01",
  "fechaFin": "1968-08-01",
  "fechasFaltantes": [
    "1968-08-01",
    "1968-09-01",
    "1968-10-01",
    "1968-11-01",
    "1968-12-01",
    "1969-01-01",
    "1969-02-01",
    "1969-04-01",
    "1969-06-01",
    "1969-07-01",
    "1969-08-01"
  ]
}
```
