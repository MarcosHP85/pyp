DROP TABLE programacion_ot;
DROP TABLE paquete_ot;
DROP TABLE ot;
DROP TABLE programacion;
DROP TABLE paquete;

CREATE TABLE programacion (
	id INT(11) NOT NULL AUTO_INCREMENT,
	titulo VARCHAR(16) NOT NULL,
	planta VARCHAR(4) NOT NULL,
	f_inicio DATE,
	f_fin DATE,
	enabled TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id),
	UNIQUE KEY (titulo)
);

CREATE TABLE ot (
	id INT(11) NOT NULL,
	componente VARCHAR(12) NOT NULL,
	prioridad VARCHAR(1) NOT NULL,
	org_mant VARCHAR(8) NOT NULL,
	tipo_trabajo VARCHAR(3) NOT NULL,
	tarea VARCHAR(1024) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE programacion_ot (
	programacion_id INT(11) NOT NULL,
	ot_id INT(11) NOT NULL,
	PRIMARY KEY (programacion_id,ot_id),
	UNIQUE KEY uni_ot_programacion (ot_id),
	KEY fk_programacion_ot (programacion_id),
	KEY fk_ot_programacion (ot_id),
	CONSTRAINT fk_programacion_ot FOREIGN KEY (programacion_id) REFERENCES programacion(id),
	CONSTRAINT fk_ot_programacion FOREIGN KEY (ot_id) REFERENCES ot(id)
);

CREATE TABLE paquete (
	id INT(11) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id)
);

CREATE TABLE paquete_ot (
	paquete_id INT(11) NOT NULL,
	ot_id INT(11) NOT NULL,
	PRIMARY KEY (paquete_id,ot_id),
	UNIQUE KEY uni_ot_paquete (ot_id),
	KEY fk_paquete_ot (paquete_id),
	KEY fk_ot_paquete (ot_id),
	CONSTRAINT fk_paquete_ot FOREIGN KEY (paquete_id) REFERENCES paquete(id),
	CONSTRAINT fk_ot_paquete FOREIGN KEY (ot_id) REFERENCES ot(id)
);

SELECT * FROM programacion;
SELECT * FROM programacion_ot;
SELECT * FROM ot;
SELECT * FROM paquete;
SELECT * FROM paquete_ot;

delete from programacion_ot where ot_id = 36201;
delete from ot where id = 36201;