DROP TABLE ADDRESS IF EXISTS;
DROP TABLE CLIENTE IF EXISTS;

CREATE TABLE CLIENTE (
    ID integer identity primary key,  
    NOMBRE varchar(50) not null,
    APELLIDO varchar(50) not null,
	CREADO timestamp,
	CONSTRAINT IDX_USUARIO_ID PRIMARY KEY (ID)
);



INSERT INTO CLIENTE(ID, NOMBRE, APELLIDO, CREADO) 
   VALUES(1, 'Joe', 'Smith', NOW());

INSERT INTO CLIENTE(ID, NOMBRE, APELLIDO, CREADO) 
   VALUES(2, 'John', 'Wilson', NOW());

