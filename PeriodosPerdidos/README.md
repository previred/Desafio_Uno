# Solución a Desafío 1: Periodos perdidos

Este proyecto expone un API REST que es la solución del desafio en "Nivel 3", es decir, se desarrolla un servicio REST el cual consume a la API REST Generador Datos Desafio (GDD) para generar la respuesta respectiva en formato JSON solicitado.

## Compilar y ejecutar el proyecto

Para compilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *"PeriodosPerdidos"* ejecutar el siguiente comando *maven* (desde la terminal)

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* (../PeriodosPerdidos/target) ejecutar el siguiente comando *java* (desde la terminal)

```bash
java -jar ./PeriodosPerdidos-1.0.0.jar
```
*Nota*:
Debe estar disponible el puerto *:10080* en el PC donde se ejecute esta API y se debe estar ejecutando en primera instancia y a su vez la [API Generador Datos Desafio](https://github.com/previred/Generador_Datos_Desafio_Uno) localmente en el puerto *:8080* para que esta sea consumida correctamente por esta solución que genera la respuesta.

## Consumir la API

Para probar y consumir el servicio se puede invocar de diversas formas, entre ellas:

- El siguiente comando desde una terminal muestra la respuesta del servicio dentro de la misma terminal
```bash
curl -X GET 'http://localhost:10080/cxf/rest/previred/obtenerPeriodosPerdidos'
```

- La siguiente URL desde Postman o similar, señalando que se consumira un servicio tipo *"GET"*
```postman
http://localhost:10080/cxf/rest/previred/obtenerPeriodosPerdidos
```

- El siguiente comando desde una terminal genera la respuesta en un archivo .json y lo guarda en el escritorio
```bash
curl -s 'http://localhost:10080/cxf/rest/previred/obtenerPeriodosPerdidos' > ~/Desktop/obtenerPeriodosPerdidos-salida.json
```

## License

Desarrollado por [Matias I. Muñoz Soto](https://www.linkedin.com/in/matmunoz/).


