#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
MIT License
-----------

Copyright (c) 2021 Camilo Castro <camilo@ninjas.cl>
Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
"""

import datetime
import json
import sys

from dateutil.relativedelta import relativedelta

ISO8601 = '%Y-%m-%d'
MAX_DATES_IN_OUTPUT = 100
TRIM_DATES_IF_MAX_REACHED = True

class Output:
    def __init__(self, uid, start, end, dates):
        self.uid = uid
        self.start = start.strftime(ISO8601)
        self.end = end.strftime(ISO8601)
        self.dates = dates

        dates_length = len(dates)
        if dates_length > MAX_DATES_IN_OUTPUT:
            print(f"WARN: Max Dates Reached. {dates_length}/{MAX_DATES_IN_OUTPUT}")
            if TRIM_DATES_IF_MAX_REACHED:
                self.dates = self.dates[0:MAX_DATES_IN_OUTPUT]
    
    def toDict(self):
        return {
            "id": self.uid,
            "fechaCreacion": self.start,
            "fechaFin": self.end,
            "fechasFaltantes": self.dates
        }
    
    def toJson(self):
        return json.dumps(self.toDict())
    
    def save(self, name = "output.json"):
        with open(name, "w") as file:
            file.write(self.toJson())
            file.close()

    def __str__(self):
        return self.toJson()

def parse_date(date):
    return datetime.datetime.strptime(date, ISO8601)

def read_input(name):
    with open(name, "r") as file:
        return json.load(file)

def get_dates_from(start_at, end_at):
    # Since a month can have 30, 31 or 28 days we need a special delta
    time_step = relativedelta(months=1)

    # We have to stablish the first day to standarize the output
    next_month = start_at.replace(day=1) + time_step

    result = []

    while start_at < end_at:
        result.append(start_at.strftime(ISO8601))
        start_at = next_month
        next_month += time_step
    
    return result

def main(argc, argv):
    file_input = "input.json"
    file_output = "output.json"

    if argc == 2:
        file_input = argv[1]
    
    if argc == 3:
        file_output = argv[2]

    try:
        # Read JSON data
        params = read_input(file_input)

        uid = params["id"]
        start_at = parse_date(params["fechaCreacion"])
        end_at = parse_date(params["fechaFin"])
        available_dates = params["fechas"]

        # Execute Algorithm
        all_dates = get_dates_from(start_at, end_at)
        final_dates = sorted(list(set(all_dates) - set(available_dates)))

        # Final Output
        output = Output(uid, start_at, end_at, final_dates)
        output.save(file_output)

    except Exception as ex:
        print(ex)


if __name__ == "__main__":
    # $ python3 . <input?> <output?>
    main(len(sys.argv), sys.argv)