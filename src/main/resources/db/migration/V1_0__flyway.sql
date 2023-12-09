/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;

#create sequence `hibernateSequence`;
create sequence `entitySequence`;
create sequence `linkSequence`;

CREATE TABLE IF NOT EXISTS `person`
(
    `id`                   bigint(20) unsigned NOT NULL,
    `map_data`             longtext                                           DEFAULT NULL CHECK (json_valid(`map_data`)),
    `name`                 tinytext            NOT NULL,
    `email`                varchar(256)        NOT NULL,
    `password`             tinytext                                           DEFAULT NULL,
    `active`               bit(1)              NOT NULL,
    `attachment_list_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `creator`              longtext                                           DEFAULT NULL CHECK (json_valid(`creator`)),
    `editor`               longtext                                           DEFAULT NULL CHECK (json_valid(`editor`)),
    `created_by`           varchar(100)                                       DEFAULT NULL,
    `created`              timestamp           NULL                           DEFAULT NULL,
    `updated_by`           varchar(100)                                       DEFAULT NULL,
    `updated`              timestamp           NULL                           DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_person_email` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `role`
(
    `id`   bigint(20) unsigned NOT NULL,
    `name` varchar(100)        NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `person_roles`
(
    person_id bigint(20) unsigned NOT NULL,
    roles_id  bigint(20) unsigned NOT NULL,
    primary key (person_id, roles_id),
    foreign key (person_id) references person (id),
    foreign key (roles_id) references role (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `product`
(
    `id`       bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `id_shop`  bigint(20) unsigned NOT NULL,
    `slug`     tinytext            NOT NULL,
    `name`     tinytext            NOT NULL,
    `quantity` int(11)             NOT NULL,
    PRIMARY KEY (`id`),
    KEY `foreignKey_product_shopId` (`id_shop`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `shop`
(
    `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `map_data`   longtext                 DEFAULT NULL CHECK (json_valid(`map_data`)),
    `slug`       varchar(128)        NOT NULL,
    `name`       tinytext            NOT NULL,
    `creator`    longtext                 DEFAULT NULL CHECK (json_valid(`creator`)),
    `editor`     longtext                 DEFAULT NULL CHECK (json_valid(`editor`)),
    `created_by` varchar(100)             DEFAULT NULL,
    `created`    timestamp           NULL DEFAULT NULL,
    `updated_by` varchar(100)             DEFAULT NULL,
    `updated`    timestamp           NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_shop_slug` (`slug`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13657
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `shopsetting`
(
    `id`      bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `id_shop` bigint(20) unsigned NOT NULL,
    `value`   tinytext            NOT NULL,
    PRIMARY KEY (`id`),
    KEY `foreignKey_shopSetting_shopId` (`id_shop`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `template`
(
    `id`         bigint(20) unsigned NOT NULL,
    `map_data`   longtext                 DEFAULT NULL CHECK (json_valid(`map_data`)),
    `created_by` varchar(100)             DEFAULT NULL,
    `created`    timestamp           NULL DEFAULT NULL,
    `updated_by` varchar(100)             DEFAULT NULL,
    `updated`    timestamp           NULL DEFAULT NULL,
    `creator`    longtext                 DEFAULT NULL CHECK (json_valid(`creator`)),
    `editor`     longtext                 DEFAULT NULL CHECK (json_valid(`editor`)),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `tenant`
(
    `id`       bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `name`     tinytext            NOT NULL,
    `map_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4;

/*!40101 SET SQL_MODE = IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS = IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;

-- role
INSERT INTO role (id, name)
VALUES (1001, 'ROLE_USER');
INSERT INTO role (id, name)
VALUES (1002, 'ROLE_TENANT');
INSERT INTO role (id, name)
VALUES (1003, 'ROLE_ADMIN');

-- person: password -> 1234
INSERT INTO person (id, map_data, name, email, password, active, attachment_list_data, creator, editor,
                          created_by, created, updated_by, updated)
VALUES (1001, '{"address":{"city":"Bandung"}}', 'John Travolta', 'john@mail.com', '$2a$10$mtEAmwAl1SSg/cfuavxME.3wBqlsTSIv.jjdmq73k8TlHmPBTRCDi',
        true, '{}', null, null, 'system', '2021-10-04 09:42:29', 'system', '2021-10-04 09:42:29');
INSERT INTO person (id, map_data, name, email, password, active, attachment_list_data, creator, editor,
                          created_by, created, updated_by, updated)
VALUES (1002, '{"address":{"city":"Jakarta"}}', 'Will Smith', 'will@mail.com', '$2a$10$MKtR6IhurqMaLZW4IaWdtugcqjAElDpnXcSkmG.cpHhYA2o1dOyGu', true,
        '{}', null, null, 'system', '2021-10-04 09:42:29', 'system', '2021-10-04 09:42:29');
INSERT INTO person (id, map_data, name, email, password, active, attachment_list_data, creator, editor,
                          created_by, created, updated_by, updated)
VALUES (1003, '{"address":{"city":"Surabaya"}}', 'Jim Carry', 'jim@mail.com', '$2a$10$/JQTGUEkGGieCve9z14couDy1oy1qGD/g0UDW/J/IYqYsBrN2Abtu', false,
        '{}', null, null, 'system', '2021-10-04 09:42:29', 'system', '2021-10-04 09:42:29');
INSERT INTO person (id, map_data, name, email, password, active, attachment_list_data, creator, editor,
                          created_by, created, updated_by, updated)
VALUES (1004, '{"address":{"city":"Bogor"}}', 'Arnold Schwarzenegger', 'arnold@mail.com',
        '$2a$10$e0zU/jyKba32V8NuFpW07OdgnbWUIk.0UYt9UqDFXrlQuznPE31qy', true, '{}', null, null, 'system',
        '2021-10-04 09:42:29', 'system', '2021-10-04 09:42:29');

INSERT INTO person_roles (person_id, roles_id) VALUES (1001, 1001);
INSERT INTO person_roles (person_id, roles_id) VALUES (1001, 1002);
INSERT INTO person_roles (person_id, roles_id) VALUES (1002, 1002);
INSERT INTO person_roles (person_id, roles_id) VALUES (1003, 1003);
INSERT INTO person_roles (person_id, roles_id) VALUES (1004, 1001);
INSERT INTO person_roles (person_id, roles_id) VALUES (1004, 1002);
INSERT INTO person_roles (person_id, roles_id) VALUES (1004, 1003);

-- shop
insert into shop (id, map_data, slug, name, creator, editor, created_by, created, updated_by, updated)
values (1, '{}', 'slug-001', 'Shop 001', null, null, 'User test', current_timestamp, 'User Test', current_timestamp);

-- product
insert into product (id, id_shop, slug, name, quantity)
values (1, 1, 'slug-001', 'Product 001', 1);
insert into product (id, id_shop, slug, name, quantity)
values (2, 1, 'slug-001', 'Product 002', 1);
insert into product (id, id_shop, slug, name, quantity)
values (3, 1, 'slug-001', 'Product 003', 1);

-- template
insert into template (id, map_data, created_by, created, updated_by, updated, creator, editor)
values (1, '{}', 'system', '2021-10-04 09:42:29', 'system', '2021-10-04 09:42:29', null, null);
