import datetime
from dateutil.relativedelta import relativedelta


def parse_str_date(date_str):
    return datetime.datetime.strptime(date_str, '%Y-%m-%d')


def format_date(date_in):
    return date_in.strftime('%Y-%m-%d')


def buscar_y_remover_fecha(fechas_in, fecha):
    f = 0
    found = False
    while f < len(fechas_in):
        if fechas_in[f] == fecha:
            fechas_in.pop(f)
            found = True
            break
        f = f + 1

    return found


def obtener_fechas_faltantes(in_json):
    id = in_json['id']
    fecha_creacion = parse_str_date(in_json['fechaCreacion'])
    fecha_fin = parse_str_date(in_json['fechaFin'])

    fechas_in = []
    fechas_in_str = in_json['fechas']
    for fecha_in_str in fechas_in_str:
        fechas_in.append(parse_str_date(fecha_in_str))

    #print(f'id: {id} fc: {fecha_creacion} ff: ´{fecha_fin} fs: {fechas_in}')

    fechas_out = []
    fecha_aux = fecha_creacion
    while fecha_aux <= fecha_fin:
        found = buscar_y_remover_fecha(fechas_in, fecha_aux)

        if not found:
            fechas_out.append(format_date(fecha_aux))

        fecha_aux = fecha_aux + relativedelta(months=+1)

    return {
        'id': id,
        'fechaCreacion': format_date(fecha_creacion),
        'fechaFin': format_date(fecha_fin),
        'fechasFaltantes': fechas_out
    }
