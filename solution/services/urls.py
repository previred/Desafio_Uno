from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns
from services import api

urlpatterns = [
    url(r'^util/fix_dates/$', api.FixDatesView.as_view(), name='util_fix_dates'),
]

urlpatterns = format_suffix_patterns(urlpatterns)
