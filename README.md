# Desafio_Uno GDD Nivel 3
Servicio Rest creado con Spring Boot

id: identificador 
fechaCreacion: Fecha de inicio 
fechaFin: Fecha de fin 
fechas: Lista de fechas que están entre la “fechaCreacion” y la fecha “fechaFin”.

Ejemplo Json de ejecución:

{
  "id": 6,
  "fechaCreacion": "1967-03-01",
  "fechaFin": "1970-01-01",
  "fechas": [
    "1969-03-01",
    "1969-05-01",
    "1969-09-01",
    "1970-01-01"
  ]
}

# Limpiar y Compilar el proyecto
mvn clean package

# Tecnologías utilizadas
- Spring Boot 2.5.4
- Java 1.8.0_231

Nombre: Claudio Fabián Flores Allende
Correo: claudio.flores.allende@gmail.com
Me enteré del desafío por una ex colega de Sodexo