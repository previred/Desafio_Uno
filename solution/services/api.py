# Native
from datetime import datetime

# Django
from django.http import JsonResponse

# Rest
from rest_framework import status
from rest_framework.parsers import JSONParser
from rest_framework.views import APIView

# Internal
from services.utils.connection import dispatcher_data_json
from services.utils.dates import month_delta


class FixDatesView(APIView):
    """
        Arreglas los datos de fecha inicio y fecha final.

        Author
        -------
            - Miguel Sánchez Padilla

        Last Modification
        -----------------
            - 11/09/2019
    """
    authentication_classes = ()
    permission_classes = ()
    parser_classes = (JSONParser,)

    def get(self, request):
        """
            Consulta haciendo un request al api de GGD
        """
        result = None
        response = self.catch_request_ggd()

        if "data" in response:
            data = response.get('data', {})
            result = self.fix_date(data)

        if result:
            return JsonResponse(
                {'status': 'OK', 'data': result},
                status=status.HTTP_200_OK
            )

        return JsonResponse(
            {'status': 'ER'},
            status=status.HTTP_400_BAD_REQUEST
        )

    def post(self, request):
        """
            Recibe un json para arreglar los datos.
        """
        result = None
        data = request.data

        # Tiene que ser un diccionario para entrar.
        if data and isinstance(data, dict):
            # Repara los datos
            result = self.fix_date(data)
        else:
            return JsonResponse(
                {'status': 'ER', 'type': 'Error del formato.'},
                status=status.HTTP_400_BAD_REQUEST
            )

        if result:
            return JsonResponse(
                {'status': 'OK', 'data': result},
                status=status.HTTP_200_OK
            )

        return JsonResponse(
            {'status': 'ER'},
            status=status.HTTP_400_BAD_REQUEST
        )

    def fix_date(self, data):
        """
            Requisitos
            ----------
            Revisar las fechas faltantes por mes

            Author
            -------
                - Miguel Sánchez Padilla

            Last Modification
            -----------------
                - 11/09/2019
        """
        format_date = '%Y-%m-%d'
        start_date_string = data.get('fechaCreacion')
        end_date_string = data.get('fechaFin')
        dates_data = data.get('fechas')
        missing_dates = []

        if not start_date_string or not end_date_string:
            return None

        start_date = datetime.strptime(start_date_string, format_date)
        end_date = datetime.strptime(end_date_string, format_date)

        # Calcula los meses entre dos fechas
        many_months = month_delta(start_date, end_date)

        try:
            # Fecha inicial
            next_date = start_date

            # Recorrera los meses calculados
            for month_complete in range(many_months + 1):
                missing_dates.append(next_date.strftime(format_date))

                # El mes 12 es el cambio de año
                if next_date.month != 12:
                    next_date = next_date.replace(month=next_date.month + 1)
                else:
                    next_date = next_date.replace(year=next_date.year + 1, month=1)

            # Elimina los datos duplicados
            missing_dates = list(set(missing_dates) - set(dates_data))

            # Ordena las fechas
            missing_dates = sorted(missing_dates, key=lambda x: datetime.strptime(x, format_date))

            # Agrega al data las fechas faltantes
            data.update({'fecha_faltantes': missing_dates})
        except Exception:
            print('Se ingreso mal el formato.')

        return data

    def catch_request_ggd(self):
        """
            Request donde hara la consulta a GGD

            Author
            -------
                - Miguel Sánchez Padilla

            Last Modification
            -----------------
                - 11/09/2019
        """
        URL = 'http://localhost:8080/periodos/api'
        return dispatcher_data_json('GET', URL)
