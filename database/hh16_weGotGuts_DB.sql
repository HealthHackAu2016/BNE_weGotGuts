-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hh_guts_public
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hh_guts_public
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hh_guts_public` DEFAULT CHARACTER SET utf8 ;
USE `hh_guts_public` ;

-- -----------------------------------------------------
-- Table `hh_guts_public`.`tresponder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hh_guts_public`.`tresponder` ;

CREATE TABLE IF NOT EXISTS `hh_guts_public`.`tresponder` (
  `idResponder` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idResponder`),
  UNIQUE INDEX `idtResponder_UNIQUE` (`idResponder` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hh_guts_public`.`tibdocreading`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hh_guts_public`.`tibdocreading` ;

CREATE TABLE IF NOT EXISTS `hh_guts_public`.`tibdocreading` (
  `idResponder` VARCHAR(45) NOT NULL,
  `Reading` VARCHAR(45) NOT NULL,
  `dtSubmit` DATETIME NOT NULL,
  PRIMARY KEY (`idResponder`, `dtSubmit`),
  CONSTRAINT `RespReading`
    FOREIGN KEY (`idResponder`)
    REFERENCES `hh_guts_public`.`tresponder` (`idResponder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hh_guts_public`.`tsurveyreshbi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hh_guts_public`.`tsurveyreshbi` ;

CREATE TABLE IF NOT EXISTS `hh_guts_public`.`tsurveyreshbi` (
  `idResponder` VARCHAR(45) NOT NULL,
  `dtSubmit` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `genWellbeing` INT(1) NULL DEFAULT NULL,
  `abdPain` INT(1) NULL DEFAULT NULL,
  `lqdStoolFreq` INT(10) NULL DEFAULT NULL,
  `adbMass` INT(1) NULL DEFAULT NULL,
  `jointProb` INT(1) NULL DEFAULT NULL,
  `eyeProb` INT(1) NULL DEFAULT NULL,
  `mouthProb` INT(1) NULL DEFAULT NULL,
  `skinProbUlcers` INT(1) NULL DEFAULT NULL,
  `skinProbRedBumps` INT(1) NULL DEFAULT NULL,
  `perianalProb` INT(1) NULL DEFAULT NULL,
  `fistula` INT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idResponder`, `dtSubmit`),
  CONSTRAINT `FK_HBI_RES`
    FOREIGN KEY (`idResponder`)
    REFERENCES `hh_guts_public`.`tresponder` (`idResponder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hh_guts_public`.`tSurveyResSCCAI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hh_guts_public`.`tSurveyResSCCAI` ;

CREATE TABLE IF NOT EXISTS `hh_guts_public`.`tSurveyResSCCAI` (
  `idResponder` VARCHAR(45) NOT NULL,
  `dtSubmit` DATETIME NOT NULL,
  `DBowleFreq` INT(1) NULL,
  `NBowleFreq` INT(1) NULL,
  `urgency` INT(1) NULL,
  `blood` INT(1) NULL,
  `GenWellbeing` INT(1) NULL,
  `Extracolonics` INT(1) NULL,
  PRIMARY KEY (`idResponder`, `dtSubmit`),
  CONSTRAINT `FK_SCCAI_RES`
    FOREIGN KEY (`idResponder`)
    REFERENCES `hh_guts_public`.`tresponder` (`idResponder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
