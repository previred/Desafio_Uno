from flask import Flask
from flask_restplus import Api, Resource

from main import get_lost_periods

flask_app = Flask(__name__)
app = Api(app=flask_app)

name_space = app.namespace('missing-dates', description='Desafio Uno API')


@name_space.route("/")
class MainClass(Resource):
    def get(self):
        return get_lost_periods()
