from flask import Flask, Response
import os
import logging
from dotenv import load_dotenv
import connexion
from flask_cors import CORS


connx_app = connexion.App(__name__, specification_dir='api/')

# Read the api_spec.yaml file to configure the endpoints
#Lee el archivo api_spec.yaml para mapear los endopints de la api
connx_app.add_api('api_spec.yaml')

app = connx_app.app

#no muy seguro pero se sluciona agregando los host o ip permitidos
cors = CORS(app, resources={r"*": {"origins": "*"}})

logging.basicConfig(filename='logger.log',level=logging.DEBUG)
logger = logging.getLogger(__name__)

#carga las variables de entorno desde el archivo .env
load_dotenv()

#Se puede mejorar usando por ejemplo nginx
# pero lo uso así para mantener la solución mas sencilla
if __name__ == '__main__':
    app.run(host='0.0.0.0')