CREATE TABLE `taxidermy`.`animal_notes` (
  `id_notes` BIGINT(8) NOT NULL AUTO_INCREMENT,
  `id_animal_n` BIGINT(8) NOT NULL,
  `notes` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_notes`),
  INDEX `id_animal_idx` (`id_animal_n` ASC),
  CONSTRAINT `id_animal_n`
    FOREIGN KEY (`id_animal_n`)
    REFERENCES `taxidermy`.`animal` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;