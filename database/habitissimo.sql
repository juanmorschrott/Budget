DROP DATABASE 'habitissimo';

CREATE DATABASE IF NOT EXISTS 'habitissimo';

CREATE USER 'habitissimo'@'%' IDENTIFIED BY 'habitissimo' PASSWORD EXPIRE;

GRANT ALL PRIVILEGES ON habitissimo.*  TO 'habitissimo'@'%';

FLUSH PRIVILEGES;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Table `habitissimo`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `habitissimo`.`User` ;

CREATE TABLE IF NOT EXISTS `habitissimo`.`User` (
  `id` INT NOT NULL,
  `email` VARCHAR(100) NULL,
  `phone` VARCHAR(45) NULL,
  `address` VARCHAR(250) NULL,
  `Usercol` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `habitissimo`.`Budget`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `habitissimo`.`Budget` ;

CREATE TABLE IF NOT EXISTS `habitissimo`.`Budget` (
  `id` INT NOT NULL,
  `title` VARCHAR(50) NULL,
  `description` VARCHAR(250) NULL,
  `category` VARCHAR(50) NULL,
  `state` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `habitissimo`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
