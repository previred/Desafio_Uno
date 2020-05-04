
# Obtener Fechas Faltantes
Este proyecto expone un API REST que entrega la siguiente información:

***id*:** identificador
***fechaCreacion*:** Fecha de inicio de la secuencia
***fechaFin*:** Fecha de fin de la secuencia
***fechas*:** Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
**fechasFaltantes:** Fechas que faltan para el rango entregado en "fechas"
Ejemplo.
```json
{
    "id": 6,
    "fechaCreacion": "1969-03-01",
    "fechaFin": "1970-01-01",
    "fechasFaltantes": [
      "1969-04-01",
      "1969-06-01",
      "1969-07-01",
      "1969-08-01",
      "1969-10-01",
      "1969-11-01",
      "1969-12-01"]
      
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd
El servicio entrega 1 periodos, el periodo contiene una fecha inicial una fecha final, una lista fechas y la **lista de fechas faltantes**

# Detalle de los sistemas

Java 8
JAX-RS con Jersey
Maven 3
Cargo Tomcat plugin


# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *Desafio1* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ejecutar el siguiente comando en la misma carpeta

```bash
mvn cargo:run
```
*Nota*:
Esta API se ejecutará en el puerto **8082** para dejar libre el puerto del Generador de Datos. Es requisito que el Generador de Datos esté publicado en el puerto **8080**

# Consumir la API

El API quedará publicado en la siguiente dirección

http://127.0.0.1:8082/Desafio1/webapi/fechasfaltantes

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8082/Desafio1/webapi/fechasfaltantes'
```

