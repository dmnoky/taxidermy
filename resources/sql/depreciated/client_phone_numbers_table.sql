CREATE TABLE `taxidermy`.`client_phone_numbers` (
  `id_client_phone_numbers` BIGINT(8) NOT NULL AUTO_INCREMENT,
  `id_client_pn` BIGINT(8) NOT NULL,
  `phone_numbers` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_client_phone_numbers`),
  INDEX `id_client_pn_idx` (`id_client_pn` ASC),
  CONSTRAINT `id_client_pn`
    FOREIGN KEY (`id_client_pn`)
    REFERENCES `taxidermy`.`client` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;