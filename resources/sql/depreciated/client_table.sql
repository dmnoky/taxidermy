CREATE TABLE `taxidermy`.`client` (
  `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `article` INT UNSIGNED NULL,
  `enabled` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `article_UNIQUE` (`article` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
