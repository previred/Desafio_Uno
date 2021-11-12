import sys
import json
from json.decoder import JSONDecodeError
import pandas as pd

#Obtain command line arguments
try: 
    infile = sys.argv[1]
    outfile = sys.argv[2]
except IndexError:
    print("Command syntax: desafio <name of input file> <name of output file>")
    sys.exit()

#Load input file
try:
    f = open(infile)
    datos = json.load(f)
    f.close()
except OSError:
    print ("File \"", infile, "\" could not be opened for reading.")
    sys.exit()
except JSONDecodeError:
    print("File \"", infile, "\" is not a valid JSON format file.")
    f.close()
    sys.exit()

#Calculate missing dates
try:
    fechas = datos["fechas"]
    fechas_full = pd.date_range(start=datos["fechaCreacion"], end=datos["fechaFin"], freq="MS").strftime("%Y-%m-%d").tolist()
    fechas_faltante = sorted(set(fechas_full).difference(fechas))
except KeyError:
    print("The JSON input file does not contain the necessary fields.")
    sys.exit()

#Compile output data
data_ret = {"id": datos["id"], "fechaCreacion": datos["fechaCreacion"], "fechaFin": datos["fechaFin"], "fechasFaltantes": fechas_faltante}
json_ret = json.dumps(data_ret, indent=4)

#Write output file
try:
    f = open(outfile, "w")
    f.write(json_ret)
except OSError:
    print ("File \"", outfile, "\" could not be opened for writing.")
    sys.exit()

f.close()
