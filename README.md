# SakhShop

**Это Сахалинский проект - Сахалинский Amazon.**

1. Установить базу данных **sakhshop.sql**


2. Установить **роли**:

INSERT INTO `role_user` (`id`, `name`) VALUES ('1', 'ROLE_USER');


INSERT INTO `role_seller` (`id`, `name`) VALUES ('1', 'ROLE_SELLER');


INSERT INTO `role_admin` (`id`, `name`) VALUES ('1', 'ROLE_ADMIN');


INSERT INTO `role_logistics_person` (`id`, `name`) VALUES ('1', 'ROLE_LOGISTICS_PERSON');


INSERT INTO `role_logistics_company` (`id`, `name`) VALUES ('1', 'ROLE_LOGISTICS_COMPANY');


INSERT INTO `model_admin` (`id`, `account_non_locked`, `email`, `enabled`, `name`, `password`, `phone`, `token`) VALUES ('1', b'1', 'bbb@bb.bb', b'1', 'Роман', '$2a$12$oUcljGf89hDLSJhfhHl3PemCH3bTnpM6jBZXewv83i9F3gZkMB6EK', '79240000000', NULL);
INSERT INTO `join_a_admin_and_role_admin` (`admin_id`, `role_admin_id`) VALUES ('1', '1');





