# drf
from rest_framework.views import APIView
from rest_framework.response import Response

# requests
import requests

# utils
from api import gdd_utils


class GDDView(APIView):
    """
    List all snippets, or create a new snippet.
    """
    permission_classes = []

    def get(self, request, format=None):
        r = requests.get(
            'http://127.0.0.1:8080/periodos/api',
            headers={'Accept': 'application/json'}
        )
        return Response(gdd_utils.get_missing_dates(r.json()))
