CREATE SCHEMA `taxidermy` DEFAULT CHARACTER SET utf8;

CREATE TABLE `taxidermy`.`animal` (
                                      `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                      `name` VARCHAR(255) NOT NULL,
                                      `id_subsidiary` BIGINT(8) NULL,
                                      `id_type` BIGINT(8) NULL,
                                      `article` VARCHAR(255) NULL,
                                      `number` INT NULL,
                                      `width` DOUBLE NULL,
                                      `height` DOUBLE NULL,
                                      `weight` DOUBLE NULL,
                                      `price` DOUBLE NULL,
                                      `deposit` DOUBLE NULL,
                                      `content` LONGTEXT NULL,
                                      PRIMARY KEY (`id`),
                                      UNIQUE INDEX `article_UNIQUE` (`article` ASC))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`client` (
                                      `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                      `name` VARCHAR(45) NULL,
                                      `surname` VARCHAR(45) NULL,
                                      `email` VARCHAR(45) NULL,
                                      `password` VARCHAR(255) NULL,
                                      `article` VARCHAR(255) NULL,
                                      `enabled` TINYINT(1) NULL DEFAULT 1,
                                      `city` VARCHAR(45) NULL,
                                      `address` VARCHAR(45) NULL,
                                      `more_address` VARCHAR(45) NULL,
                                      `postal_code` VARCHAR(45) NULL,
                                      PRIMARY KEY (`id`),
                                      UNIQUE INDEX `article_UNIQUE` (`article` ASC))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`product` (
                                       `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                       `name` VARCHAR(255) NOT NULL,
                                       `url` VARCHAR(255) NULL,
                                       `type` VARCHAR(45) NULL,
                                       `width` DOUBLE NULL,
                                       `height` DOUBLE NULL,
                                       `weight` DOUBLE NULL,
                                       `price` DOUBLE NULL,
                                       `content` LONGTEXT NULL,
                                       `city` VARCHAR(45) NULL,
                                       `address` VARCHAR(45) NULL,
                                       `more_address` VARCHAR(45) NULL,
                                       `postal_code` VARCHAR(45) NULL,
                                       PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`authorities` (
                                           `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                           `article` VARCHAR(255) NOT NULL,
                                           `authority` VARCHAR(45) NOT NULL,
                                           PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`animal_images` (
                                             `id_images` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                             `id_animal_i` BIGINT(8) NOT NULL,
                                             `images` LONGBLOB NOT NULL,
                                             PRIMARY KEY (`id_images`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`animal_notes` (
                                            `id_notes` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                            `id_animal_n` BIGINT(8) NOT NULL,
                                            `notes` VARCHAR(255) NOT NULL,
                                            PRIMARY KEY (`id_notes`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`animals_products` (
                                                `id_animals_products` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                                `id_animal_p` BIGINT(8) NOT NULL,
                                                `id_product_a` BIGINT(8) NOT NULL,
                                                PRIMARY KEY (`id_animals_products`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`animal_subsidiary` (
                                                 `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                                 `name` VARCHAR(45) NOT NULL,
                                                 `content` LONGTEXT NULL,
                                                 PRIMARY KEY (`id`),
                                                 UNIQUE INDEX `name_UNIQUE` (`name` ASC))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`animal_type` (
                                           `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                           `name` VARCHAR(45) NOT NULL,
                                           `content` LONGTEXT NULL,
                                           PRIMARY KEY (`id`),
                                           UNIQUE INDEX `name_UNIQUE` (`name` ASC))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`client_notes` (
                                            `id_notes` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                            `id_client_n` BIGINT(8) NOT NULL,
                                            `notes` VARCHAR(255) NOT NULL,
                                            PRIMARY KEY (`id_notes`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`worker_notes` (
                                            `id_notes` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                            `id_worker_n` BIGINT(8) NOT NULL,
                                            `notes` VARCHAR(255) NOT NULL,
                                            PRIMARY KEY (`id_notes`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`client_phone_numbers` (
                                                    `id_client_phone_numbers` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                                    `id_client_pn` BIGINT(8) NOT NULL,
                                                    `phone_numbers` VARCHAR(45) NOT NULL,
                                                    PRIMARY KEY (`id_client_phone_numbers`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`clients_animals` (
                                               `id_clients_animals` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                               `id_clients_a` BIGINT(8) NOT NULL,
                                               `id_animals_c` BIGINT(8) NOT NULL,
                                               PRIMARY KEY (`id_clients_animals`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`animal_count` (
                                            `id_animal_count` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                            `id_client_ac` BIGINT(8) NOT NULL,
                                            `id_animal_ac` BIGINT(8) NOT NULL,
                                            `count` INT NOT NULL,
                                            PRIMARY KEY (`id_animal_count`),
                                            INDEX `id_animal_ac_idx` (`id_animal_ac` ASC),
                                            CONSTRAINT `id_animal_ac`
                                                FOREIGN KEY (`id_animal_ac`)
                                                    REFERENCES `taxidermy`.`animal` (`id`)
                                                    ON DELETE CASCADE
                                                    ON UPDATE no action
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `taxidermy`.`worker` (
                                      `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
                                      `name` VARCHAR(45) NULL,
                                      `surname` VARCHAR(45) NULL,
                                      `email` VARCHAR(45) NULL,
                                      `password` VARCHAR(255) NULL,
                                      `article` VARCHAR(255) NULL,
                                      `enabled` TINYINT(1) NULL DEFAULT 1,
                                      PRIMARY KEY (`id`),
                                      UNIQUE INDEX `article_UNIQUE` (`article` ASC))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

INSERT INTO `taxidermy`.`animal_type` (`name`, `content`)
VALUES ('Чучело', 'Набитая чем-либо шкура животного');

INSERT INTO `taxidermy`.`animal_type` (`name`, `content`)
VALUES ('Ковер', 'Напольное изделие из шкуры животного');

INSERT INTO `taxidermy`.`animal_type` (`name`, `content`)
VALUES ('Шкура', 'Обработанная шкура животного');

INSERT INTO `taxidermy`.`animal_type` (`name`, `content`)
VALUES ('Кость', 'Кость животного');

INSERT INTO `taxidermy`.`animal_type` (`name`, `content`)
VALUES ('Аксессуар', 'Что-либо изготовленное из животного');

INSERT INTO `taxidermy`.`animal_subsidiary` (`name`, `content`)
VALUES ('Зверь', 'Медведь, лось, кабан, волк, лиса, бобер, заяц, куница, белка...');

INSERT INTO `taxidermy`.`animal_subsidiary` (`name`, `content`)
VALUES ('Птица', 'Глухарь, тетерев, орел, сова, утка, дятел...');

INSERT INTO `taxidermy`.`animal_subsidiary` (`name`, `content`)
VALUES ('Рыба', 'Щука, налим, окунь, карп, лещ, карась...');

INSERT INTO `taxidermy`.`animal_subsidiary` (`name`, `content`)
VALUES ('Другое', 'Талисман, амулет, жир...');

INSERT INTO `taxidermy`.`worker` (`name`, `surname`, `email`, `password`, `article`)
VALUES ('Сергей', 'Кузнецов', '', '', 'admin');

INSERT INTO `taxidermy`.`authorities` (`article`, `authority`)
VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO `taxidermy`.`authorities` (`article`, `authority`)
VALUES ('admin', 'ROLE_WORKER');

INSERT INTO `taxidermy`.`authorities` (`article`, `authority`)
VALUES ('admin', 'ROLE_USER');