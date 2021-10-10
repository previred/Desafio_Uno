
# API Desafio Uno

API Rest desarrollada en GO que obtiene una lista de periodos desde una fecha inicial hasta una fecha final y detecta que fechas han sido omitidas.

## Requerimientos

Para ejecutar esta API debe disponer de los siguiente requerimientos:

- [Go 1.17](https://golang.org/)
- [Servicio API Generator](https://github.com/previred/Generador_Datos_Desafio_Uno) escuchando en http://127.0.0.1:8080/periodos/

## Uso de la API

- Ejecutar API con GO

    En una terminal y ubicado en la raíz de la API debe ejecutar el siguiente comando:

    ```sh
    go run server.go
    ```

- Ejecutar la APi por medio de un ejecutable

    En una terminal y ubicado en la raíz de la API debe ejecutar el siguiente comando:

    Para __Windows__

    ```sh
    go build -o bin/server.exe
    ```

    Para __Linux__

    ```sh
    go build -o bin/server
    ```

    En Windows creará el archivo bin/_server.exe_ y en Linux bin/_server_, puede cambiar el nombre del archivo de salido a su preferencia.

    Luego ejecute el archivo de salida.

- Uso de la API en un Navegador

    Abrir en un navegador la siguiente dirección

    - [http://localhost:8090/api](http://localhost:8090/api)

## Configuración de los endpoint

En tiempo de desarrollo es posible cambiar el endpoint de consulta de datos modificando el archivo _config/config.go_:

```go
func Config() *AppConfig {
	return &AppConfig{
		Protocol: "HTTP",
		Host:     "localhost",
		Port:     8090, Root: "/",
		Backend: Backend{
			Protocol: "HTTP",
			Host:     "localhost",
			Port:     8080, Root: "/periodos",
		},
	}
}
```

## Creación del Código base

Para la creación del código base del servidor se ocupo la librería [github.com/deepmap/oapi-codegen](github.com/deepmap/oapi-codegen).

Para instalar la librería debe ejecutar el siguiente comando:

```sh
go install github.com/deepmap/oapi-codegen/cmd/oapi-codegen
```

Para actualizaciones del código base debe ejecutar los siguientes comandos:

```sh
oapi-codegen -generate types -o types/api_types.gen.go -package types swagger/swagger.yml
oapi-codegen -generate chi-server -o openapi/api_server.gen.go -package openapi swagger/swagger.yml
```

Es importante __no editar los archivos de salida__, de lo contrario esos cambios se perderán en caso de volver a generarlos.

## Como extender o evolucionar la API

Para extender o evolucionar la API se debe tener en consideración lo siguiente:

- Para nuevas fuente de datos se debe extender el paquete datasource.
- En caso de cambios de los modelos o métodos de la API se debe regenerar el código base según explicado más arriba.
- Se debe actualizar el __Router__ routes/router.go para que incluya las modificaciones.
- La lógica de negocio se realiza en los servicios dentro del paquete __services__


## Mejoras a implementar

A continuación se listan alguna mejoras posibles de realizar:

- Devolver errores controlados
- Uso de un logger
- Agregar pruebas unitarias
- Permitir la configuración de los endpoint por medio de variables de entorno
- Crear un imagen docker de la API
- Incluir mecanismo de seguridad como por ejemplo JWT
- 