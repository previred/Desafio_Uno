import sys
import json
from previlib import previlib


if __name__ == "__main__":
    input_json_str = "".join(sys.stdin.readlines())
    input_json = json.loads(input_json_str)
    print(json.dumps(previlib.obtener_fechas_faltantes(input_json), indent=4))
