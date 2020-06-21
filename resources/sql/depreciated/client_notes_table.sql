CREATE TABLE `taxidermy`.`client_notes` (
  `id_notes` BIGINT(8) NOT NULL AUTO_INCREMENT,
  `id_client_n` BIGINT(8) NOT NULL,
  `notes` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_notes`),
  INDEX `id_client_idx` (`id_client_n` ASC),
  CONSTRAINT `id_client_n`
    FOREIGN KEY (`id_client_n`)
    REFERENCES `taxidermy`.`client` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;