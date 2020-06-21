CREATE TABLE `taxidermy`.`animals_products` (
  `id_animals_products` BIGINT(8) NOT NULL AUTO_INCREMENT,
  `id_animal_p` BIGINT(8) NOT NULL,
  `id_product_a` BIGINT(8) NOT NULL,
  PRIMARY KEY (`id_animals_products`),
  INDEX `id_animal_p_idx` (`id_animal_p` ASC),
  INDEX `id_product_a_idx` (`id_product_a` ASC),
  CONSTRAINT `id_animal_p`
    FOREIGN KEY (`id_animal_p`)
    REFERENCES `taxidermy`.`animal` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_product_a`
    FOREIGN KEY (`id_product_a`)
    REFERENCES `taxidermy`.`product` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
