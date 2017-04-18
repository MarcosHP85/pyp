CREATE TABLE user (
	id_user INT(11) NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL,
	password VARCHAR(60) NOT NULL,
	enabled TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_user),
	UNIQUE KEY (username)
);

CREATE TABLE role (
	id_role INT(11) NOT NULL AUTO_INCREMENT,
	name VARCHAR(45) NOT NULL,
	PRIMARY KEY (id_role),
	UNIQUE KEY (name)
);

CREATE TABLE permission (
	id_permission INT(11) NOT NULL AUTO_INCREMENT,
	name VARCHAR(45) NOT NULL,
	PRIMARY KEY (id_permission),
	UNIQUE KEY (name)
);

CREATE TABLE user_role (
	id_user_role INT(11) NOT NULL AUTO_INCREMENT,
	id_user INT(11) NOT NULL,
	id_role INT(11) NOT NULL,
	PRIMARY KEY (id_user_role),
	UNIQUE KEY uni_user_role (id_user,id_role),
	CONSTRAINT fk_user_role FOREIGN KEY (id_user) REFERENCES user(id_user),
	CONSTRAINT fk_role_user FOREIGN KEY (id_role) REFERENCES role(id_role)
);

CREATE TABLE role_permission (
	id_role_permission INT(11) NOT NULL AUTO_INCREMENT,
	id_role INT(11) NOT NULL,
	id_permission INT(11) NOT NULL,
	PRIMARY KEY (id_role_permission),
	UNIQUE KEY uni_role_permission (id_role,id_permission),
	CONSTRAINT fk_role_permission FOREIGN KEY (id_role) REFERENCES role(id_role),
	CONSTRAINT fk_permission_role FOREIGN KEY (id_permission) REFERENCES permission(id_permission)
);

-- Roles
INSERT INTO role(name) VALUES ('ROLE_ADMIN');
INSERT INTO role(name) VALUES ('ROLE_SUP_PLAN');
INSERT INTO role(name) VALUES ('ROLE_PLAN');

-- Usuarios
INSERT INTO user(username,password,enabled) VALUES ('MAPARODI','$2a$10$6TajU85/gVrGUm5fv5Z8beVF37rlENohyLk3BEpZJFi6Av9JNkw9O',1);
INSERT INTO user(username,password,enabled) VALUES ('MSOTTILE','$2a$10$6TajU85/gVrGUm5fv5Z8beVF37rlENohyLk3BEpZJFi6Av9JNkw9O',1);
INSERT INTO user(username,password,enabled) VALUES ('HBRAVO','$2a$10$6TajU85/gVrGUm5fv5Z8beVF37rlENohyLk3BEpZJFi6Av9JNkw9O',1);

-- Asignar roles
INSERT INTO user_role(id_user,id_role) VALUES (
	(SELECT id_user FROM user WHERE username = 'MPARODI'),
	(SELECT id_role FROM role WHERE name = 'ROLE_ADMIN')
);
INSERT INTO user_role(id_user,id_role) VALUES (
	(SELECT id_user FROM user WHERE username = 'HBRAVO'),
	(SELECT id_role FROM role WHERE name = 'ROLE_SUP_PLAN')
);
INSERT INTO user_role(id_user,id_role) VALUES (
	(SELECT id_user FROM user WHERE username = 'MSOTTILE'),
	(SELECT id_role FROM role WHERE name = 'ROLE_PLAN')
);

INSERT INTO permission(name) VALUES ('ADD_USER');
INSERT INTO role_permission(id_role,id_permission) VALUES (1,1);
INSERT INTO permission(name) VALUES ('ADD_ROLE');
INSERT INTO role_permission(id_role,id_permission) VALUES (1,2);