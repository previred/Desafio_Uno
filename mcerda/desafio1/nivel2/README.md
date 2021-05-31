# Nivel 2

Respuesta al nivel 2 del desafio 1 para postular a previRed

* Autor: Marco Cerda Veas
* Correo: marco.cerda.veas@gmail.com

## Requerimientos

* Java 8
* Maven 3.6
* Proyecto https://github.com/previred/Generador_Datos_Desafio_Uno

## Instalar

```bash
cd Desafio_Uno/mcerda/desafio1/nivel2
mvn clean install

```

## pruebas

```bash
mvn test

```

## Configuración

Se maneja los ambientes local, dev, qa y prod. Esto se configura con la variable de entorno PREVIRED_D1_NIVEL2_ENV con los siguientes valores:
* local (es por defecto)
* dev
* qa
* prod

```bash
ENV_NAME=${ENV_NAME:local}

GDD_BASE_URL=http://localhost:8080/periodos

```

Para configuración local se debe crear el archivo src/main/resources/filter/local.properties.

El archivo local.properties no se sube al repositorio ya que podría contener información sensible que no se debe dejar en los repositorio por seguridad. Todo se debe manejar por variables de entornos o secretos como vault.

loca.properties

```properties
ENV_NAME=${ENV_NAME:local}

```

### Generador Datos Desafio

Se debe configurar y levantar la api Generador_Datos_Desafio.

```bash
git clone https://github.com/previred/Generador_Datos_Desafio_Uno
cd Generador_Datos_Desafio_Uno/ApiPeriodos
mvn clean install
java -jar target/api-periodos-1.0.0.jar

```



### Ejecutar

Para ejecutar correctamente se hace por el script mcerda_solucion.sh.

El resultado lo deja en la carpeta docs

Ejemplo de ejecución

```bash
./mcerda_solucion.sh

```


