CREATE TABLE `taxidermy`.`address_clients` (
  `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
  `id_client_address` BIGINT(8) NOT NULL,
  `id_address_cl` BIGINT(8) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_client_address_idx` (`id_client_address` ASC),
  INDEX `id_address_cl_idx` (`id_address_cl` ASC),
  CONSTRAINT `id_client_address`
    FOREIGN KEY (`id_client_address`)
    REFERENCES `taxidermy`.`client` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_address_cl`
    FOREIGN KEY (`id_address_cl`)
    REFERENCES `taxidermy`.`address` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;