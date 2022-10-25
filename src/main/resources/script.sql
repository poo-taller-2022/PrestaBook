--BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "alumnos" (
	"documento"	bigint NOT NULL,
	"apellido"	varchar(255),
	"celular"	varchar(255),
	"codigo_postal"	varchar(255),
	"contrasenia"	varchar(255),
	"departamento"	varchar(255),
	"domicilio"	varchar(255),
	"email"	varchar(255) NOT NULL,
	"fecha_nacimiento"	varchar(255),
	"localidad"	varchar(255),
	"nacionalidad"	varchar(255),
	"nombre"	varchar(255),
	"sexo"	varchar(255),
	"tipo_documento"	varchar(255),
	"carreras"	varchar(255),
	PRIMARY KEY("documento")
);
CREATE TABLE IF NOT EXISTS "areas_tematicas" (
	"id"	integer,
	"nombre"	varchar(255),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "codigos_identificatorios" (
	"codigo"	integer,
	"estante"	integer,
	"estanteria"	integer,
	"pasillo"	integer,
	PRIMARY KEY("codigo")
);
CREATE TABLE IF NOT EXISTS "colecciones" (
	"isbn"	varchar(255) NOT NULL,
	"genero"	varchar(255),
	"primer_autor"	varchar(255),
	"segundo_autor"	varchar(255),
	"subtitulo"	varchar(255),
	"tercer_autor"	varchar(255),
	"titulo"	varchar(255),
	"tipo_id"	integer,
	PRIMARY KEY("isbn")
);
CREATE TABLE IF NOT EXISTS "colecciones_areas_tematicas" (
	"Coleccion_isbn"	varchar(255) NOT NULL,
	"area_id"	integer NOT NULL,
	PRIMARY KEY("Coleccion_isbn","area_id")
);
CREATE TABLE IF NOT EXISTS "configs" (
	"key_col"	varchar(255) NOT NULL,
	"value_col"	varchar(255),
	PRIMARY KEY("key_col")
);
CREATE TABLE IF NOT EXISTS "docentes" (
	"documento"	bigint NOT NULL,
	"apellido"	varchar(255),
	"celular"	varchar(255),
	"codigo_postal"	varchar(255),
	"contrasenia"	varchar(255),
	"departamento"	varchar(255),
	"domicilio"	varchar(255),
	"email"	varchar(255) NOT NULL,
	"fecha_nacimiento"	varchar(255),
	"localidad"	varchar(255),
	"nacionalidad"	varchar(255),
	"nombre"	varchar(255),
	"sexo"	varchar(255),
	"tipo_documento"	varchar(255),
	"carreras"	varchar(255),
	PRIMARY KEY("documento")
);
CREATE TABLE IF NOT EXISTS "ediciones" (
	"id"	integer,
	"anio"	integer,
	"editorial"	varchar(255),
	"idioma"	varchar(255),
	"isbn_obra"	varchar(255),
	"numero"	integer,
	"paginas"	integer,
	"pais"	varchar(255),
	"volumenes"	integer,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "ediciones_formato" (
	"edicion_id"	bigint NOT NULL,
	"formato_id"	integer NOT NULL,
	PRIMARY KEY("edicion_id","formato_id")
);
CREATE TABLE IF NOT EXISTS "ejemplares" (
	"id"	integer,
	"genero"	varchar(255),
	"primer_autor"	varchar(255),
	"segundo_autor"	varchar(255),
	"subtitulo"	varchar(255),
	"tercer_autor"	varchar(255),
	"titulo"	varchar(255),
	"fecha_adquisicion"	varchar(255),
	"fecha_baja"	varchar(255),
	"forma_adquisicion"	varchar(255),
	"id_Edicion"	bigint,
	"isbn_obra"	varchar(255),
	"motivo_baja"	varchar(255),
	"observaciones"	varchar(255),
	"tipo_id"	integer,
	"codigo_identificatorio"	bigint,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "formato" (
	"id"	integer,
	"nombre"	varchar(255),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "funcionarios" (
	"documento"	bigint NOT NULL,
	"apellido"	varchar(255),
	"celular"	varchar(255),
	"codigo_postal"	varchar(255),
	"contrasenia"	varchar(255),
	"departamento"	varchar(255),
	"domicilio"	varchar(255),
	"email"	varchar(255) NOT NULL,
	"fecha_nacimiento"	varchar(255),
	"localidad"	varchar(255),
	"nacionalidad"	varchar(255),
	"nombre"	varchar(255),
	"sexo"	varchar(255),
	"tipo_documento"	varchar(255),
	PRIMARY KEY("documento")
);
CREATE TABLE IF NOT EXISTS "lectores" (
	"documento"	bigint NOT NULL,
	"apellido"	varchar(255),
	"celular"	varchar(255),
	"codigo_postal"	varchar(255),
	"contrasenia"	varchar(255),
	"departamento"	varchar(255),
	"domicilio"	varchar(255),
	"email"	varchar(255) NOT NULL,
	"fecha_nacimiento"	varchar(255),
	"localidad"	varchar(255),
	"nacionalidad"	varchar(255),
	"nombre"	varchar(255),
	"sexo"	varchar(255),
	"tipo_documento"	varchar(255),
	PRIMARY KEY("documento")
);
CREATE TABLE IF NOT EXISTS "multas" (
	"id"	integer,
	"fecha"	varchar(255),
	"plazo"	integer,
	"lector_documento"	bigint,
	"prestamo_id"	bigint,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "obras" (
	"isbn"	varchar(255) NOT NULL,
	"genero"	varchar(255),
	"primer_autor"	varchar(255),
	"segundo_autor"	varchar(255),
	"subtitulo"	varchar(255),
	"tercer_autor"	varchar(255),
	"titulo"	varchar(255),
	"isbn_coleccion"	varchar(255),
	"tipo_id"	integer,
	PRIMARY KEY("isbn")
);
CREATE TABLE IF NOT EXISTS "obras_areas_tematicas" (
	"Obra_isbn"	varchar(255) NOT NULL,
	"area_id"	integer NOT NULL,
	PRIMARY KEY("Obra_isbn","area_id")
);
CREATE TABLE IF NOT EXISTS "prestamos" (
	"id"	integer,
	"fecha_pactada_devolucion"	varchar(255),
	"fecha_real_devolucion"	varchar(255),
	"fecha_y_hora_prestamo"	varchar(255),
	"plazo_prestamo"	integer,
	"ejemplar_id"	bigint,
	"funcionario_documento"	bigint,
	"lector_documento"	bigint,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "reserva" (
	"id"	integer,
	"fecha_reserva"	varchar(255),
	"isActive"	boolean,
	"ejemplar_id"	bigint,
	"lector_documento"	bigint,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "tipos_obra" (
	"id"	integer,
	"nombre"	varchar(255),
	PRIMARY KEY("id")
);
INSERT OR IGNORE INTO "areas_tematicas" ("id","nombre") VALUES (1,'Matemática'),
 (2,'Programación'),
 (3,'Contabilidad'),
 (4,'Administración de Empresas'),
 (5,'Sistemas Operativos'),
 (6,'Programación Orientada a Objetos'),
 (7,'Java'),
 (8,'Python'),
 (9,'Ingeniería de Software');
INSERT OR IGNORE INTO "configs" ("key_col","value_col") VALUES ('default_loan_time','4');
INSERT OR IGNORE INTO "formato" ("id","nombre") VALUES (1,'Electrónico'),
 (2,'CD'),
 (3,'DVD'),
 (4,'Papel'),
 (5,'Video');
INSERT OR IGNORE INTO "funcionarios" ("documento","apellido","celular","codigo_postal","contrasenia","departamento","domicilio","email","fecha_nacimiento","localidad","nacionalidad","nombre","sexo","tipo_documento") VALUES (123,'a','21312','a','$2a$12$LzjJxP88XL0n8geagQRf7OXRLujj4OpVZmurQ58GQ5AmuhSJeJGqW','a','a','a','1997-04-10','a','a','a','Hombre','a');
INSERT OR IGNORE INTO "tipos_obra" ("id","nombre") VALUES (1,'Libro'),
 (2,'Revista'),
 (3,'Tesis'),
 (4,'Ensayo'),
 (5,'Manual'),
 (6,'Boletín'),
 (7,'Compendio'),
 (8,'Diario'),
 (9,'Diccionario'),
 (10,'Documental'),
 (11,'Enciclopedia'),
 (12,'Investigación'),
 (13,'Monografía');
--COMMIT;
