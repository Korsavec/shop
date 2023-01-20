-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Янв 20 2023 г., 20:27
-- Версия сервера: 8.0.29
-- Версия PHP: 8.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `sakhshop`
--

-- --------------------------------------------------------

--
-- Структура таблицы `join_a_seller_and_role_seller`
--

CREATE TABLE `join_a_seller_and_role_seller` (
  `seller_id` bigint NOT NULL,
  `role_seller_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `join_a_seller_and_role_seller`
--

INSERT INTO `join_a_seller_and_role_seller` (`seller_id`, `role_seller_id`) VALUES
(6, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `join_a_user_and_role_user`
--

CREATE TABLE `join_a_user_and_role_user` (
  `user_id` bigint NOT NULL,
  `role_users_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `join_a_user_and_role_user`
--

INSERT INTO `join_a_user_and_role_user` (`user_id`, `role_users_id`) VALUES
(2, 1),
(3, 1),
(4, 1),
(5, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `join_product_sellers`
--

CREATE TABLE `join_product_sellers` (
  `product_id` bigint NOT NULL,
  `sellers_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `join_product_users`
--

CREATE TABLE `join_product_users` (
  `product_id` bigint NOT NULL,
  `users_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `model_product`
--

CREATE TABLE `model_product` (
  `id` bigint NOT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `bullet1` varchar(500) DEFAULT NULL,
  `bullet2` varchar(500) DEFAULT NULL,
  `bullet3` varchar(500) DEFAULT NULL,
  `bullet4` varchar(500) DEFAULT NULL,
  `bullet5` varchar(500) DEFAULT NULL,
  `date_created_product` datetime(6) NOT NULL,
  `description` varchar(800) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `keyword1` varchar(50) DEFAULT NULL,
  `keyword2` varchar(50) DEFAULT NULL,
  `keyword3` varchar(50) DEFAULT NULL,
  `keyword4` varchar(50) DEFAULT NULL,
  `keyword5` varchar(50) DEFAULT NULL,
  `modified` bit(1) NOT NULL,
  `name` varchar(150) NOT NULL,
  `product_creator_id` bigint DEFAULT NULL,
  `url_img1` varchar(50) NOT NULL,
  `url_img2` varchar(50) DEFAULT NULL,
  `url_img3` varchar(50) DEFAULT NULL,
  `url_img4` varchar(50) DEFAULT NULL,
  `url_img5` varchar(50) DEFAULT NULL,
  `url_img6` varchar(50) DEFAULT NULL,
  `url_img7` varchar(50) DEFAULT NULL,
  `url_img8` varchar(50) DEFAULT NULL,
  `url_img9` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `model_seller`
--

CREATE TABLE `model_seller` (
  `id` bigint NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `apartment` smallint DEFAULT NULL,
  `approval` bit(1) NOT NULL,
  `balance` decimal(12,2) DEFAULT '0.00',
  `bank_account` varchar(20) NOT NULL,
  `bank_name` varchar(50) NOT NULL,
  `beak_bank` int NOT NULL,
  `building` varchar(11) DEFAULT NULL,
  `city` varchar(25) NOT NULL,
  `correspondent_account` varchar(20) NOT NULL,
  `date_birth` date NOT NULL,
  `date_created_seller` datetime(6) NOT NULL,
  `email` varchar(58) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `house` varchar(11) NOT NULL,
  `img_passport` varchar(150) NOT NULL,
  `inn` bigint NOT NULL,
  `inn_bank` bigint NOT NULL,
  `ip_address_first_entrance` varchar(39) DEFAULT NULL,
  `ip_address_last_entrance` varchar(39) DEFAULT NULL,
  `ip_address_reg_confirm` varchar(39) DEFAULT NULL,
  `ip_address_registration` varchar(39) NOT NULL,
  `kpp_bank` int NOT NULL,
  `middle_name` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `number_passport` bigint NOT NULL,
  `password` varchar(65) NOT NULL,
  `phone` bigint NOT NULL,
  `region` varchar(7) NOT NULL,
  `shop_name` varchar(30) NOT NULL,
  `street` varchar(50) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `token` varchar(45) DEFAULT NULL,
  `username` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `model_seller`
--

INSERT INTO `model_seller` (`id`, `account_non_locked`, `apartment`, `approval`, `balance`, `bank_account`, `bank_name`, `beak_bank`, `building`, `city`, `correspondent_account`, `date_birth`, `date_created_seller`, `email`, `enabled`, `house`, `img_passport`, `inn`, `inn_bank`, `ip_address_first_entrance`, `ip_address_last_entrance`, `ip_address_reg_confirm`, `ip_address_registration`, `kpp_bank`, `middle_name`, `name`, `number_passport`, `password`, `phone`, `region`, `shop_name`, `street`, `surname`, `token`, `username`) VALUES
(6, b'1', 4, b'0', '0.00', '11111111111111111111', 'ваыавы', 111111111, '4', 'ваыва', '11111111111111111111', '2023-01-03', '2023-01-20 13:32:33.543011', 'ddd@ddd.dd', b'1', '4', '1/8f/89/58/18f89586989d5d0c8e1be28bd4b68cde.jpg', 111111111111, 1111111111, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', 111111111, 'авпвп', 'вапвп', 1111111111, '$2a$10$BYdPG4A697ga5SmIvHsRbu3JjG5DX6Kn53NvwyQpKmc.h1v2C51KS', 1111111111, 'Сахалин', 'впавп', 'ыва', 'павп', NULL, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `model_users`
--

CREATE TABLE `model_users` (
  `id` bigint NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `date_birth` date DEFAULT NULL,
  `date_created_user` datetime(6) NOT NULL,
  `email` varchar(58) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `ip_address_first_entrance` varchar(39) DEFAULT NULL,
  `ip_address_last_entrance` varchar(39) DEFAULT NULL,
  `ip_address_reg_confirm` varchar(39) DEFAULT NULL,
  `ip_address_registration` varchar(39) NOT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(65) NOT NULL,
  `phone` bigint DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `token` varchar(45) DEFAULT NULL,
  `username` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `model_users`
--

INSERT INTO `model_users` (`id`, `account_non_locked`, `date_birth`, `date_created_user`, `email`, `enabled`, `ip_address_first_entrance`, `ip_address_last_entrance`, `ip_address_reg_confirm`, `ip_address_registration`, `middle_name`, `name`, `password`, `phone`, `surname`, `token`, `username`) VALUES
(2, b'1', NULL, '2023-01-20 13:53:29.240647', 'ddd@ddd.dd', b'1', NULL, NULL, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', NULL, NULL, '$2a$10$oeC4dYFF9eFhnKeC7a0PBOo6BAFKUxCfSFgv8GOM1jGzyqRPTj4cm', NULL, NULL, NULL, NULL),
(3, b'1', NULL, '2023-01-20 13:55:19.296370', 'gdfgdf@sdfs.fds', b'1', NULL, NULL, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', NULL, NULL, '$2a$10$KhOqYcuOblpWk0GrFym2q.cqi5HCmQxX9k1qkFFmg3EBZT9GZ0szS', NULL, NULL, '84640db9-8920-47e7-aa30-e10e1e9d4ecd', NULL),
(4, b'1', NULL, '2023-01-20 14:50:04.839513', 'www@www.www', b'1', NULL, NULL, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', NULL, NULL, '$2a$10$L.oi8ZZmIVi1LmjQTyIdm.w032ZeUGuMJVd6YNT20Fdnnzn3Suf2e', NULL, NULL, NULL, NULL),
(5, b'1', NULL, '2023-01-20 17:41:19.134796', 'tutut@rerw.er', b'1', NULL, NULL, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', NULL, NULL, '$2a$10$yGWqcWSbMuggbcSk0Lsna.70LPgR/5g8FpiEXHrKvUBQReZ7OD3i6', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `not_activated_seller`
--

CREATE TABLE `not_activated_seller` (
  `id` bigint NOT NULL,
  `active` bit(1) NOT NULL,
  `date_deletion_seller` datetime(6) DEFAULT NULL,
  `seller_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `not_activated_seller`
--

INSERT INTO `not_activated_seller` (`id`, `active`, `date_deletion_seller`, `seller_id`) VALUES
(6, b'1', '2023-01-21 13:32:33.543011', 6);

-- --------------------------------------------------------

--
-- Структура таблицы `not_activated_user`
--

CREATE TABLE `not_activated_user` (
  `id` bigint NOT NULL,
  `active` bit(1) NOT NULL,
  `date_deletion_user` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `not_activated_user`
--

INSERT INTO `not_activated_user` (`id`, `active`, `date_deletion_user`, `user_id`) VALUES
(2, b'1', '2023-01-21 13:53:29.240647', 2),
(3, b'1', '2023-01-21 13:55:19.296370', 3),
(4, b'1', '2023-01-21 14:50:04.839513', 4),
(5, b'1', '2023-01-21 17:41:19.134796', 5);

-- --------------------------------------------------------

--
-- Структура таблицы `role_seller`
--

CREATE TABLE `role_seller` (
  `id` bigint NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `role_seller`
--

INSERT INTO `role_seller` (`id`, `name`) VALUES
(1, 'ROLE_SELLER');

-- --------------------------------------------------------

--
-- Структура таблицы `role_user`
--

CREATE TABLE `role_user` (
  `id` bigint NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `role_user`
--

INSERT INTO `role_user` (`id`, `name`) VALUES
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Структура таблицы `seq_a_product`
--

CREATE TABLE `seq_a_product` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_a_product`
--

INSERT INTO `seq_a_product` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_a_seller`
--

CREATE TABLE `seq_a_seller` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_a_seller`
--

INSERT INTO `seq_a_seller` (`next_val`) VALUES
(7);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_a_user`
--

CREATE TABLE `seq_a_user` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_a_user`
--

INSERT INTO `seq_a_user` (`next_val`) VALUES
(6);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_not_activated_seller`
--

CREATE TABLE `seq_not_activated_seller` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_not_activated_seller`
--

INSERT INTO `seq_not_activated_seller` (`next_val`) VALUES
(7);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_not_activated_user`
--

CREATE TABLE `seq_not_activated_user` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_not_activated_user`
--

INSERT INTO `seq_not_activated_user` (`next_val`) VALUES
(6);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_role_seller`
--

CREATE TABLE `seq_role_seller` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_role_seller`
--

INSERT INTO `seq_role_seller` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_role_user`
--

CREATE TABLE `seq_role_user` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_role_user`
--

INSERT INTO `seq_role_user` (`next_val`) VALUES
(1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `join_a_seller_and_role_seller`
--
ALTER TABLE `join_a_seller_and_role_seller`
  ADD PRIMARY KEY (`seller_id`,`role_seller_id`),
  ADD KEY `FKqq6n26yyyqek55x0s8em2hj1d` (`role_seller_id`);

--
-- Индексы таблицы `join_a_user_and_role_user`
--
ALTER TABLE `join_a_user_and_role_user`
  ADD PRIMARY KEY (`user_id`,`role_users_id`),
  ADD KEY `FKgqokruma1y09emkcc9klhgft7` (`role_users_id`);

--
-- Индексы таблицы `join_product_sellers`
--
ALTER TABLE `join_product_sellers`
  ADD PRIMARY KEY (`product_id`,`sellers_id`),
  ADD KEY `FK4w6tor59davida8m7jojbrbyr` (`sellers_id`);

--
-- Индексы таблицы `join_product_users`
--
ALTER TABLE `join_product_users`
  ADD PRIMARY KEY (`product_id`,`users_id`),
  ADD KEY `FKb6e1q4cvqmgyr309x2anll6y0` (`users_id`);

--
-- Индексы таблицы `model_product`
--
ALTER TABLE `model_product`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `model_seller`
--
ALTER TABLE `model_seller`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_hhgw75re2u7ngsskemu7l5ji0` (`email`,`inn`,`inn_bank`,`kpp_bank`,`number_passport`,`phone`,`shop_name`),
  ADD UNIQUE KEY `UK_o8vrfv5q5yc6vp4vriyc8opc9` (`bank_account`),
  ADD UNIQUE KEY `UK_qlppeevdbivqafwuledfin0dv` (`email`),
  ADD UNIQUE KEY `UK_j1gl3cqw2pepyaatla7ec1l0f` (`img_passport`),
  ADD UNIQUE KEY `UK_ibsmxlfvm99puhynn9vie9d2` (`inn`),
  ADD UNIQUE KEY `UK_9bx0genadbdgu60v2ol7xu9q8` (`number_passport`),
  ADD UNIQUE KEY `UK_ilvm8ipu68ymeomum1930j33s` (`phone`),
  ADD UNIQUE KEY `UK_mvvbi4o2chbfhusku688yy15m` (`shop_name`),
  ADD UNIQUE KEY `UK_g3ot86mxqwysmjyx2lyfwaabx` (`token`),
  ADD UNIQUE KEY `UK_ieei1vv1tns8onedf5i23g2br` (`username`);

--
-- Индексы таблицы `model_users`
--
ALTER TABLE `model_users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_7p8cjud14cxguvri01wu774t8` (`email`),
  ADD UNIQUE KEY `UK_o27e2i325jidgqgdbqfea1sn6` (`email`,`phone`),
  ADD UNIQUE KEY `UK_a6a63x887uymnvdjjsvvcebri` (`phone`),
  ADD UNIQUE KEY `UK_553y655dmf2due17p1o9ei3pq` (`token`),
  ADD UNIQUE KEY `UK_k112tgo0tkcx1krqb71uv3k24` (`username`);

--
-- Индексы таблицы `not_activated_seller`
--
ALTER TABLE `not_activated_seller`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmyic80p3tfamvv0fqd9a13dls` (`seller_id`);

--
-- Индексы таблицы `not_activated_user`
--
ALTER TABLE `not_activated_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKritv6ns7er9rvanshaaevgxa` (`user_id`);

--
-- Индексы таблицы `role_seller`
--
ALTER TABLE `role_seller`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_2des992ubikc1dg4hglt0bc6` (`name`);

--
-- Индексы таблицы `role_user`
--
ALTER TABLE `role_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_jiltx188xdo3iehcnqj8fxfgn` (`name`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `join_a_seller_and_role_seller`
--
ALTER TABLE `join_a_seller_and_role_seller`
  ADD CONSTRAINT `FKo0bau0txeug4orifty3ynwb3d` FOREIGN KEY (`seller_id`) REFERENCES `model_seller` (`id`),
  ADD CONSTRAINT `FKqq6n26yyyqek55x0s8em2hj1d` FOREIGN KEY (`role_seller_id`) REFERENCES `role_seller` (`id`);

--
-- Ограничения внешнего ключа таблицы `join_a_user_and_role_user`
--
ALTER TABLE `join_a_user_and_role_user`
  ADD CONSTRAINT `FKgqokruma1y09emkcc9klhgft7` FOREIGN KEY (`role_users_id`) REFERENCES `role_user` (`id`),
  ADD CONSTRAINT `FKny11orpqklrfvgcbktcwgul0n` FOREIGN KEY (`user_id`) REFERENCES `model_users` (`id`);

--
-- Ограничения внешнего ключа таблицы `join_product_sellers`
--
ALTER TABLE `join_product_sellers`
  ADD CONSTRAINT `FK4w6tor59davida8m7jojbrbyr` FOREIGN KEY (`sellers_id`) REFERENCES `model_seller` (`id`),
  ADD CONSTRAINT `FKtojq98n6ynodppvixc4w1ayek` FOREIGN KEY (`product_id`) REFERENCES `model_product` (`id`);

--
-- Ограничения внешнего ключа таблицы `join_product_users`
--
ALTER TABLE `join_product_users`
  ADD CONSTRAINT `FK17n1nljcfhk9ibjxtytdq674u` FOREIGN KEY (`product_id`) REFERENCES `model_product` (`id`),
  ADD CONSTRAINT `FKb6e1q4cvqmgyr309x2anll6y0` FOREIGN KEY (`users_id`) REFERENCES `model_users` (`id`);

--
-- Ограничения внешнего ключа таблицы `not_activated_seller`
--
ALTER TABLE `not_activated_seller`
  ADD CONSTRAINT `FKmyic80p3tfamvv0fqd9a13dls` FOREIGN KEY (`seller_id`) REFERENCES `model_seller` (`id`);

--
-- Ограничения внешнего ключа таблицы `not_activated_user`
--
ALTER TABLE `not_activated_user`
  ADD CONSTRAINT `FKritv6ns7er9rvanshaaevgxa` FOREIGN KEY (`user_id`) REFERENCES `model_users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
