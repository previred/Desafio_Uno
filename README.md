# Compilar y ejecutar el proyecto

Para compilar el proyecto se requiere Java y Maven instalado.

Primero, asegurarse de que *ApiPeriodos* este en ejecución.

Segundo, ingresar al directorio Desafio_Uno y ejecutar el comando maven package

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\gdd-0.0.1.jar
```

*Nota*:
Debe si *ApiPeriodos* tiene utilizado el puerto *8080* en el PC donde se ejecute esta API, deberá cambiar
el puerto para que esta se pueda ejecutar esto se puede hacer con el comando

```bash
java -jar .\gdd-0.0.1.jar –server.port=9090
```
O agregando *server.port=9090* en application.config


NOTA:
No tuve tiempo para usar swagger correctamente, realmente no lo conocía pero se ve bastante útil,
lo seguiré investigando por mi cuenta.