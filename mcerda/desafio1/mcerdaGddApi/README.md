# mcerda-gdd-api

Api para solución nivel 3 del desafio 1

* Autor: Marco Cerda Veas
* Correo: marco.cerda.veas@gmail.com

## Requerimientos

* Java 8
* Maven 3.6
* Proyecto https://github.com/previred/Generador_Datos_Desafio_Uno

## Instalar

```bash
cd Desafio_Uno/mcerda/desafio1/mcerda-gdd-api
mvn clean install

```

## pruebas

```bash
mvn test

```

## Configuración

Se maneja los ambientes local, dev, qa y prod. Estos son los perfiles que se pueden utilizar:
* local (es por defecto)
* dev
* qa
* prod



Para configuración local se debe crear el archivo src/main/resources/filter/local.properties.

El archivo local.properties no se sube al repositorio ya que podría contener información sensible que no se debe dejar en los repositorio por seguridad. Todo se debe manejar por variables de entornos o secretos como vault.

loca.properties

```properties
ENV_NAME=${ENV_NAME:local}

GDD_BASE_URL=http://localhost:8080/periodos

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

Es un proyecto maven que se puede ejecutar de la siguiente manera con perfil local.

```bash
cd Desafio_Uno/mcerda/desafio1/mcerda-gdd-api
mvn clean install
mvn -Plocal -DskipTests spring-boot:run -Dspring-boot.run.profile=local

```

Si se necesita ejecutar en otro ambiente se debe cambiar -Plocal por -P<local o dev o qa o prod> y también -Dspring-boot.run.profile=local por -Dspring-boot.run.profile=<local o dev o qa o prod>.

Por defecto está local