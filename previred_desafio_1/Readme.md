## Respuesta desafio 1 Previred

Como requisito debe tener instalado python 3

Antes de ejecutar debe tener instalado los modulos necesarios

```bash
pip install python-dateutil
pip install requests
pip install Flask
```

### Para ejecutar el Nivel 1

En la linea de comandos

```bash
python nivel_1 < nombre_archivo_entrada > nombre_archivo_salida
```

### Para ejecutar el Nivel 1

Edite el script nivel_2.py. Cambie la variable url_generador con la url correpondiente al 'Generador datos desafio 1'

En la linea de comandos

```bash
python nivel_2
```

### Para ejecutar el Nivel 3

Edite el script nivel_3.py. Cambie la variable url_generador con la url correpondiente al 'Generador datos desafio 1'  

Ejecute en la linea de comando en la carpeta donde esta nivel_3.py (https://flask.palletsprojects.com/en/2.0.x/quickstart/)

```bash
set FLASK_APP=nivel_3
Flask run
```

Con eso el servicio queda disponible

El servicio del generador se encuentra disponible en la url http://127.0.0.1:5000/