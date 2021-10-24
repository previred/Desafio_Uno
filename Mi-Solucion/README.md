instalar
copiar carpeta del programa en una ruta en su equipo que tenga acceso.
o
importar en eclipse como un "Existing Maven Projects" y buscar la carpeta donde esta el programa seleccionar carpeta y despues seleccionar el pom.xml  y finish luego esperar un momentos y ya se puede ejecutar en entorno eclipse

compilar con maven
ir a la ruta principal donde se encuentra el pom.xml y ejecutar
o
en eclipse ejecutar como Java Application y seleccionar archivo "MiSolucionApplication.java"
o
en windows
java -jar /target/mi_solucion-0.0.1-SNAPSHOT.jar 
en systemas linux
java -jar /target/mi_solucion-0.0.1-SNAPSHOT.jar

para acceder al servicio se ingresa por url en browser o en postman o soapUi con la siguiente url en metodo get

http://127.0.0.1:8081/misolucion/ws/misolucion

Nota: se require que el servicio de periodos este corriendo en el mismo equipo, ya que url usada en el serivicio es "http://127.0.0.1:8080/periodos/api" que apunta a la misma maquina que se esta usando.
