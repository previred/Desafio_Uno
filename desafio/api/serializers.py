from rest_framework import serializers
from desafio import settings


class DateListField(serializers.ListField):
    child = serializers.DateField(
       settings.DATE_FORMAT
    )


class GDDSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    fechaCreacion = serializers.DateField()
    fechaFin = serializers.DateField()
    fechas = DateListField()
    fechasFaltantes = DateListField()
