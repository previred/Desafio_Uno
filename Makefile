.PHONY: py ogu

p py:
	cd python && python3 ./__main__.py

o ogu:
	cd ogu && java -jar ./ogu.jar -n ./main.ogu
