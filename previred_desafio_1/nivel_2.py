import sys
import json
import requests
from previlib import previlib


url_generador = 'http://localhost:5000/generador' #'http://127.0.0.1:8080/periodos/api'


if __name__ == "__main__":
    res = requests.get(url_generador, headers={'Accept': 'application/json'})
    out = {}
    if res.ok:
        entrada = res.json()

        out = previlib.obtener_fechas_faltantes(entrada)

        f = open("out_nivel_2.txt", "w", encoding='utf-8')
        f.write('fecha creación: ' + entrada['fechaCreacion'] + '\n')
        f.write('fecha fin: ' + entrada['fechaFin'] + '\n')
        f.write('fechas recibidas: ' + ", ".join(entrada['fechas']) + '\n')
        f.write('fechas recibidas: ' + ", ".join(out['fechasFaltantes']))
        f.close()
    else:
        raise Exception('json not ok')