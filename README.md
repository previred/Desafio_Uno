# Previred Desafio Uno
Solucion a Desafio Uno impuesto por Previred para prueba de selección en Desarrollador Backend

Programa escrito en Python 3 donde se especifica dos parametros: el archivo json de entrada y el nombre del archivo json de salida. El script al recibir el archivo json itera entre las fechas de 'fechaCreacion' y 'fechaFin', ignorando las fechas dentro del array 'fechas' mientras inserta las demas en un arreglo del cual es usado para generar un nuevo json como 'fechasFaltantes'.

## Requerimientos
- Sistema operativo compatible con Python 3
- Python 3 instalado (version usada la 3.8.2)
- Modulo dateutil.relativedelta instalado

## Instalación y uso
- Clonar proyecto
- Instalar el modulo dateutils.relativedelta con `pip3 install python-dateutil`
- Dirigirse al directorio del repositorio
- Ejecutar previred.py bajo el siguiente formato: $ `.\previred.py <archivo_json> <nombre_salida_json>`
- Ejemplo: `.\previred.py entrada.json salida.json`

### Parametros
- archivo_json: Directorio del archivo json usado en el ejercicio
- nombre_salida: Nombre del archivo json a generar por por el script

### Pruebas unitarias
En caso de querer realizar pruebas unitarias ejecute el archivo test.py, sirvase de abrir, revisar y crear nuevas pruebas de ser necesario

## Datos Commit

- **Nombre Completo:** Pedro Pablo Pastene Eluani
- **Correo Electrónico:** pe.pastene@gmail.com
- **Vía por la que te entérate del desafío:** LinkedIn