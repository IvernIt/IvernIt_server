  DROP DATABASE IF EXISTS IvernIt;

  CREATE DATABASE IvernIt;

  USE IvernIt;

  CREATE TABLE usuario (
    uId         INTEGER      AUTO_INCREMENT PRIMARY KEY,
    uNombre     VARCHAR(255) UNIQUE,
    uContrasena VARCHAR(64)  NOT NULL,
    uPremium    TINYINT(1)   NOT NULL
  );

  INSERT INTO usuario(uNombre, uContrasena, uPremium) VALUES('pablo', '26079e41910bcde04be636fbeecc9045379882b5ad3fe7f70b762436c6d98055', 1);
  INSERT INTO usuario(uNombre, uContrasena, uPremium) VALUES('menendez', '2cae32224c8f2416475351adc9923d77ff307c82d899557e2d30c6d71dd63c00', 1);
  INSERT INTO usuario(uNombre, uContrasena, uPremium) VALUES('sampru', 'e7fa886ec38e36629a43367fdd0a42badab224c9047ba5aaba2d4dfb4fcd0623', 0);

  CREATE TABLE invernadero (
    iId     INTEGER AUTO_INCREMENT PRIMARY KEY,
    uId     INTEGER NOT NULL,
    iNombre VARCHAR(255),
    CONSTRAINT FK_usuInv FOREIGN KEY (uId) REFERENCES usuario(uId)
  );

  INSERT INTO invernadero(uId,iNombre) VALUES(1,'testing greenhouse');
  INSERT INTO invernadero(uId,iNombre) VALUES(1,'testing greenhouse');
  INSERT INTO invernadero(uId,iNombre) VALUES(2,'testing greenhouse');
  INSERT INTO invernadero(uId,iNombre) VALUES(3,'testing greenhouse');
  INSERT INTO invernadero(uId,iNombre) VALUES(3,'testing greenhouse');

  CREATE TABLE estadoCrecimiento (
    ecId     INTEGER      AUTO_INCREMENT PRIMARY KEY,
    ecNombre VARCHAR(255) NOT NULL
  );

  INSERT INTO estadoCrecimiento(ecNombre) VALUES('Germinación');
  INSERT INTO estadoCrecimiento(ecNombre) VALUES('Ahijamiento');
  INSERT INTO estadoCrecimiento(ecNombre) VALUES('Gran crecimiento');
  INSERT INTO estadoCrecimiento(ecNombre) VALUES('Maduracion');

  CREATE TABLE vegetal (
    vId     INTEGER      AUTO_INCREMENT PRIMARY KEY,
    vNombre VARCHAR(255) NOT NULL,
    ecId    INTEGER      NOT NULL,
    CONSTRAINT FK_vegIdEst FOREIGN KEY (ecId) REFERENCES estadoCrecimiento(ecId)    
  );

  INSERT INTO vegetal(vNombre, ecId) VALUES('Tomate',1);
  INSERT INTO vegetal(vNombre, ecId) VALUES('Tomate',2);
  INSERT INTO vegetal(vNombre, ecId) VALUES('Tomate',3);
  INSERT INTO vegetal(vNombre, ecId) VALUES('Tomate',4);
  INSERT INTO vegetal(vNombre, ecId) VALUES('Patata',1);
  INSERT INTO vegetal(vNombre, ecId) VALUES('Patata',2);
  INSERT INTO vegetal(vNombre, ecId) VALUES('Patata',3);
  INSERT INTO vegetal(vNombre, ecId) VALUES('Patata',4);
  INSERT INTO vegetal(vNombre, ecId) VALUES('Cebolla',1);
  INSERT INTO vegetal(vNombre, ecId) VALUES('Cebolla',2);
  INSERT INTO vegetal(vNombre, ecId) VALUES('Cebolla',3);
  INSERT INTO vegetal(vNombre, ecId) VALUES('Cebolla',4);
  INSERT INTO vegetal(vNombre, ecId) VALUES('Pimiento',1);
  INSERT INTO vegetal(vNombre, ecId) VALUES('Pimiento',4);

  CREATE TABLE parametro (
    pId          INTEGER      AUTO_INCREMENT PRIMARY KEY,
    pAgua        DOUBLE       NOT NULL,
    pHorasLuz    DOUBLE       NOT NULL,
    pTemperatura DOUBLE       NOT NULL,
    pTipoTierra  VARCHAR(255) NOT NULL
  );

  INSERT INTO parametro(pAgua, pHorasLuz, pTemperatura, pTipoTierra) VALUES(1, 2,20,'rocosa');
  INSERT INTO parametro(pAgua, pHorasLuz, pTemperatura, pTipoTierra) VALUES(2, 3,18,'fertil');
  INSERT INTO parametro(pAgua, pHorasLuz, pTemperatura, pTipoTierra) VALUES(3, 5,21,'acida');

  CREATE TABLE cultivo (
    cId          INTEGER,
    vId          INTEGER,
    pId          INTEGER,
    iId          INTEGER,
    cFechaInicio DATE NOT NULL,
    cResultado   CHAR(1),
    CONSTRAINT PK_culInv    PRIMARY KEY (cId,vId,pId,iId),
    CONSTRAINT FK_culInvVeg FOREIGN KEY (vId) REFERENCES vegetal(vId),
    CONSTRAINT FK_culInvPar FOREIGN KEY (pId) REFERENCES parametro(pId),
    CONSTRAINT FK_culInvInv FOREIGN KEY (iId) REFERENCES invernadero(iId)
  );

  INSERT INTO cultivo(cId, vId, pId, iId, cFechaInicio, cResultado) VALUES(1, 1, 1, 1,'2017-01-02', 'A');
  INSERT INTO cultivo(cId, vId, pId, iId, cFechaInicio, cResultado) VALUES(1, 2, 2, 1,'2017-01-02', 'A');
  INSERT INTO cultivo(cId, vId, pId, iId, cFechaInicio, cResultado) VALUES(1, 3, 3, 1,'2017-01-02', 'A');
  INSERT INTO cultivo(cId, vId, pId, iId, cFechaInicio, cResultado) VALUES(1, 4, 3, 1,'2017-01-02', 'A');
  INSERT INTO cultivo(cId, vId, pId, iId, cFechaInicio, cResultado) VALUES(2, 1, 3, 2,'2017-01-02', 'B');
  INSERT INTO cultivo(cId, vId, pId, iId, cFechaInicio, cResultado) VALUES(2, 2, 1, 2,'2017-01-02', 'B');
  INSERT INTO cultivo(cId, vId, pId, iId, cFechaInicio, cResultado) VALUES(2, 3, 2, 2,'2017-01-02', 'B');
  INSERT INTO cultivo(cId, vId, pId, iId, cFechaInicio, cResultado) VALUES(2, 4, 2, 2,'2017-01-02', 'B');

  CREATE USER 'ivernit'@'*' IDENTIFIED BY '1vern1t';
  GRANT SELECT, INSERT,  ON IvernIt.* TO 'ivernit'@'*';
  FLUSH PRIVILEGES;
