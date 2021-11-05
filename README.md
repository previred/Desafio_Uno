# Sistema Para Desafio Nivel 3 Previred. 

El sistema funciona mediante Springboot y java 8+ (Pom configurado para java 11). Se usan 2 controladores, uno para la vista y otro para la respuesta json solamente.

## Requerimientos

`ApiPeriodos.0.0.1.jar` entregado por Previred. Por defecto desplegado puero :8080


## Instalación
1.-Despues de descargarla carpeta, ejectutar cualquiera de los 2 comandos para obtener el .jar y ejecutar.

```bash
mvn clean install -U
```

```bash
mvn package -U
```

## Despliegue
```cmd
java -jar desafio-resultado-0.0.1.jar
```
## Configurable para docker u otro puerto
```cmd
java -jar -Xmx512m -Dspring.profiles.active=dev -Dserver.port=8282 -Dtimix.host.port=8282 desafio-resultado-0.0.1.jar
```



## Ejecución del Sistema
1.- Vista a travez de servidor web con html del resultado: `http://127.0.0.1:8282/`

2.- Url del servicio solicitado: `http://127.0.0.1:8282/rest/resultado`

3.- Url de Swagger `http://127.0.0.1:8282/swagger-ui.html`

## Comentarios
"Soy Benjamín y ha sido entrete el desafio. Me fui por un controlador mvc y otro rest, no sentí la necesidad de agregar bootstrap, springsecurity, springdatabase,etc. Es general es una vista html donde se ve el resultado. El enlace N°2 entrega la respuesta al requerimiento como json response. He visto algo de swagger, pero mentiria si dijera que lo he implementado la mayoria de mis proyectos. Esto ultimo tambien esta implementado. Saludos"