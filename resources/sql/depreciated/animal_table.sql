CREATE TABLE `taxidermy`.`animal` (
  `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `id_subsidiary` BIGINT(8) NULL,
  `id_type` BIGINT(8) NULL,
  `article` INT NULL,
  `number` INT NULL,
  `width` DOUBLE NULL,
  `height` DOUBLE NULL,
  `weight` DOUBLE NULL,
  `price` DOUBLE NULL,
  `deposit` DOUBLE NULL,
  `content` LONGTEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `article_UNIQUE` (`article` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;