CREATE TABLE `taxidermy`.`animal_images` (
  `id_images` BIGINT(8) NOT NULL AUTO_INCREMENT,
  `id_animal_i` BIGINT(8) NOT NULL,
  `images` BLOB NOT NULL,
  PRIMARY KEY (`id_images`),
  INDEX `id_animal_idx` (`id_animal_i` ASC),
  CONSTRAINT `id_animal_i`
    FOREIGN KEY (`id_animal_i`)
    REFERENCES `taxidermy`.`animal` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;