-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema vps
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema vps
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vps` DEFAULT CHARACTER SET latin1 ;
USE `vps` ;

-- -----------------------------------------------------
-- Table `vps`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vps`.`admin` (
  `NameAdmin` VARCHAR(10) NOT NULL,
  `PassAdmin` VARCHAR(15) NOT NULL,
  `Securityques` VARCHAR(50) NULL DEFAULT NULL,
  `Ans` VARCHAR(45) NULL DEFAULT NULL,
  `Mobileno` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`NameAdmin`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vps`.`price`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vps`.`price` (
  `Vehicle_type` VARCHAR(20) NOT NULL,
  `PriceForHr` INT(11) NOT NULL,
  PRIMARY KEY (`Vehicle_type`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vps`.`vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vps`.`vehicle` (
  `Vehicleno` VARCHAR(11) NOT NULL,
  `OwnFname` VARCHAR(45) NOT NULL,
  `OwnerLname` VARCHAR(45) NOT NULL,
  `v_type` VARCHAR(45) NOT NULL,
  `mobile` VARCHAR(10) NOT NULL,
  `DurationInHour` INT(11) NOT NULL,
  `Amount` INT(11) NOT NULL,
  `EntryTime` VARCHAR(25) NOT NULL,
  `ExitTime` VARCHAR(25) NULL DEFAULT NULL,
  PRIMARY KEY (`Vehicleno`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
