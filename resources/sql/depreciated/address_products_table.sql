CREATE TABLE `taxidermy`.`address_products` (
  `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
  `id_product_address` BIGINT(8) NOT NULL,
  `id_address_pr` BIGINT(8) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_product_address_idx` (`id_product_address` ASC),
  INDEX `id_address_pr_idx` (`id_address_pr` ASC),
  CONSTRAINT `id_product_address`
    FOREIGN KEY (`id_product_address`)
    REFERENCES `taxidermy`.`product` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_address_pr`
    FOREIGN KEY (`id_address_pr`)
    REFERENCES `taxidermy`.`address` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;