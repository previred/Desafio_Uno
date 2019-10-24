import os
import requests
from dateutil.parser import parse
from dateutil.relativedelta import relativedelta


def get_lost_periods():
    data = get_gdd_data()
    periods = generate_periods(data['fechaCreacion'], data['fechaFin'])
    dates = set(data['fechas'])
    missing_dates = list(periods - dates)
    missing_dates.sort()
    data.update({
        "fechasFaltantes": missing_dates,
    })
    return data


def get_gdd_data():
    url = os.getenv('GDD_URL', 'http://127.0.0.1:8080/periodos/api')
    r = requests.get(
        url,
        headers={'Accept': 'application/json'},
    )
    return r.json()


def generate_periods(begin, end):
    begin_date = parse(begin)
    end_date = parse(end)
    periods = set()
    iter_date = begin_date
    while iter_date < end_date:
        periods.add(iter_date.strftime("%Y-%m-%d"))
        iter_date += relativedelta(months=1)
    return periods
