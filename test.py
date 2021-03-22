import unittest
from previred import solucion

class PreviredTest(unittest.TestCase):
    def test(self):
        entrada = {
            "id": 6,
            "fechaCreacion": "1969-03-01",
            "fechaFin": "1970-01-01",
            "fechas": [
                "1969-03-01",
                "1969-05-01",
                "1969-09-01",
                "1970-01-01"
            ]
        }

        salida = {
            "id": 6,
            "fechaCreacion": "1969-03-01",
            "fechaFin": "1970-01-01",
            "fechasFaltantes": [
                "1969-04-01",
                "1969-06-01",
                "1969-07-01",
                "1969-08-01",
                "1969-10-01",
                "1969-11-01",
                "1969-12-01"
            ]
        }

        resultado = solucion(entrada)

        assert resultado == salida

if __name__ == '__main__':
    unittest.main()
