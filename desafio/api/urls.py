# django
from django.conf.urls import url

# views
from api import views

urlpatterns = [
    url(r'^GDD', views.GDDView.as_view()),
]
