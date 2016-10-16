-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema guts
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
CREATE TABLE IF NOT EXISTS `hh_guts_public`.`tresponder` (
  `idResponder` VARCHAR(45) NOT NULL,
  `activeDisease` INT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idResponder`),
  UNIQUE INDEX `idtResponder_UNIQUE` (`idResponder` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hh_guts_public`.`tfbactivities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hh_guts_public`.`tfbactivities` (
  `idResponder` VARCHAR(45) NOT NULL,
  `dtRecorded` DATETIME NOT NULL,
  `CaloriesBurned` INT(16) NULL DEFAULT '0',
  `Steps` INT(16) NULL DEFAULT '0',
  `Distance` FLOAT NULL DEFAULT '0',
  `Floors` INT(16) NULL DEFAULT '0',
  `MinSedentary` INT(16) NULL DEFAULT '0',
  `MinLightActive` INT(16) NULL DEFAULT '0',
  `MinFairActive` INT(16) NULL DEFAULT '0',
  `MinVeryActive` INT(16) NULL DEFAULT '0',
  `ActivityCalories` INT(16) NULL DEFAULT '0',
  PRIMARY KEY (`idResponder`, `dtRecorded`),
  CONSTRAINT `FK_FB_ACT`
    FOREIGN KEY (`idResponder`)
    REFERENCES `hh_guts_public`.`tresponder` (`idResponder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hh_guts_public`.`tfsleep`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hh_guts_public`.`tfsleep` (
  `idResponder` VARCHAR(45) NOT NULL,
  `dtRecorded` DATETIME NOT NULL,
  `MinAsleep` INT(16) NULL DEFAULT '0',
  `MinAwake` INT(16) NULL DEFAULT '0',
  `NoAwakenings` INT(16) NULL DEFAULT '0',
  `TimeBed` INT(16) NULL DEFAULT '0',
  PRIMARY KEY (`idResponder`, `dtRecorded`),
  CONSTRAINT `FK_FB_SLP`
    FOREIGN KEY (`idResponder`)
    REFERENCES `hh_guts_public`.`tresponder` (`idResponder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hh_guts_public`.`tibdocreading`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hh_guts_public`.`tibdocreading` (
  `idResponder` VARCHAR(45) NOT NULL,
  `Reading` FLOAT NOT NULL DEFAULT '0',
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
CREATE TABLE IF NOT EXISTS `hh_guts_public`.`tsurveyreshbi` (
  `idResponder` VARCHAR(45) NOT NULL,
  `dtSubmit` DATETIME NOT NULL,
  `genWellbeing` INT(1) NULL DEFAULT '0',
  `abdPain` INT(1) NULL DEFAULT '0',
  `lqdStoolFreq` INT(10) NULL DEFAULT '0',
  `adbMass` INT(1) NULL DEFAULT '0',
  `jointProb` INT(1) NULL DEFAULT '0',
  `eyeProb` INT(1) NULL DEFAULT '0',
  `mouthProb` INT(1) NULL DEFAULT '0',
  `skinProbUlcers` INT(1) NULL DEFAULT '0',
  `skinProbRedBumps` INT(1) NULL DEFAULT '0',
  `perianalProb` INT(1) NULL DEFAULT '0',
  `fistula` INT(1) NULL DEFAULT '0',
  PRIMARY KEY (`idResponder`, `dtSubmit`),
  CONSTRAINT `FK_HBI_RES`
    FOREIGN KEY (`idResponder`)
    REFERENCES `hh_guts_public`.`tresponder` (`idResponder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hh_guts_public`.`tsurveyressccai`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hh_guts_public`.`tsurveyressccai` (
  `idResponder` VARCHAR(45) NOT NULL,
  `dtSubmit` DATETIME NOT NULL,
  `DBowleFreq` INT(1) NULL DEFAULT '0',
  `NBowleFreq` INT(1) NULL DEFAULT '0',
  `urgency` INT(1) NULL DEFAULT '0',
  `blood` INT(1) NULL DEFAULT '0',
  `GenWellbeing` INT(1) NULL DEFAULT '0',
  `Extracolonics` INT(1) NULL DEFAULT '0',
  PRIMARY KEY (`idResponder`, `dtSubmit`),
  CONSTRAINT `FK_SCCAI_RES`
    FOREIGN KEY (`idResponder`)
    REFERENCES `hh_guts_public`.`tresponder` (`idResponder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
