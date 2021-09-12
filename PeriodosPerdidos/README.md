Desafío resuelto en .NET 5.0

Para hacerlo funcionar, instalar visual studio community 2019.
https://visualstudio.microsoft.com/

abrir la solución y compilar.

esta organizado por carpeta.
carpeta api --> api que resuelve las fechas faltantes
carpeta Nivel01 --> consola que resuelve el Nivel 1
carpeta Nivel02 --> consola que resuelve el Nivel 2
carpeta Nivel03 --> api que resuelve el Nivel 3

para iniciar cada proyecto, se debe hacer click derecho en cada proyecto, seleccionar set as start proyect, presionar play, iniciara la aplicación web o la consola.

https://localhost:xxxx/swagger --> el puerto te lo autoasignara el visual studio

Se utilizo arquitectura limpia para resolver el problema.
Se uso inyección de dependencia.
se usa el patrón mediator entre las capas.
se usa el patron circuit breaker para la invocación http hacia el api GDD.


se resolvió el api que entrega las fechas restantes
con un máximo de 100 fechas como resultado, se eliminan aleatoriamente las sobrantes, 
si se invoca 2 veces, el resultado siempre será distinto.

El api de GDD se instaló en Azure, los niveles se resolvieron apuntando al servicio publicado en Azure.

el nivel 1, está resuelto tomando el archivo en la carpeta.

//PeriodosPerdidos.Nivel01/Files/Request.json

el archivo de salida queda en la siguiente carpeta.

C:\Temp\Result_Nivel01_2021-09-12.json

nivel 1, reutiliza el negocio del api GDD, y se escribe el archivo.
la consola puede ser convertida en una imagen de Docker para luego instalarlo en cualquier plataforma (net core corre sobre linux o windows) o a un servicio windows.

para el nivel 2, se conecta al api que se publicó en Azure.
para conectarse se usa una pieza que construí hace tiempo que usa el patrón de circuit breaker utilizando la librería de polly.

parecido al nivel 1, toma el archivo en la carpeta files, se lee el archivo, se serializa, se invoca al api, se obtiene el resultado, se serializa el resultado, y se escribe el archivo txt en la carpeta temp del equipo.

C:\Temp\Result_Nivel01_2021-09-12.txt

el nivel 2 está totalmente independiente, solo usa la librería common, donde agregue el helper donde implemente el patron circuit breaker

para el nivel 3, se usa el patrón mediador, se reutiliza el negocio, pero se crea otro procesador independiente al api GDD, el invoca al api, serializa el resultado y prepara el resultado combinado, para responder.

para probar los niveles 2 y 3, con otra api que deseas invocar, debes modificar el archivo appsettings.json , ahí se encuentra la url de Azure, puedes cambiarla apuntando a tu local, ahí debes iniciar proyectos múltiples.

URL API GDD EN AZURE
https://periodos-perdidos-previred.azurewebsites.net/swagger/index.html

URL API Nivel 3 EN AZURE
https://periodos-perdidos-previred-nivel03.azurewebsites.net/swagger/index.html
