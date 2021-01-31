# Desafío 1: Periodos perdidos

Para copilar el proyecto se requiere Java y Maven instalado.

Ejecutar el siguiente comando 

mvn package

Luego de compilar el proyecto ingresar al directorio target ejecutar el siguiente comando java

./mvnw spring-boot:run

El proyecto correra en el puerto 8081.

Para el funcionamiento del proyeto es necesario tener la Api de Generador_de_Datos ejecutandose, para esto:

Ingresar al directorio ApiPeriodos ejecutar el siguiente comando maven

mvn package

Luego de compilar el proyecto ingresar al directorio target ejecutar el siguiente comando java

java -jar .\api-periodos-1.0.0.jar

Nota: Debe estar disponible el puerto 8080 en el PC donde se ejecute esta API
