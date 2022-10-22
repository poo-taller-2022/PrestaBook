# Prestabook

Proyecto universitario de creación de un gestor de préstamos de libros.

## Database

Se usa una base de datos SQLite con Hibernate como gestor ORM.

## Dependencias

| Artifact                | Description                                           | Version                                            |
| ----------------------- | ----------------------------------------------------- | -------------------------------------------------- |
| Barbecue                | Crea, muestra e imprime códigos de barra              | [1.5-beta1](https://barbecue.sourceforge.net/)     |
| modelmapper             | Librería inteligente de mapeo de objetos              | [3.1.0](http://modelmapper.org/)                   |
| sqlite-jdbc             | Accede y crea archivos de base de datos en Java       | [3.39.2.1])(https://github.com/xerial/sqlite-jdbc) |
| lombok                  | Reduce el código boilerplate y aumenta la legibilidad | [1.18.24](https://projectlombok.org/)              |
| hibernate-entitymanager | Librería de mapeo objeto/relacional escalable         | [5.6.12.Final](https://hibernate.org/)             |
| sqlite-dialect          | Dialecto Hibernate para SQLite                        | [0.1.2](https://github.com/gwenn/sqlite-dialect)   |

## Iniciar Prestabook

El programa puede inciarse con el comando maven:

    $ `mvn clean compile exec:java`

O iniciándolo con el IDE de preferencia.

## Casos de uso

### Registro e inicio de sesión

El sistema permite al usuario registrarse e iniciar sesión como Funcionario o Lector. Adicionalmente, un Lector puede ser Alumno, Docente o de público general.
La contraseña se almacena encriptada en la base de datos.

## Funcionario

### Carga de datos

Un funcionario puede cargar diferentes tipos de datos en el software desde la sección `Administrar`. Allí puede agregarse un nuevo Tipo de Obra, una nueva Area Temática, un nuevo Formato, una Obra o una colección de Obras, una Edición de obras,un ejemplar de determinadas obras.

### Menú lateral

Desde el menú lateral, el funcionario puede acceder a otras funcionalidades

### Gestionar préstamos

Desde aquí el funcionario puede gestionar el préstamo del ejemplar de un libro, ya sea a domicilio o en sala. Puede elegir la obra, el número de ejemplar, el lector, la fecha y hora del préstamo y la fecha límite de devolución.

### Gestionar devolución de préstamo

Aquí el funcionario puede seleccionar un lector, y en base a los préstamos vigentes que tenga, elegir uno y registrar la devolución del libro. Si está fuera de término, puede aplicar una multa.

### Ver ejemplares

En esta ventana, se puede elegir una obra y sus ejemplares, además de poder editar datos relativos al mismo, como por ejemplo la ubicación física. Asimismo, se puede dar de baja a un ejemplar especificando datos pertintentes.

### Ver lectores

Este panel muestra un listado de todos los lectores.

### Ver multas

Este panel muestra un listado de todas las multas, permitiendo filtrar por fechas.

### Ver obras por editorial

Este panel muestra un listado de las obras y permite filtrarlas por la editorial.

### Ver ejemplares disponibles por area

Este panel permite ver los ejemplares disponibles de obras filtradas por una de sus áreas temáticas,

### Ver reservas

Este panel permite ver los ejemplares que estén reservados, y permite filtrar a partir de una fecha en particular.

### Ver obras más solicitadas

Este panel muestra todas las obras, ordenadas por la cantidad de veces que fueron prestadas y las reservas vigentes.

## Lector

El lector puede consultar las obras existentes, filtrarlas por area temática, y hacer una reserva o solicitar un préstamo
