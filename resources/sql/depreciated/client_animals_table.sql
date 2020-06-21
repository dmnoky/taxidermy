CREATE TABLE `taxidermy`.`client_animals` (
  `id_client_animals` BIGINT(8) NOT NULL AUTO_INCREMENT,
  `id_client_a` BIGINT(8) NOT NULL,
  `id_animals_c` BIGINT(8) NOT NULL,
  PRIMARY KEY (`id_client_animals`),
  INDEX `id_client_a_idx` (`id_client_a` ASC),
  INDEX `id_animals_c_idx` (`id_animals_c` ASC),
  CONSTRAINT `id_client_a`
    FOREIGN KEY (`id_client_a`)
    REFERENCES `taxidermy`.`client` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_animals_c`
    FOREIGN KEY (`id_animals_c`)
    REFERENCES `taxidermy`.`animal` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;