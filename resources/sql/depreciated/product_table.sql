CREATE TABLE `taxidermy`.`product` (
  `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `url` VARCHAR(255) NULL,
  `type` VARCHAR(45) NULL,
  `width` DOUBLE NULL,
  `height` DOUBLE NULL,
  `weight` DOUBLE NULL,
  `price` DOUBLE NULL,
  `content` LONGTEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;