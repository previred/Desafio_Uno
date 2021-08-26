# Desafío 1: Periodos perdidos - Nivel 3

# Descarga
Para correr el proyecto se debe clonar con sus submodulos. 
```sh
git clone --recurse-submodules https://github.com/j1cs/Desafio_Uno
```

# Instalación

## Docker 
Si se tiene instalado docker y docker-compose, se puede ejecutar 
```sh
docker-compose up
```
y quedará listo para el uso.

## Por proyecto
Ir a la carpeta `missing-dates` correr:
```sh
mvn -T 1C clean package && java -jar target/missing-dates-1.0.jar
```
Luego ir a la carpeta `Generador_Datos_Desafio_Uno/ApiPeriodos` y seguir las instrucciones. En resumen:
```sh
mvn package && java -jar target/api-periodos-1.0.0.jar
```

# Uso
Para hacer una consulta a la api ejecutar:
```sh
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8081/missing-dates'
```

# Documentación
Se puede ver swagger abriendo el navegador en la siguiente dirección:  
[http://localhost:8081/swagger-ui](http://localhost:8081/swagger-ui)