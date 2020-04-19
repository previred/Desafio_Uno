# Nivel 3

Esta aplicacion java spring boot levanta un servicio que realiza la consulta al servicio que genera los datos y responde la solicitud con el formato requerido

El servicio se despliega en el puerto 8081 para no entrar en conflicto con el generador de datos

## Compilar

En la raiz del proyecto ejecutar

    mvn package

## Ejecutar

En la raiz del proyecto ejecutar
    
    java -jar target/nivel3.jar

## Consumo

Desde una consola se puede utilizar el comando curl para obtener el formato deseado

    curl -X GET 'http://localhost:8081/faltantes'

## Swagger
Se puede acceder a la documentacion autogenerada del plugin swagger

    http://localhost:8081/swagger-ui.html

y se puede encontrar la definicion en la raiz [faltantes.yaml](./faltantes.yaml)
