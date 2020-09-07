Este proyecto contiene varios archivos para ejecutarlo de maneras diferentes.
Debe copiar todos los arhivos en una carpeta.(Windows)

Detalle de los sistemas
Java 8

Compilar y ejecutar el proyecto
Para copilar el proyecto se requiere Java y Maven instalado. Ingresar al directorio MiSolucion ejecutar el siguiente comando maven

mvn package

Luego de compilar el proyecto ingresar al directorio target ejecutar el siguiente comando java

java -jar .\mi_solucion.jar "RutaYNombreArchivoEntrada" "RutaYNombreArchivoSalida"

Posteriormente verificar el archivo de salida que contiene las fechas faltantes.

