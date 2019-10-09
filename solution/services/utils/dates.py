from calendar import monthrange
from datetime import timedelta


def month_delta(date_1, date_2):
    """
        Devuelve la diferencia de 2 años en meses
    """
    delta_month = 0
    while True:
        _days = monthrange(date_1.year, date_1.month)[1]
        date_1 += timedelta(days=_days)
        if date_1 <= date_2:
            delta_month += 1
        else:
            break
    return delta_month
