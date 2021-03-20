.PHONY: py ogu

p py python:
	cd python && python3 ./__main__.py

o ogu:
	cd ogu && java -jar ./ogu.jar -n ./main.ogu

e ex elixir:
	cd elixir && mix solution