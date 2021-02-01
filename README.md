# Previred Desafío 1: Periodos perdidos

Primer desafío enviado por Previred para prueba de Proceso de Selección para Ingeniero desarrollo Junior.

Programa escrito en `node.js`, que substrae fechas de entre otro rango de fechas, a partir de una API Rest (local) escrita en Java Spring Boot.

Archivo generado de entrada está ubicado en `./assets/json/input-1612170182871.json` y de salida en `./assets/json/output.json`.

## Requerimientos

- Sistema Operativo compatible con node.js
- node.js v12.16.3
- axios 0.21.1
- Para levantar API GDD, [estas instrucciones](https://github.com/previred/Generador_Datos_Desafio_Uno), y, en el archivo `./Generador_Datos_Desafio_Uno/ApiPeriodos/pom.xml`, el siguiente código, inmediatamente antes de la tag `</dependencies>`, ya que no pude compilarlo sólo con `mvn package` (MacOS Catalina 10.15.7, Java 15.0.1, Maven 3.6.3):

  ```xml
  <dependency> <groupId>javax.xml.bind</groupId> <artifactId>jaxb-api</artifactId> <version>2.3.0</version> </dependency>
  ```

## Instalación

1. Instalar [node.js](https://nodejs.org/es/download/)
2. Clonar repositorio

   ```shell
   git clone https://github.com/nufrankz/Desafio_Uno.git
   ```

3. Dirigirse al directorio del repositorio

   ```shell
   cd /path/to/Desafio_Uno
   ```

4. Instalar dependencias

   ```shell
   npm i
   ```

## Uso

En la carpeta raíz de este repositorio, escribir:

```shell
node desafio_uno <endpoint_url> archivo_de_salida.json
```

La ejecución de `desafio_uno` genera automáticamente un archivo `input-[timestamp].json` en `./assets/json/` con el contenido generado por la API GDD.

### Parámetros

`endpoint_url`: Endpoint URL a consumir, actualmente fija en la dirección generada por GDD nativo.

`archivo_de_salida.json`: Nombre del archivo a generar, ubicado en `./assets/json/`.

## Datos Commit

**Nombre:** Francisco Andrés Cerda Sepúlveda

**Correo:** francisco.cerda@gmail.com

**Me enteré por:** Selección Beca Talento Digital

Espero quedar seleccionado :)

TODO: pull request
