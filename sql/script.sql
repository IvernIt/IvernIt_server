  DROP DATABASE IF EXISTS IvernIt;

  CREATE DATABASE IvernIt;

  USE IvernIt;

  CREATE TABLE usuarios (
    idUsuario  INTEGER      AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(255) UNIQUE,
    contrasena VARCHAR(64)  NOT NULL,
    premium    TINYINT(1)   NOT NULL
  );

  INSERT INTO usuarios(nombre, contrasena, premium) VALUES('pablo', '26079e41910bcde04be636fbeecc9045379882b5ad3fe7f70b762436c6d98055', 1);
  INSERT INTO usuarios(nombre, contrasena, premium) VALUES('menendez', '2cae32224c8f2416475351adc9923d77ff307c82d899557e2d30c6d71dd63c00', 1);
  INSERT INTO usuarios(nombre, contrasena, premium) VALUES('sampru', 'e7fa886ec38e36629a43367fdd0a42badab224c9047ba5aaba2d4dfb4fcd0623', 0);

  CREATE TABLE invernaderos (
    idInvernadero INTEGER AUTO_INCREMENT PRIMARY KEY,
    idUsuario     INTEGER NOT NULL,
    CONSTRAINT FK_usuInv FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
  );

  INSERT INTO invernaderos(idUsuario) VALUES(1);
  INSERT INTO invernaderos(idUsuario) VALUES(1);
  INSERT INTO invernaderos(idUsuario) VALUES(2);
  INSERT INTO invernaderos(idUsuario) VALUES(3);
  INSERT INTO invernaderos(idUsuario) VALUES(3);

  CREATE TABLE estados (
    idEstado  INTEGER      AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(255) NOT NULL
  );

  INSERT INTO estados(nombre) VALUES('Germinación');
  INSERT INTO estados(nombre) VALUES('Ahijamiento');
  INSERT INTO estados(nombre) VALUES('Gran crecimiento');
  INSERT INTO estados(nombre) VALUES('Maduracion');

  CREATE TABLE vegetales (
    idVegetal INTEGER      AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(255) NOT NULL,
    idEstado  INTEGER      NOT NULL,
    CONSTRAINT FK_vegIdEst FOREIGN KEY (idEstado)   REFERENCES estados(idEstado)    
  );

  INSERT INTO vegetales(nombre, idEstado) VALUES('Tomate',1);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Tomate',2);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Tomate',3);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Tomate',4);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Patata',1);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Patata',2);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Patata',3);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Patata',4);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Cebolla',1);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Cebolla',2);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Cebolla',3);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Cebolla',4);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Pimiento',1);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Pimiento',2);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Pimiento',3);
  INSERT INTO vegetales(nombre, idEstado) VALUES('Pimiento',4);

  CREATE TABLE parametros (
    idParametro INTEGER      AUTO_INCREMENT PRIMARY KEY,
    horasRiego  DOUBLE       NOT NULL,
    horasLuz    DOUBLE       NOT NULL,
    temperatura DOUBLE       NOT NULL,
    tipoTierra  VARCHAR(255) NOT NULL
  );

  INSERT INTO parametros(horasRiego, horasLuz, temperatura, tipoTierra) VALUES(1, 2,20,'rocosa');
  INSERT INTO parametros(horasRiego, horasLuz, temperatura, tipoTierra) VALUES(2, 3,18,'fertil');
  INSERT INTO parametros(horasRiego, horasLuz, temperatura, tipoTierra) VALUES(3, 5,21,'acida');

  CREATE TABLE cultivoIdeal (
    idCultivoIdeal INTEGER AUTO_INCREMENT PRIMARY KEY,
    idVegetal      INTEGER UNIQUE NOT NULL,
    idParametro    INTEGER NOT NULL,
    CONSTRAINT FK_culIdeVeg FOREIGN KEY (idVegetal)   REFERENCES vegetales(idVegetal),
    CONSTRAINT FK_culIdePar FOREIGN KEY (idParametro) REFERENCES parametros(idParametro)
  );
  
  INSERT INTO cultivoIdeal(idVegetal, idParametro) VALUES(1, 1);
  INSERT INTO cultivoIdeal(idVegetal, idParametro) VALUES(2, 1);

  CREATE TABLE cultivoInvernadero (
    idCultivoInve INTEGER,
    idVegetal     INTEGER,
    idParametro   INTEGER,
    idInvernadero INTEGER,
    fechaInicio   DATE NOT NULL,
    CONSTRAINT PK_culInv    PRIMARY KEY (idCultivoInve,idVegetal,idParametro,idInvernadero),
    CONSTRAINT FK_culInvVeg FOREIGN KEY (idVegetal)     REFERENCES vegetales(idVegetal),
    CONSTRAINT FK_culInvPar FOREIGN KEY (idParametro)   REFERENCES parametros(idParametro),
    CONSTRAINT FK_culInvInv FOREIGN KEY (idInvernadero) REFERENCES invernaderos(idInvernadero)
  );

  INSERT INTO cultivoInvernadero(idCultivoInve, idVegetal, idParametro, idInvernadero, fechaInicio) VALUES(1, 1, 1, 1,'01-02-2017');
  INSERT INTO cultivoInvernadero(idCultivoInve, idVegetal, idParametro, idInvernadero, fechaInicio) VALUES(1, 2, 2, 1,'01-02-2017');
  INSERT INTO cultivoInvernadero(idCultivoInve, idVegetal, idParametro, idInvernadero, fechaInicio) VALUES(1, 3, 3, 1,'01-02-2017');
  INSERT INTO cultivoInvernadero(idCultivoInve, idVegetal, idParametro, idInvernadero, fechaInicio) VALUES(1, 4, 3, 1,'01-02-2017');
  INSERT INTO cultivoInvernadero(idCultivoInve, idVegetal, idParametro, idInvernadero, fechaInicio) VALUES(2, 1, 3, 2,'01-02-2017');
  INSERT INTO cultivoInvernadero(idCultivoInve, idVegetal, idParametro, idInvernadero, fechaInicio) VALUES(2, 2, 1, 2,'01-02-2017');
  INSERT INTO cultivoInvernadero(idCultivoInve, idVegetal, idParametro, idInvernadero, fechaInicio) VALUES(2, 3, 2, 2,'01-02-2017'); 
  INSERT INTO cultivoInvernadero(idCultivoInve, idVegetal, idParametro, idInvernadero, fechaInicio) VALUES(2, 4, 2, 2,'01-02-2017'); 

  CREATE TABLE datosCosecha (
    idDatoCosecha INTEGER AUTO_INCREMENT PRIMARY KEY,
    idCultivoInve  INTEGER NOT NULL,
    resultado     CHAR NOT NULL,
    CONSTRAINT FK_datCosVeg FOREIGN KEY (idCultivoInve)   REFERENCES cultivoInvernadero(idCultivoInve)
  );

  INSERT INTO datosCosecha(idCultivoInve, resultado) VALUES(1, 'A');
  INSERT INTO datosCosecha(idCultivoInve, resultado) VALUES(2, 'B');

  CREATE USER 'ivernit'@'localhost' IDENTIFIED BY '1vern1t';
  GRANT ALL PRIVILEGES ON IvernIt.* TO 'ivernit'@'localhost';
  FLUSH PRIVILEGES;