import sys
import json
import requests
from flask import Flask, request, jsonify
from previlib import previlib


url_generador = 'http://localhost:5000/generador' #'http://127.0.0.1:8080/periodos/api'
app = Flask(__name__)


@app.route('/generador', methods=['GET'])
def generador():
    filename = "prueba_nivel_1.json"

    # Open the file as f.
    # The function readlines() reads the file.
    with open(filename) as f:
        content = "".join(f.readlines())
    return content


@app.route('/', methods=['GET'])
def obtener_fechas_faltantes():
    res = requests.get(url_generador, headers={'Accept': 'application/json'})
    out = {}
    if res.ok:
        entrada = res.json()

        fechas_in = []
        fechas_in_str = entrada['fechas']
        for fecha_in_str in fechas_in_str:
            fechas_in.append(fecha_in_str)

        out = previlib.obtener_fechas_faltantes(entrada)

        out['fechas'] = fechas_in
        return jsonify(out)
    else:
        raise Exception('not ok')