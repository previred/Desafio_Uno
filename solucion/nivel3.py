from flask import Flask, jsonify
from flask_restful import Resource, Api
import requests
import datetime, random
from datetime import date, timedelta
from dateutil.relativedelta import *

app = Flask(__name__)
api = Api(app)

class Nivel3(Resource):

    def get(self):
        try:
            response = requests.get( 'http://127.0.0.1:8080/periodos/api',   headers={'Accept':'application/json'})
        except:
            data = "Servicio de API no disponible en este momento"
            return  jsonify(data)
        if response.status_code == 200:
            data =  response.json()
            fecha_inicio = datetime.datetime.strptime(data['fechaCreacion'], '%Y-%m-%d')
            fecha_termino = datetime.datetime.strptime(data['fechaFin'], '%Y-%m-%d')
            meses = relativedelta(months=+1)
            listado_fechas_recibidas = []
            listado_fechas_faltantes = []

            for fecha in data['fechas']:
                fecha_inicio_string = str(fecha)
                listado_fechas_recibidas.append(fecha_inicio_string)
            while fecha_inicio.date() <= fecha_termino.date():
                fecha_inicio_string = str(fecha_inicio.date())
                if fecha_inicio_string not in listado_fechas_recibidas:
                    listado_fechas_faltantes.append(fecha_inicio_string)
                fecha_inicio += meses

            data["fechas"] = listado_fechas_recibidas
            data["fechasFaltantes"] = listado_fechas_faltantes
            return data


api.add_resource(Nivel3, '/')

if __name__ == '__main__':
    app.run(debug=True)
