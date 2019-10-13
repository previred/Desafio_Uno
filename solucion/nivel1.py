import json
import datetime, random
from datetime import date, timedelta
import io
from dateutil.relativedelta import *

class Nivel1:

    def ouput_fechas(self):
        with open('input.json') as json_file:
            data = json.load(json_file)
            fecha_inicio = datetime.datetime.strptime(data['fechaCreacion'], '%Y-%m-%d')
            fecha_termino = datetime.datetime.strptime(data['fechaFin'], '%Y-%m-%d')
            meses = relativedelta(months=+1)
            listado_fechas_input = []
            listado_fechas_output = []
            for fecha in data['fechas']:
                listado_fechas_input.append(fecha)
            while fecha_inicio <= fecha_termino:
                fecha_inicio_string = str(fecha_inicio.date())
                if fecha_inicio_string not in listado_fechas_input:
                    listado_fechas_output.append(fecha_inicio_string)
                fecha_inicio += meses
        data["fechas"] = listado_fechas_output
        print(json.dumps(data, indent=4))


nivel1 = Nivel1()
nivel1.ouput_fechas()

