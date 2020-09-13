# Desafio_Uno

Este proyecto resuelve el nivel 3 del desafío.

>## Nivel 3:
>
>Implementar un nuevo servicio REST. Este servicio REST debe invocar al servicio GDD y entregar la respuesta en formato JSON con las fechas recibidas y las fechas faltantes.
>Ejemplo de la respuesta que debería entregar:
>
>```json
>{
>    "id": 6,
>    "fechaCreacion": "1969-03-01",
>    "fechaFin": "1970-01-01",
>    "fechas": [
>      "1969-03-01",
>      "1969-05-01",
>      "1969-09-01",
>      "1970-01-01"],
>    "fechasFaltantes": [
>      "1969-04-01",
>      "1969-06-01",
>      "1969-07-01",
>      "1969-08-01",
>      "1969-10-01",
>      "1969-11-01",
>      "1969-12-01"]
>
>}
>```


# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *Desafio_Uno* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\desafio_uno-0.0.1-SNAPSHOT.jar
```
**Notas**:
- Es necesario tener montado el Generador de datos en el puerto *8080*
- Debe estar disponible el puerto *8090* en el PC donde se ejecute esta API

# Visualizar Documentación y consumir la API

La documentación swagger queda disponible en

http://127.0.0.1:8090/swagger-ui.html#/
