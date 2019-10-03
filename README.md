# Desafío 1: Periodos perdidos
1.- Para compilar ejecutar en directorio raiz
./mvnw clean package

2.- Ejecutar servicio GDD Generador_Datos_Desafio_Uno en puerto 8080
   java -jar .\api-periodos-1.0.0.jar


3.- Ejecutar servicio Periodos Perdidos:
    cd target 
    java -jar desafio-0.0.1-SNAPSHOT.jar, estará escuchando en puerto 9090

    En navegador web: http://127.0.0.1:9090/periodofaltante
    curl http://127.0.0.1:9090/periodofaltante desde terminal.
4.- Se incluye archivo de muestra.
