SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `biblioteca` DEFAULT CHARACTER SET utf8 ;
USE `biblioteca` ;

-- -----------------------------------------------------
-- Table `biblioteca`.`libri`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `biblioteca`.`libri` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `Titolo` VARCHAR(255) NULL DEFAULT NULL ,
  `Autore` VARCHAR(255) NULL DEFAULT NULL ,
  `Pagine` VARCHAR(255) NULL DEFAULT NULL ,
  `ISBN` VARCHAR(255) NULL DEFAULT NULL ,
  `Scaffale` VARCHAR(255) NULL DEFAULT NULL ,
  `Disponibile` TINYINT(1) NULL DEFAULT '0' ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `biblioteca`.`prestiti`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `biblioteca`.`prestiti` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `Utente` VARCHAR(255) NULL DEFAULT NULL ,
  `Libro` INT(11) NULL DEFAULT NULL ,
  `Data` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `consegnato` TINYINT(1) NULL DEFAULT '0' ,
  `sollecito` TINYINT(1) NULL DEFAULT '0' ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `biblioteca`.`utenti`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `biblioteca`.`utenti` (
  `ID` VARCHAR(255) NOT NULL ,
  `Nome` VARCHAR(255) NULL DEFAULT NULL ,
  `Cognome` VARCHAR(255) NULL DEFAULT NULL ,
  `Telefono` VARCHAR(255) NULL DEFAULT NULL ,
  `Email` VARCHAR(255) NULL DEFAULT NULL ,
  `Citta` VARCHAR(255) NULL DEFAULT NULL ,
  `Indirizzo` VARCHAR(255) NULL DEFAULT NULL ,
  `Password` VARCHAR(255) NULL DEFAULT NULL ,
  `Tipo` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `Cognome` (`Cognome` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `utenti` (`ID`,`Nome`,`Cognome`,`Telefono`,`Email`,`Citta`,`Indirizzo`,`Password`,`Tipo`) VALUES ('1','',NULL,NULL,'',NULL,NULL,'1','utente');
INSERT INTO `utenti` (`ID`,`Nome`,`Cognome`,`Telefono`,`Email`,`Citta`,`Indirizzo`,`Password`,`Tipo`) VALUES ('2',NULL,NULL,NULL,NULL,NULL,NULL,'2','bibliotecario');
INSERT INTO `utenti` (`ID`,`Nome`,`Cognome`,`Telefono`,`Email`,`Citta`,`Indirizzo`,`Password`,`Tipo`) VALUES ('3',NULL,NULL,NULL,NULL,NULL,NULL,'3','admin');
INSERT INTO `utenti` (`ID`,`Nome`,`Cognome`,`Telefono`,`Email`,`Citta`,`Indirizzo`,`Password`,`Tipo`) VALUES ('admin','Rosario','Venturella','328-11111','rosarioventurella@gmail.com','Cefalù','P.za Verdi','admin','admin');
INSERT INTO `utenti` (`ID`,`Nome`,`Cognome`,`Telefono`,`Email`,`Citta`,`Indirizzo`,`Password`,`Tipo`) VALUES ('biblio','Luca','Aiello','340-11111','luca.gmail.it','Bagheria','C.da Marina','avc','bibliotecario');
INSERT INTO `utenti` (`ID`,`Nome`,`Cognome`,`Telefono`,`Email`,`Citta`,`Indirizzo`,`Password`,`Tipo`) VALUES ('crlblf','Basilio','Ceraolo','380-11111','ing.cbf@gmail.com','Brolo','V. Emanuele','avc','utente');




INSERT INTO `libri` (`ID`,`Titolo`,`Autore`,`Pagine`,`ISBN`,`Scaffale`,`Disponibile`) VALUES (1,'Java','rossi','500','123','A2',1);
INSERT INTO `libri` (`ID`,`Titolo`,`Autore`,`Pagine`,`ISBN`,`Scaffale`,`Disponibile`) VALUES (2,'Robotica','bianchi','500','234','C2',1);
INSERT INTO `libri` (`ID`,`Titolo`,`Autore`,`Pagine`,`ISBN`,`Scaffale`,`Disponibile`) VALUES (3,'Programmare Robot','neri','700','1234','B1',1);
INSERT INTO `libri` (`ID`,`Titolo`,`Autore`,`Pagine`,`ISBN`,`Scaffale`,`Disponibile`) VALUES (4,'umldistilled','rossi','190','1946','B3',1);
INSERT INTO `libri` (`ID`,`Titolo`,`Autore`,`Pagine`,`ISBN`,`Scaffale`,`Disponibile`) VALUES (5,'Teoria dei segnali','rossi','200','154','B6',1);
INSERT INTO `libri` (`ID`,`Titolo`,`Autore`,`Pagine`,`ISBN`,`Scaffale`,`Disponibile`) VALUES (6,'Analisi 1','bianchi','800','165','C3',1);
INSERT INTO `libri` (`ID`,`Titolo`,`Autore`,`Pagine`,`ISBN`,`Scaffale`,`Disponibile`) VALUES (7,'Analisi 2','rossi','600','468','4F',1);
INSERT INTO `libri` (`ID`,`Titolo`,`Autore`,`Pagine`,`ISBN`,`Scaffale`,`Disponibile`) VALUES (8,'Geometria','verdi','250','954','5G',1);
INSERT INTO `libri` (`ID`,`Titolo`,`Autore`,`Pagine`,`ISBN`,`Scaffale`,`Disponibile`) VALUES (9,'Metodi','gialli','300','699','2F',1);
