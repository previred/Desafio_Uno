# standard
import datetime
from dateutil.rrule import rrule, MONTHLY

# settings
from desafio import settings


def get_missing_dates(data):
    start_str = data.get('fechaCreacion')
    end_str = data.get('fechaFin')

    return_dict = {
        'id': data.get('id'),
        'fechaCreacion': start_str,
        'fechaFin': end_str,
        'fechas': data.get('fechas'),
        'fechasFaltantes': [],
    }

    start = datetime.datetime.strptime(
        start_str, settings.DATE_FORMAT
    )

    end = datetime.datetime.strptime(
        end_str, settings.DATE_FORMAT
    )

    dates = [dt for dt in rrule(MONTHLY, dtstart=start, until=end)]

    str_dates = [
        dt.strftime(settings.DATE_FORMAT) for dt in dates
        if dt not in (start_str, end_str)
    ]

    return_dict['fechasFaltantes'] = list(
        set(str_dates) - set(data.get('fechas'))
    )

    return_dict['fechasFaltantes'].sort()

    return return_dict
