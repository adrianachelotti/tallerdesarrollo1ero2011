SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `CONDUCTOR`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CONDUCTOR` ;

CREATE  TABLE IF NOT EXISTS `CONDUCTOR` (
  `TIPO_DOCUMENTO` VARCHAR(3) NOT NULL ,
  `NUMERO_DOCUMENTO` INT UNSIGNED NOT NULL ,
  `SEXO` CHAR NOT NULL ,
  `FECHA_NACIMIENTO` DATE NOT NULL ,
  `LUGAR_RESIDENCIA` VARCHAR(35) NOT NULL ,
  `ESTADO_CIVIL` VARCHAR(10) NOT NULL ,
  `PROFESION` VARCHAR(35) NULL ,
  `NIVEL_ESTUDIO` VARCHAR(35) NULL ,
  `INGRESO_PROMEDIO` FLOAT NULL ,
  `HIJOS` TINYINT UNSIGNED NOT NULL ,
  `NOMBRE` VARCHAR(25) NOT NULL ,
  `APELLIDO` VARCHAR(35) NOT NULL ,
  `SALDO_SCORING` TINYINT UNSIGNED NULL ,
  `CANTIDAD_VECES_CERO_SCORING` TINYINT UNSIGNED NULL ,
  `ID_CONDUCTOR` INT NOT NULL ,
  `EDAD` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`ID_CONDUCTOR`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `CONDUCTOR_UQ1` USING BTREE ON `CONDUCTOR` (`TIPO_DOCUMENTO` ASC, `NUMERO_DOCUMENTO` ASC) ;


-- -----------------------------------------------------
-- Table `VEHICULO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `VEHICULO` ;

