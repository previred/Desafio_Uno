from flask import Response
import logging
from bson.json_util import dumps, loads
import requests
import json
from datetime import date, datetime
from dateutil.relativedelta import relativedelta
import os
from dotenv import load_dotenv


#carga las variables de entorno desde el archivo .env
load_dotenv()

#obtiene la variable de entorno con el endpoint al servicio java
java_endpoint = os.environ.get('PERIODOS_JAVA_ENDPOINT')

#metodo para encontrar los periodos perdidos
#autor: Felipe Aillon Sanhueza
def api_home():
    return "Bienvenidos al desafio Previred NIvel 3"

#metodo para encontrar los periodos perdidos
#segun una fecha de inicio y fin
#autor: Felipe Aillon Sanhueza
def encontrar_periodos_faltantes():

    headers = {
        "Accept": "application/json"
    }

    logging.info("[encontrar_periodos_faltantes]")
    logging.info("JAVA Endpoint")
    logging.info(java_endpoint)

    try:
        #peticion get al servicio java
        response = requests.get(java_endpoint, headers=headers)

        #se logea status y texto de la respuesta del servicio java
        logging.info("status code")
        logging.info(response.status_code)

        logging.info("text")
        logging.info(response.text)

        #se guarda la respuesta en formato json
        data = response.json()

        #se envia json a metodo para encontrar periodos faltantes
        fechas_faltantes = procesar_fechas(data)
        
        #se agrega key al json con las fechas faltantes
        data["fechasFaltantes"] = fechas_faltantes
    
    except Exception as e:
        return Response(json.dumps({"error": str(e.args)}) , mimetype="application/json" , status=400)

    return Response(json.dumps(data) , mimetype="application/json" , status=200)

#metodo para encontrar fechas faltamtes
def procesar_fechas(json_fechas):

    logging.info("[procesar_fechas]")

    #se convierten fecha inicio y fecha fin a formato date
    fecha_inicio = datetime.strptime(json_fechas["fechaCreacion"], "%Y-%m-%d")
    fecha_fin = datetime.strptime(json_fechas["fechaFin"], "%Y-%m-%d")

    #se obtiene arreglo con las fechas devueltas por el servicio java
    fechas = json_fechas["fechas"]
    
    # se crea arreglo para guardar fechas faltantes
    fechas_faltantes = []

    #ciclo para iterar agregando un mes a la fecha de inicio hasta que 
    #alcance la fecha de fin
    while(fecha_inicio <= fecha_fin):

        #se convierte fecha inicio a str para buscarla en el arreglo de 
        #fechas devuelto por servicio java
        fecha_inicio_str = fecha_inicio.strftime("%Y-%m-%d")
  
        #si la fecha no esta en el arreglo devuelto
        #se agrega a las fechas faltantes
        if fecha_inicio_str not in fechas:
            fechas_faltantes.append(fecha_inicio_str)
    
        #se agrega un mes a la fecha de inicio
        fecha_inicio = fecha_inicio + relativedelta(months=+1)

    return fechas_faltantes
