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

  CREATE TABLE vegetales (
    idVegetal INTEGER      AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(255) NOT NULL
  );

  INSERT INTO vegetales(nombre) VALUES('Tomate');
  INSERT INTO vegetales(nombre) VALUES('Patata');
  INSERT INTO vegetales(nombre) VALUES('Cebolla');
  INSERT INTO vegetales(nombre) VALUES('Pimiento');

  CREATE TABLE parametros (
    idParametro INTEGER      AUTO_INCREMENT PRIMARY KEY,
    dimension   VARCHAR(255) NOT NULL,
    unidad      VARCHAR(255) NOT NULL
  );

  INSERT INTO parametros(dimension, unidad) VALUES('riego', 'litros');
  INSERT INTO parametros(dimension, unidad) VALUES('luz' ,'lumenes');
  INSERT INTO parametros(dimension, unidad) VALUES('temperatura' ,'grados');

  CREATE TABLE cultivoIdeal (
    idCultivoIdeal INTEGER AUTO_INCREMENT PRIMARY KEY,
    idVegetal      INTEGER NOT NULL,
    idParametro    INTEGER NOT NULL,
    valor          INTEGER,
    CONSTRAINT FK_culIdeVeg FOREIGN KEY (idVegetal)   REFERENCES vegetales(idVegetal),
    CONSTRAINT FK_culIdePar FOREIGN KEY (idParametro) REFERENCES parametros(idParametro)
  );
  
  INSERT INTO cultivoIdeal(idVegetal, idParametro, valor) VALUES(1, 1, 5);
  INSERT INTO cultivoIdeal(idVegetal, idParametro, valor) VALUES(1, 2, 40);
  INSERT INTO cultivoIdeal(idVegetal, idParametro, valor) VALUES(1, 3, 24);
  INSERT INTO cultivoIdeal(idVegetal, idParametro, valor) VALUES(2, 1, 5);
  INSERT INTO cultivoIdeal(idVegetal, idParametro, valor) VALUES(2, 2, 40);
  INSERT INTO cultivoIdeal(idVegetal, idParametro, valor) VALUES(2, 3, 24);

  CREATE TABLE cultivoInvernadero (
    idCultivoInve INTEGER AUTO_INCREMENT PRIMARY KEY,
    idVegetal     INTEGER NOT NULL,
    idParametro   INTEGER NOT NULL,
    idInvernadero INTEGER NOT NULL,
    valor         INTEGER NOT NULL,
    CONSTRAINT FK_culInvVeg FOREIGN KEY (idVegetal)     REFERENCES vegetales(idVegetal),
    CONSTRAINT FK_culInvPar FOREIGN KEY (idParametro)   REFERENCES parametros(idParametro),
    CONSTRAINT FK_culInvInv FOREIGN KEY (idInvernadero) REFERENCES invernaderos(idInvernadero)
  );

  INSERT INTO cultivoInvernadero(idVegetal, idParametro, idInvernadero, valor) VALUES(1, 1, 1, 4);
  INSERT INTO cultivoInvernadero(idVegetal, idParametro, idInvernadero, valor) VALUES(1, 2, 1, 39);
  INSERT INTO cultivoInvernadero(idVegetal, idParametro, idInvernadero, valor) VALUES(1, 3, 1, 25);
  INSERT INTO cultivoInvernadero(idVegetal, idParametro, idInvernadero, valor) VALUES(2, 1, 2, 4);
  INSERT INTO cultivoInvernadero(idVegetal, idParametro, idInvernadero, valor) VALUES(2, 2, 2, 39);
  INSERT INTO cultivoInvernadero(idVegetal, idParametro, idInvernadero, valor) VALUES(2, 3, 2, 25); 

  CREATE TABLE datosCosecha (
    idDatoCosecha INTEGER AUTO_INCREMENT PRIMARY KEY,
    idVegetal     INTEGER NOT NULL,
    idParametro   INTEGER NOT NULL,
    valor         INTEGER,
    fecha         VARCHAR(10),
    resultado     CHAR,
    CONSTRAINT FK_datCosVeg FOREIGN KEY (idVegetal)   REFERENCES vegetales(idVegetal),
    CONSTRAINT FK_datCosPar FOREIGN KEY (idParametro) REFERENCES parametros(idParametro)
  );

  INSERT INTO datosCosecha(idVegetal, idParametro, valor, fecha, resultado) VALUES(1, 1, 5,'01-01-2017', 'A');
  INSERT INTO datosCosecha(idVegetal, idParametro, valor, fecha, resultado) VALUES(1, 2, 40,'01-01-2017', 'A');
  INSERT INTO datosCosecha(idVegetal, idParametro, valor, fecha, resultado) VALUES(1, 3, 24,'01-01-2017', 'A');