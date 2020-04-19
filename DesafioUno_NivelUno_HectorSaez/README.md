# Programa: Periodos perdidos

	Datos Basicos:
   		Eclipse IDE for Enterprise Java Developers.
		Version: 2019-09 R (4.13.0)
		Build id: 20190917-1200
		OS: Windows 10, v.10.0, x86_64 / win32
		Java version: 1.8.0_241
		Libreria externa: Json Simple version 1.1.1 (permite parsear archivos (json) en objetos java)
	
	Ejecucion:
	
		Forma 1: 
			Ejecutar librería 	desafiouno_nivel1_hectorsaez.jar, en directorio "jar" del proyecto de la forma
		
			java -cp desafiouno_nivel1_hectorsaez.jar;json-simple-1.1.1.jar cl.previred.lp.LostPeriods input.json output.json
	
		Forma 2:	 
			Ejecutar clase LostPeriods, previa compilación, ya sea a traves de un IDE o consola
			java.exe -classpath "\bin;\lib\json-simple-1.1.1.jar" cl.previred.lp.LostPeriods input.json output.json	

## Nivel 1:

	Detalle de logica utilizada
		1.-El programa revise 2 argumentos como entrada, archivo de lectura y escritura, ambos obligatorios y de tipo json.
			La clase principal se llama LostPeriods
		2.-Se parsea el archivo de entrada como obejeto JSON con la librería Json Simple
		3.-Se lee la información de fechas de inicio y termino, generando un conjunto con el rango completo
		4.-Se hace comparacion de conjuntos, del conjunto completo de fechas vs fechas de entradas.
		5.-La diferencia en las fechas nos entrega los periodos perdidos, de forma ordenada.
		6.-Se genera el archivo de salida, con información del archivo de entrada y las fechas con los periodos perdidos.
		
	Consideraciones
	
		1.-El programa no debiese caerse con archivos invalidos o con formatos erroneos, se dio enfasis en las caidas
		2.-Se documento a nivel de javadoc
		3.-Se hicieron las pruebas pertinentes, con varios rangos de fechas, incluso para años bisiestos.
		4.-El program esta ordenado y sin comentarios que no aporten.
		5.-Se considero que el orden de salida de los rangos perdidos era importante.
		6.-No se habilitu una librería para los logs.



