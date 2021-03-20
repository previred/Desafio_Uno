.PHONY: python ogu elixir

p py python:
	cd python && python3 ./__main__.py

o ogu:
	cd ogu && java -jar ./ogu.jar -n ./main.ogu

e ex elixir:
	cd elixir && mix solution

i install:
	cd elixir && mix deps.get
