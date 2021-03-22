#!/usr/bin/python3

import sys
import json
from datetime import datetime
from dateutil.relativedelta import *

limite = 100

def leerJson(entrada):
    with open(entrada, 'r') as f:
        return json.load(f)

def obtenerFechas(fechaCreacion, fechaFin, fechas):
    rango_fechas = []
    fecha_inicio = datetime.strptime(fechaCreacion, '%Y-%m-%d').date()
    fecha_fin = datetime.strptime(fechaFin, '%Y-%m-%d').date()

    while fecha_inicio < fecha_fin:
        if str(fecha_inicio) not in fechas:
            rango_fechas.append(str(fecha_inicio))
            
        fecha_inicio = fecha_inicio + relativedelta(months=+1)

    return rango_fechas

def generarSalida(fechas, data):
    return {
        "id": data['id'],
        "fechaCreacion": data['fechaCreacion'],
        "fechaFin": data['fechaFin'],
        "fechasFaltantes": sorted(fechas)[0:limite]
    }

def escribirJson(salida, contenido):

    with open(salida, 'w') as f:
        f.write(json.dumps(contenido))
        f.close

def solucion(diccionario):

    return generarSalida(obtenerFechas(diccionario['fechaCreacion'], diccionario['fechaFin'], diccionario['fechas']), diccionario)

if __name__ == '__main__':
    parametros = len(sys.argv)
    entrada = 'entrada.json'
    salida = 'salida.json'
    
    if parametros > 1:
        entrada = sys.argv[1]
    if parametros > 2:
        salida = sys.argv[2]

    data = leerJson(entrada)
    escribirJson(salida, solucion(data))
