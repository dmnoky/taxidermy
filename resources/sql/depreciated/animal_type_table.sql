CREATE TABLE `taxidermy`.`animal_type` (
  `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `content` LONGTEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;