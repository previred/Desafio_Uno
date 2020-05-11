## Servicio Rest para generación de fechas faltantes en servicio Generador De Datos períodos Previred.

---

### Requisitos
1- Levantar el servicio Generador De Datos ó GDD períodos de previred con antelación.
2- Configurar url de servicio GDD como variable de entorno en el archivo **.env** de la raíz del proyecto. Para ello vaya a la raíz del proyecto y renombre el archivo .sample-env por env, luego establezca la url del servicio GDD en el .env como se indica o como esté disponible en el ambiente donde vaya a ejecutar la aplicación por ejemplo: 
```sh
URL_API=http://127.0.0.1:8080/periodos/api
```
3- Para compilar el proyecto se requiere [Node.js](https://nodejs.org/) V8.11 ó superior por lo que deberá instalarlo.

### Intalar dependencias:
 Ingresar al directorio raiz del proyecto y ejecutar el siguiente comando comando:
```sh
$ npm install
```
### Generación de build y levantar el servicio
Una vez instaladas las dependencias inicie el servidor ejecutando el siguiente comando:
```sh
$ npm start
```
Nota: Debe estar disponible el puerto 3000 en el PC donde se ejecute esta API

### Visualizar Documentación y consumir la API:
La documentación swagger del API (una vez que se levanta el API) queda disponible en:
http://localhost:3000/api/api-docs/


### NOTA: Archivos de entrada y salida:
En la raíz del proyecto existen dos archivos .json:
1- entrada.json (ejemplo de entrada)
2- salida.json (ejemplo de salida)