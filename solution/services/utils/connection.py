# ESTANADAR
import json
import logging

# HERRAMIENTAS EXTERNAS
import requests

# ERROR MANAGER
from requests.exceptions import ConnectionError

__version__ = '1.0.0'

logging.basicConfig(format='%(asctime)s \n %(message)s', level=logging.ERROR)

STATUS_CODES_SUCCESS = [
    requests.codes.ok,
    requests.codes.created,
    requests.codes.accepted,
    requests.codes.non_authoritative_info,
    requests.codes.non_authoritative_information,
    requests.codes.no_content,
    requests.codes.reset_content,
    requests.codes.reset,
    requests.codes.partial_content,
    requests.codes.partial,
    requests.codes.multi_status,
    requests.codes.multiple_status,
    requests.codes.multi_stati,
    requests.codes.multiple_stati,
    requests.codes.already_reported,
    requests.codes.im_used
]


def dispatcher_data_json(method, url, data=None, extra={}):
    """
    Despachador de datos solo para formato json y en metodo GET.

    PARAMETER
    ---------
        method: string (GET)
        url: string
        data: dict
        extra: dict (HEADERS settings)

    RETURN
    ------
        Sucess:
            dict {status: bool, data: dict, code: int}

        Error:
            dict {status: bool, code: int}

    Author
    -------
        - Miguel Sánchez Padilla

    Last Modification
    -----------------
        - 11/09/2019

    """
    try:
        headers = {'Content-Type': 'application/json'}
        response = requests.request(
            method=method,
            url=url,
            params=data,
            headers=headers
        )
        if response.status_code in STATUS_CODES_SUCCESS:
            return {'status': True, 'data': json.loads(response.text), 'code': response.status_code}
        else:
            return {'status': False, 'data': json.loads(response.text), 'code': response.status_code}
    except TimeoutError:
        logging.error("Exception occurred", exc_info=True)
        return {'status': False, 'code': 500, 'timeout': True}
    except ConnectionError:
        logging.error("Exception occurred", exc_info=True)
        return {'status': False, 'code': 500}
    except Exception:
        logging.error("Exception occurred", exc_info=True)
        return {'status': False, 'code': 500}
