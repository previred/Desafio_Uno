+ Proyecto para obtener fechas faltantes en lista generada por GDD.
+ Instrucciones:
- Clonar repositorio: https://github.com/previred/Desafio_Uno.git
- Acceder al directorio del repositorio descargado en nuestro equipo local.
- Compilar proyecto e instalar dependencias con la instruccion 'mvn clean install'.
- Se debe tener iniciado proyecto GDD en la direccion http://127.0.0.1:8080/. Instrucciones en link: https://github.com/previred/Desafio_Uno.git
+ Consumir endpoint: http://127.0.0.1:8031/v1/periodos
- CURL: curl -X GET 'http://127.0.0.1:8080/v1/periodos'
- POSTMAN: HTTP.GET 'http://127.0.0.1:8080/v1/periodos'
- No requiere RequestBody, servicio integrado con GDD.
+ jsonResponse.json en raiz del proyecto representa respuesta obtenida.