CREATE  TABLE IF NOT EXISTS `VEHICULO` (
  `PATENTE` VARCHAR(6) NOT NULL ,
  `MODELO` TINYINT NOT NULL ,
  `ESTADO_EXTERIOR` VARCHAR(20) NOT NULL ,
  `ESTADO_INTERIOR` VARCHAR(20) NOT NULL ,
  `ESTADO_MOTOR` VARCHAR(20) NOT NULL ,
  `MECANICA` VARCHAR(45) NULL ,
  PRIMARY KEY (`PATENTE`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VERIFICACION_TECNICA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `VERIFICACION_TECNICA` ;

CREATE  TABLE IF NOT EXISTS `VERIFICACION_TECNICA` (
  `NUMERO_OBLEA` INT UNSIGNED NOT NULL ,
  `ZONA` VARCHAR(45) NOT NULL ,
  `ESTACION` VARCHAR(45) NOT NULL ,
  `PATENTE` VARCHAR(6) NOT NULL ,
  `FECHA_INSPECCION` DATE NOT NULL ,
  `TIPO_VERIFICACION` VARCHAR(45) NOT NULL ,
  `FECHA_VENCIMIENTO` DATE NOT NULL ,
  `RESULTADO` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`NUMERO_OBLEA`) ,
  CONSTRAINT `VERIFICACION_TECNICA_VEHICULO_FK`
    FOREIGN KEY (`PATENTE` )
    REFERENCES `VEHICULO` (`PATENTE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `VERIFICACION_TECNICA_VEHICULO_FK` ON `VERIFICACION_TECNICA` (`PATENTE` ASC) ;


-- -----------------------------------------------------
-- Table `DEUDA_SISTEMA_FINANCIERO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DEUDA_SISTEMA_FINANCIERO` ;

CREATE  TABLE IF NOT EXISTS `DEUDA_SISTEMA_FINANCIERO` (
  `TIPO_CLAVE_UNICA` VARCHAR(4) NOT NULL ,
  `CLAVE_UNICA` VARCHAR(15) NOT NULL ,
  `INHABILITACION_JUDICIAL` TINYINT(1)  NULL ,
  `INHABILITACION_MULTA` TINYINT(1)  NULL ,
  `CHEQUES_RECHAZADOS` TINYINT UNSIGNED NULL ,
  `PRESTAMOS` TINYINT UNSIGNED NULL ,
  `PRESTAMOS_CALIFICACION_MAYOR` TINYINT UNSIGNED NULL ,
  `MONTO_DEUDA_NORMAL` FLOAT NULL ,
  `MONTO_DEUDA_ESPECIAL` FLOAT NULL ,
  `MONTO_DEUDA_EN_PROBLEMAS` FLOAT NULL ,
  `MONTO_DEUDA_INSOLVENTE` FLOAT NULL ,
  `MONTO_DEUDA_IRRECUPERABLE` FLOAT NULL ,
  `PROMEDIO_DIAS_ATRASO` FLOAT NULL ,
  `MONTO_MAXIMO` FLOAT NULL ,
  `ID_DEUDA` INT NOT NULL ,
  PRIMARY KEY (`ID_DEUDA`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `DEUDA_UQ1` ON `DEUDA_SISTEMA_FINANCIERO` (`TIPO_CLAVE_UNICA` ASC, `CLAVE_UNICA` ASC) ;


-- -----------------------------------------------------
-- Table `CONDUCTOR_SISTEMA_FINANCIERO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CONDUCTOR_SISTEMA_FINANCIERO` ;

CREATE  TABLE IF NOT EXISTS `CONDUCTOR_SISTEMA_FINANCIERO` (
  `ID_DEUDA_SISTEMA_FINANCIERO` INT NOT NULL ,
  `ID_CONDUCTOR` INT NOT NULL ,
  PRIMARY KEY (`ID_DEUDA_SISTEMA_FINANCIERO`, `ID_CONDUCTOR`) ,
  CONSTRAINT `DEUDA_SISTEMA_FINANCIERO_FK1`
    FOREIGN KEY (`ID_DEUDA_SISTEMA_FINANCIERO` )
    REFERENCES `DEUDA_SISTEMA_FINANCIERO` (`ID_DEUDA` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CONDUCTOR`
    FOREIGN KEY (`ID_CONDUCTOR` )
    REFERENCES `CONDUCTOR` (`ID_CONDUCTOR` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `DEUDA_SISTEMA_FINANCIERO_FK1` ON `CONDUCTOR_SISTEMA_FINANCIERO` (`ID_DEUDA_SISTEMA_FINANCIERO` ASC) ;

CREATE INDEX `CONDUCTOR` ON `CONDUCTOR_SISTEMA_FINANCIERO` (`ID_CONDUCTOR` ASC) ;


-- -----------------------------------------------------
-- Table `EXPEDIENTE_JUDICIAL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EXPEDIENTE_JUDICIAL` ;

CREATE  TABLE IF NOT EXISTS `EXPEDIENTE_JUDICIAL` (
  `NUMERO_EXPEDIENTE` INT NOT NULL ,
  `SITUACION` CHAR NOT NULL ,
  `OBJETO` VARCHAR(45) NULL ,
  `ID_CONDUCTOR` INT NOT NULL ,
  PRIMARY KEY (`NUMERO_EXPEDIENTE`) ,
  CONSTRAINT `ESPEDIENTE_CONDUCTOR_FK1`
    FOREIGN KEY (`ID_CONDUCTOR` )
    REFERENCES `CONDUCTOR` (`ID_CONDUCTOR` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `ESPEDIENTE_CONDUCTOR_FK1` ON `EXPEDIENTE_JUDICIAL` (`ID_CONDUCTOR` ASC) ;


-- -----------------------------------------------------
-- Table `SINIESTRO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SINIESTRO` ;

CREATE  TABLE IF NOT EXISTS `SINIESTRO` (
  `ID_SINIESTRO` INT NOT NULL ,
  `TIPO_SINIESTRO` VARCHAR(10) NOT NULL ,
  `FECHA` DATE NOT NULL ,
  `DESCRIPCION` VARCHAR(30) NULL ,
  `LUGAR` VARCHAR(30) NULL ,
  `MONTO` FLOAT NOT NULL ,
  PRIMARY KEY (`ID_SINIESTRO`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VEHICULO_SINIESTRO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `VEHICULO_SINIESTRO` ;

CREATE  TABLE IF NOT EXISTS `VEHICULO_SINIESTRO` (
  `PATENTE` VARCHAR(6) NOT NULL ,
  `ID_SINIESTRO` INT NOT NULL ,
  PRIMARY KEY (`PATENTE`, `ID_SINIESTRO`) ,
  CONSTRAINT `VEHICULO_FK1`
    FOREIGN KEY (`PATENTE` )
    REFERENCES `VEHICULO` (`PATENTE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `SINIESTRO_FK2`
    FOREIGN KEY (`ID_SINIESTRO` )
    REFERENCES `SINIESTRO` (`ID_SINIESTRO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `VEHICULO_FK1` ON `VEHICULO_SINIESTRO` (`PATENTE` ASC) ;

CREATE INDEX `SINIESTRO_FK2` ON `VEHICULO_SINIESTRO` (`ID_SINIESTRO` ASC) ;


-- -----------------------------------------------------
-- Table `CONDUCTOR_VEHICULO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CONDUCTOR_VEHICULO` ;

CREATE  TABLE IF NOT EXISTS `CONDUCTOR_VEHICULO` (
  `ID_CONDUCTOR` INT NOT NULL ,
  `PATENTE` VARCHAR(6) NOT NULL ,
  PRIMARY KEY (`ID_CONDUCTOR`, `PATENTE`) ,
  CONSTRAINT `CONDUCTOR_VEHICULO_FK1`
    FOREIGN KEY (`ID_CONDUCTOR` )
    REFERENCES `CONDUCTOR` (`ID_CONDUCTOR` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CONDUCTOR_VEHICULO_FK2`
    FOREIGN KEY (`PATENTE` )
    REFERENCES `VEHICULO` (`PATENTE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `CONDUCTOR_VEHICULO_FK1` ON `CONDUCTOR_VEHICULO` (`ID_CONDUCTOR` ASC) ;

CREATE INDEX `CONDUCTOR_VEHICULO_FK2` ON `CONDUCTOR_VEHICULO` (`PATENTE` ASC) ;


-- -----------------------------------------------------
-- Table `INFRACCION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `INFRACCION` ;

CREATE  TABLE IF NOT EXISTS `INFRACCION` (
  `NUMERO_INFRACCION` INT UNSIGNED NOT NULL ,
  `DESCRIPCION_INFRACCION` VARCHAR(100) NOT NULL ,
  `FECHA_INFRACCION` DATE NOT NULL ,
  `LUGAR_INFRACCION` VARCHAR(100) NOT NULL ,
  `NUMERO_ACTA` INT UNSIGNED NOT NULL ,
  `TIPO_ACTA` VARCHAR(25) NOT NULL ,
  `ESTADO_UACF` VARCHAR(25) NOT NULL ,
  `PATENTE` VARCHAR(6) NOT NULL ,
  `ID_CONDUCTOR` INT NOT NULL ,
  PRIMARY KEY (`NUMERO_INFRACCION`) ,
  CONSTRAINT `INFRACCION_CONDUCTOR_FK1`
    FOREIGN KEY (`ID_CONDUCTOR` )
    REFERENCES `CONDUCTOR` (`ID_CONDUCTOR` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `INFRACCION_VEHICULO_FK2`
    FOREIGN KEY (`PATENTE` )
    REFERENCES `VEHICULO` (`PATENTE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `INFRACCION_VEHICULO_FK2` ON `INFRACCION` (`PATENTE` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;