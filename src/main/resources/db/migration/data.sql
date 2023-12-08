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