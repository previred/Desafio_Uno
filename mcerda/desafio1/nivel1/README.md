# Nivel 1

Respuesta al nivel 1 del desafio 1 para postular a previRed

* Autor: Marco Cerda Veas
* Correo: marco.cerda.veas@gmail.com

## Requerimientos

* Java 8
* Maven 3.6

## Instalar

```bash
cd Desafio_Uno/mcerda/desafio1/nivel1
mvn clean install

```

## pruebas

```bash
mvn test

```

## Configuración

Se maneja los ambientes local, dev, qa y prod. Esto se configura con la variable de entorno PREVIRED_D1_NIVEL1_ENV con los siguientes valores:
* local (es por defecto)
* dev
* qa
* prod

```bash
export PREVIRED_D1_NIVEL1_ENV=local

```

Para configuración local se debe crear el archivo src/main/resources/filter/local.properties.

El archivo local.properties no se sube al repositorio ya que podría contener información sensible que no se debe dejar en los repositorio por seguridad. Todo se debe manejar por variables de entornos o secretos como vault.

loca.properties

```properties
ENV_NAME=${ENV_NAME:local}

```


### Ejecutar

Para ejecutar correctamente se hace por el script mcerda_solucion.sh que recibe dos parametros. Primero el archivo de entrada y segundo el archivo de salida.

En la carpeta src/test/resources se encuetra un archivo de ejemplo de entrada con nombre basicTest.json


Ejemplo de ejecución

```bash
./mcerda_solucion.sh /home/bodegafresh/test.json /home/bodegafresh/salida.json

```


