-- -----------------------------------------------------
-- Schema ris
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `ris` DEFAULT CHARACTER SET utf8mb4 ;
USE `ris` ;

-- -----------------------------------------------------
-- Table `ris`.`employer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ris`.`employer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ris`.`steward`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ris`.`steward` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ssn` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `relation` VARCHAR(45) NULL,
  `home_phone` VARCHAR(45) NULL,
  `cell_phone` VARCHAR(45) NULL,
  `work_phone` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ris`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ris`.`patient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ssn` VARCHAR(20) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_initial` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birthdate` VARCHAR(45) NOT NULL,
  `sex` VARCHAR(45) NOT NULL,
  `home_phone` VARCHAR(45) NULL,
  `cell_phone` VARCHAR(45) NULL,
  `work_phone` VARCHAR(45) NULL,
  `street` VARCHAR(100) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `zip` VARCHAR(20) NOT NULL,
  `employer_id` INT NULL,
  `steward_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Patient_Employer_idx` (`employer_id` ASC),
  INDEX `FK_Patient_Steward_idx` (`steward_id` ASC),
  CONSTRAINT `FK_Patient_Employer`
    FOREIGN KEY (`employer_id`)
    REFERENCES `ris`.`employer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `FK_Patient_Steward`
    FOREIGN KEY (`steward_id`)
    REFERENCES `ris`.`steward` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ris`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ris`.`image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(255) NOT NULL,
  `patient_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Image_Patient_idx` (`patient_id` ASC),
  CONSTRAINT `FK_Image_Patient`
    FOREIGN KEY (`patient_id`)
    REFERENCES `ris`.`patient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;
