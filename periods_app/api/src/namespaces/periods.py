# -*- coding: utf-8 -*-

from datetime import datetime, timedelta
import requests
import json
from api_config.settings import SOURCEAPI_URL, SOURCEAPI_HEADERS


def period_namespace(Resource, app):

    Periods = app.namespace(
        'periods', description='Execute task to run the challenge solution')

    @Periods.route("")
    class PeriodsClass(Resource):

        def get(self):

            response = requests.get(SOURCEAPI_URL, headers=SOURCEAPI_HEADERS)

            if response:
                json = response.json()
            else:
                print('Error interno.')

            start_s = json['fechaCreacion']
            end_s = json['fechaFin']
            dates_l = json['fechas']
            time_format = '%Y-%m-%d'

            start_d = datetime.strptime(start_s, time_format)

            result = []

            def get_next_month(date):
                month = (date.month % 12) + 1
                year = date.year + (date.month + 1 > 12)
                return datetime(year, month, 1)

            current = start_d

            while len(result) <= 100:
                string_date = datetime.strftime(current, time_format)
                if string_date not in dates_l:
                    result.append(string_date)
                    if string_date == end_s:
                        break
                current = get_next_month(current)

            json["fechasFaltantes"] = result

            return json
