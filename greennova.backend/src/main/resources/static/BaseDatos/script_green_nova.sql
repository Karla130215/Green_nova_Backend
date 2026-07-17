-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema s9555_bootcamp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema s9555_bootcamp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `s9555_bootcamp` DEFAULT CHARACTER SET utf8 ;
USE `s9555_bootcamp` ;

-- -----------------------------------------------------
-- Table `s9555_bootcamp`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s9555_bootcamp`.`usuarios` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `telefono` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s9555_bootcamp`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s9555_bootcamp`.`productos` (
  `id_productos` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `tipo_producto` VARCHAR(45) NULL,
  `precio` DECIMAL(10,2) NULL,
  `imagen` VARCHAR(50) NULL,
  `r_luz` VARCHAR(45) NULL,
  `f_riego` VARCHAR(45) NULL,
  `funcion` VARCHAR(45) NULL,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`id_productos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s9555_bootcamp`.`contacto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s9555_bootcamp`.`contacto` (
  `id_mensaje` INT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `mensaje` VARCHAR(45) NULL,
  `fecha_envio` DATETIME NULL,
  PRIMARY KEY (`id_mensaje`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s9555_bootcamp`.`Pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s9555_bootcamp`.`Pedidos` (
  `id_pedido` INT NOT NULL,
  `fecha_pedido` VARCHAR(45) NULL,
  `total` DECIMAL(10,2) NULL,
  `estado` VARCHAR(45) NULL,
  `usuarios_id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_pedido`),
  INDEX `fk_Pedidos_usuarios_idx` (`usuarios_id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Pedidos_usuarios`
    FOREIGN KEY (`usuarios_id_usuario`)
    REFERENCES `s9555_bootcamp`.`usuarios` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s9555_bootcamp`.`detalle_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s9555_bootcamp`.`detalle_pedido` (
  `id_detalle` INT NOT NULL AUTO_INCREMENT,
  `cantidad` INT NULL,
  `precio_unidad` DECIMAL(10,2) NULL,
  `productos_id_productos` INT NOT NULL,
  PRIMARY KEY (`id_detalle`, `productos_id_productos`),
  INDEX `fk_detalle_pedido_productos1_idx` (`productos_id_productos` ASC) VISIBLE,
  CONSTRAINT `fk_detalle_pedido_productos1`
    FOREIGN KEY (`productos_id_productos`)
    REFERENCES `s9555_bootcamp`.`productos` (`id_productos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s9555_bootcamp`.`detalle_pedido_has_Pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s9555_bootcamp`.`detalle_pedido_has_Pedidos` (
  `detalle_pedido_id_detalle` INT NOT NULL,
  `Pedidos_id_pedido` INT NOT NULL,
  PRIMARY KEY (`detalle_pedido_id_detalle`, `Pedidos_id_pedido`),
  INDEX `fk_detalle_pedido_has_Pedidos_Pedidos1_idx` (`Pedidos_id_pedido` ASC) VISIBLE,
  INDEX `fk_detalle_pedido_has_Pedidos_detalle_pedido1_idx` (`detalle_pedido_id_detalle` ASC) VISIBLE,
  CONSTRAINT `fk_detalle_pedido_has_Pedidos_detalle_pedido1`
    FOREIGN KEY (`detalle_pedido_id_detalle`)
    REFERENCES `s9555_bootcamp`.`detalle_pedido` (`id_detalle`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_pedido_has_Pedidos_Pedidos1`
    FOREIGN KEY (`Pedidos_id_pedido`)
    REFERENCES `s9555_bootcamp`.`Pedidos` (`id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
