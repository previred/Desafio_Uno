import datetime, random
from datetime import date, timedelta
from dateutil.relativedelta import *
import requests, json

class Nivel2:
    #def setear_fechas(self):
    def leer_gdd(self):
        try:
            response = requests.get( 'http://127.0.0.1:8080/periodos/api',   headers={'Accept':'application/json'})
        except:
            data = "Favor de Iniciar el Servicio"
            return  print(data)
        if response.status_code == 200:
            data =  response.json()
            fecha_inicio = datetime.datetime.strptime(data['fechaCreacion'], '%Y-%m-%d')
            fecha_termino = datetime.datetime.strptime(data['fechaFin'], '%Y-%m-%d')
            fecha_inicio_real = fecha_inicio
            fecha_termino_real = fecha_termino
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
            print(("fecha creación: {}").format(str(fecha_inicio_real.date())))
            print(("fecha fin: {}").format(str(fecha_termino_real.date())))
            print("fechas recibidas:")
            for fecha_recibida in listado_fechas_recibidas:
                print(fecha_recibida+",", end=" ")
            print("\n")
            print("fechas faltantes:")
            for fecha_faltante in listado_fechas_faltantes:
                print(fecha_faltante+",", end=" ")
            print("\n")


nivel2 = Nivel2()
nivel2.leer_gdd()
