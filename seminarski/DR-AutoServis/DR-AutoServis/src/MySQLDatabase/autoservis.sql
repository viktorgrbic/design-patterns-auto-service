/*
Softverski sistem: Auto servis (nalog za servis)
Baza podataka: autoservis
Napravljeno po uzoru na PAF41_database.sql (prof. dr Sinisa Vlajic)
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;
/*!40101 SET SQL_MODE=''*/;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `autoservis` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `autoservis`;

/* ========================= */
/* Tabela counter            */
/* (brojac za SifraNaloga)   */
/* ========================= */

DROP TABLE IF EXISTS `counter`;

CREATE TABLE `counter` (
  `TableName` varchar(100) NOT NULL DEFAULT '',
  `Counter` int(11) DEFAULT '0',
  PRIMARY KEY (`TableName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `counter` (`TableName`,`Counter`) VALUES
('DKNalogZaServis',0);

/* ========================= */
/* Tabela dknalogzaservis    */
/* ========================= */

DROP TABLE IF EXISTS `dknalogzaservis`;

CREATE TABLE `dknalogzaservis` (
  `SifraNaloga` int(11) NOT NULL DEFAULT '0',
  `RegistarskiBroj` varchar(9) DEFAULT NULL,
  `SifraMehanicara` int(11) DEFAULT '0',
  `ProcenjenoTrajanje` int(11) DEFAULT '0',
  `DatumPrijema` date DEFAULT NULL,
  PRIMARY KEY (`SifraNaloga`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* ========================= */
/* Tabela dkmehanicar        */
/* (koristi se u Chain of    */
/*  Responsibility projektu) */
/* ========================= */

DROP TABLE IF EXISTS `dkmehanicar`;

CREATE TABLE `dkmehanicar` (
  `SifraMehanicara` int(11) NOT NULL,
  `ImePrezime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`SifraMehanicara`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `dkmehanicar` (`SifraMehanicara`,`ImePrezime`) VALUES
(1,'Marko Petrovic'),
(2,'Nikola Jovanovic'),
(3,'Stefan Ilic'),
(4,'Dejan Markovic');

/* ========================= */
/* Procedura upgrade_database */
/* ========================= */

/*!50003 DROP PROCEDURE IF EXISTS `upgrade_database` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `upgrade_database`()
BEGIN

 DECLARE _count INT;

 /* Provera kolona u counter tabeli */

 SET _count = (
   SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS
   WHERE TABLE_SCHEMA = 'autoservis'
   AND TABLE_NAME = 'counter'
   AND COLUMN_NAME = 'TableName'
 );

 IF _count = 0 THEN
   ALTER TABLE counter ADD COLUMN `TableName` varchar(100);
 END IF;

 SET _count = (
   SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS
   WHERE TABLE_SCHEMA = 'autoservis'
   AND TABLE_NAME = 'counter'
   AND COLUMN_NAME = 'Counter'
 );

 IF _count = 0 THEN
   ALTER TABLE counter ADD COLUMN `Counter` int DEFAULT 0;
 END IF;

 /* Ako nema zapisa u counter tabeli */

 SET _count = (SELECT COUNT(*) FROM counter);

 IF _count = 0 THEN
   INSERT INTO `counter` (`TableName`,`Counter`)
   VALUES ('DKNalogZaServis',0);
 END IF;

END */$$

DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*
NAPOMENA za State projekat:
U State projektu nalog ima i stanje (Otvoren / Zavrsen / Otkazan).
Pre pokretanja State projekta izvrsiti:

ALTER TABLE `dknalogzaservis` ADD COLUMN `Stanje` varchar(20) DEFAULT 'Otvoren';
*/
