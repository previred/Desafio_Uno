# -*- coding: utf-8 -*-

from flask import Flask, request
from flask_restplus import Api, Resource, fields
from namespaces.periods import period_namespace
from api_config.settings import *

flask_app = Flask(__name__)

app = Api(app=flask_app,
          version="1.0",
          title="Interview Challenge API",
          description="This service consumes GDD service and solves the challenge",
          doc='/api/docs/')

period_namespace(Resource, app)

if __name__ == '__main__':
    flask_app.run(debug=DEBUG, host=HOST, port=PORT)
