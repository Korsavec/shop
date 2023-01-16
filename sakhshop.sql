-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Янв 16 2023 г., 16:13
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
-- Структура таблицы `join_a_seller_individual_and_role_seller_individual`
--

CREATE TABLE `join_a_seller_individual_and_role_seller_individual` (
  `seller_individual_id` bigint NOT NULL,
  `role_seller_individuals_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `join_a_seller_limited_and_role_seller_limited`
--

CREATE TABLE `join_a_seller_limited_and_role_seller_limited` (
  `seller_limited_id` bigint NOT NULL,
  `role_users_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `join_a_seller_person_and_role_seller_person`
--

CREATE TABLE `join_a_seller_person_and_role_seller_person` (
  `seller_person_id` bigint NOT NULL,
  `role_seller_person_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `join_a_user_and_role_user`
--

CREATE TABLE `join_a_user_and_role_user` (
  `user_id` bigint NOT NULL,
  `role_users_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `join_product_seller_individuals`
--

CREATE TABLE `join_product_seller_individuals` (
  `product_id` bigint NOT NULL,
  `individuals_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `join_product_seller_limiteds`
--

CREATE TABLE `join_product_seller_limiteds` (
  `product_id` bigint NOT NULL,
  `seller_limiteds_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `join_product_seller_persons`
--

CREATE TABLE `join_product_seller_persons` (
  `product_id` bigint NOT NULL,
  `seller_persons_id` bigint NOT NULL
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
-- Структура таблицы `model_seller_individual`
--

CREATE TABLE `model_seller_individual` (
  `id` bigint NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `apartment` int DEFAULT NULL,
  `balance` decimal(12,2) DEFAULT '0.00',
  `bank_account` varchar(20) NOT NULL,
  `bank_name` varchar(50) NOT NULL,
  `beak_bank` varchar(9) NOT NULL,
  `building` varchar(4) DEFAULT NULL,
  `city` varchar(25) NOT NULL,
  `correspondent_account` varchar(20) NOT NULL,
  `date_birth` date NOT NULL,
  `date_created_seller_individual` datetime(6) NOT NULL,
  `email` varchar(58) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `house` varchar(4) NOT NULL,
  `inn` int NOT NULL,
  `ip_address_first_entrance` varchar(39) DEFAULT NULL,
  `ip_address_last_entrance` varchar(39) DEFAULT NULL,
  `ip_address_reg_confirm` varchar(39) DEFAULT NULL,
  `ip_address_registration` varchar(39) NOT NULL,
  `middle_name` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `name_shop` varchar(30) NOT NULL,
  `number_passport` int NOT NULL,
  `ogrnip` int DEFAULT NULL,
  `okved` varchar(8) DEFAULT NULL,
  `password` varchar(65) NOT NULL,
  `phone` bigint NOT NULL,
  `region` varchar(7) NOT NULL,
  `street` varchar(50) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `token` varchar(45) DEFAULT NULL,
  `url_img_passport` varchar(50) DEFAULT NULL,
  `username` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `model_seller_limited`
--

CREATE TABLE `model_seller_limited` (
  `id` bigint NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `apartment` int DEFAULT NULL,
  `balance` decimal(12,2) DEFAULT '0.00',
  `bank_account` varchar(20) NOT NULL,
  `bank_name` varchar(50) NOT NULL,
  `beak_bank` varchar(9) NOT NULL,
  `building` varchar(4) DEFAULT NULL,
  `city` varchar(25) NOT NULL,
  `company_name` varchar(7) NOT NULL,
  `correspondent_account` varchar(20) NOT NULL,
  `date_birth` date NOT NULL,
  `date_created_seller_limited` datetime(6) NOT NULL,
  `email` varchar(58) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `house` varchar(4) NOT NULL,
  `inn` int NOT NULL,
  `ip_address_first_entrance` varchar(39) DEFAULT NULL,
  `ip_address_last_entrance` varchar(39) DEFAULT NULL,
  `ip_address_reg_confirm` varchar(39) DEFAULT NULL,
  `ip_address_registration` varchar(39) NOT NULL,
  `kpp` int NOT NULL,
  `middle_name` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `name_shop` varchar(30) NOT NULL,
  `number_passport` int NOT NULL,
  `ogrn` int NOT NULL,
  `okved` varchar(8) DEFAULT NULL,
  `password` varchar(65) NOT NULL,
  `phone` bigint NOT NULL,
  `region` varchar(7) NOT NULL,
  `street` varchar(50) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `token` varchar(45) DEFAULT NULL,
  `url_img_passport` varchar(50) DEFAULT NULL,
  `username` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `model_seller_person`
--

CREATE TABLE `model_seller_person` (
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
  `date_created_seller_person` datetime(6) NOT NULL,
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

-- --------------------------------------------------------

--
-- Структура таблицы `not_activated_seller_individual`
--

CREATE TABLE `not_activated_seller_individual` (
  `id` bigint NOT NULL,
  `active` bit(1) NOT NULL,
  `date_deletion_seller_individual` datetime(6) DEFAULT NULL,
  `seller_individual_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `not_activated_seller_limited`
--

CREATE TABLE `not_activated_seller_limited` (
  `id` bigint NOT NULL,
  `active` bit(1) NOT NULL,
  `date_deletion_seller_limited` datetime(6) DEFAULT NULL,
  `seller_limited_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `not_activated_seller_person`
--

CREATE TABLE `not_activated_seller_person` (
  `id` bigint NOT NULL,
  `active` bit(1) NOT NULL,
  `date_deletion_seller_person` datetime(6) DEFAULT NULL,
  `seller_person_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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

-- --------------------------------------------------------

--
-- Структура таблицы `role_seller_individual`
--

CREATE TABLE `role_seller_individual` (
  `id` bigint NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `role_seller_individual`
--

INSERT INTO `role_seller_individual` (`id`, `name`) VALUES
(1, 'ROLE_SELLER_INDIVIDUAL');

-- --------------------------------------------------------

--
-- Структура таблицы `role_seller_limited`
--

CREATE TABLE `role_seller_limited` (
  `id` bigint NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `role_seller_limited`
--

INSERT INTO `role_seller_limited` (`id`, `name`) VALUES
(1, 'ROLE_SELLER_LIMITED');

-- --------------------------------------------------------

--
-- Структура таблицы `role_seller_person`
--

CREATE TABLE `role_seller_person` (
  `id` bigint NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `role_seller_person`
--

INSERT INTO `role_seller_person` (`id`, `name`) VALUES
(1, 'ROLE_SELLER_PERSON');

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
-- Структура таблицы `seq_a_seller_individual`
--

CREATE TABLE `seq_a_seller_individual` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_a_seller_individual`
--

INSERT INTO `seq_a_seller_individual` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_a_seller_limited`
--

CREATE TABLE `seq_a_seller_limited` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_a_seller_limited`
--

INSERT INTO `seq_a_seller_limited` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_a_seller_person`
--

CREATE TABLE `seq_a_seller_person` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_a_seller_person`
--

INSERT INTO `seq_a_seller_person` (`next_val`) VALUES
(15);

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
(36);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_not_activated_seller_individual`
--

CREATE TABLE `seq_not_activated_seller_individual` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_not_activated_seller_individual`
--

INSERT INTO `seq_not_activated_seller_individual` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_not_activated_seller_limited`
--

CREATE TABLE `seq_not_activated_seller_limited` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_not_activated_seller_limited`
--

INSERT INTO `seq_not_activated_seller_limited` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_not_activated_seller_person`
--

CREATE TABLE `seq_not_activated_seller_person` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_not_activated_seller_person`
--

INSERT INTO `seq_not_activated_seller_person` (`next_val`) VALUES
(15);

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
(31);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_role_seller_individual`
--

CREATE TABLE `seq_role_seller_individual` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_role_seller_individual`
--

INSERT INTO `seq_role_seller_individual` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_role_seller_limited`
--

CREATE TABLE `seq_role_seller_limited` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_role_seller_limited`
--

INSERT INTO `seq_role_seller_limited` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_role_seller_person`
--

CREATE TABLE `seq_role_seller_person` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_role_seller_person`
--

INSERT INTO `seq_role_seller_person` (`next_val`) VALUES
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
-- Индексы таблицы `join_a_seller_individual_and_role_seller_individual`
--
ALTER TABLE `join_a_seller_individual_and_role_seller_individual`
  ADD PRIMARY KEY (`seller_individual_id`,`role_seller_individuals_id`),
  ADD KEY `FKtoqrbuengx0yg73hk1x6eobuy` (`role_seller_individuals_id`);

--
-- Индексы таблицы `join_a_seller_limited_and_role_seller_limited`
--
ALTER TABLE `join_a_seller_limited_and_role_seller_limited`
  ADD PRIMARY KEY (`seller_limited_id`,`role_users_id`),
  ADD KEY `FK2d730cj3pmn45j4jn9iud6rgv` (`role_users_id`);

--
-- Индексы таблицы `join_a_seller_person_and_role_seller_person`
--
ALTER TABLE `join_a_seller_person_and_role_seller_person`
  ADD PRIMARY KEY (`seller_person_id`,`role_seller_person_id`),
  ADD KEY `FKmxe0qyuw4uuefg249bnavlc1y` (`role_seller_person_id`);

--
-- Индексы таблицы `join_a_user_and_role_user`
--
ALTER TABLE `join_a_user_and_role_user`
  ADD PRIMARY KEY (`user_id`,`role_users_id`),
  ADD KEY `FKgqokruma1y09emkcc9klhgft7` (`role_users_id`);

--
-- Индексы таблицы `join_product_seller_individuals`
--
ALTER TABLE `join_product_seller_individuals`
  ADD PRIMARY KEY (`product_id`,`individuals_id`),
  ADD KEY `FKl9gu2xoaro971ge9m210kw5h7` (`individuals_id`);

--
-- Индексы таблицы `join_product_seller_limiteds`
--
ALTER TABLE `join_product_seller_limiteds`
  ADD PRIMARY KEY (`product_id`,`seller_limiteds_id`),
  ADD KEY `FKqqtrhtkps8jd98y8dxway1bmg` (`seller_limiteds_id`);

--
-- Индексы таблицы `join_product_seller_persons`
--
ALTER TABLE `join_product_seller_persons`
  ADD PRIMARY KEY (`product_id`,`seller_persons_id`),
  ADD KEY `FK6d9uqgvpaxhvovulwdw4owmrr` (`seller_persons_id`);

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
-- Индексы таблицы `model_seller_individual`
--
ALTER TABLE `model_seller_individual`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_eqlf3kvlejv39jn77amexmfn0` (`email`),
  ADD UNIQUE KEY `UK_8cs8tc6f44936ifb0lj8iljo7` (`inn`),
  ADD UNIQUE KEY `UK_i63nsvf82fyl1k41f0c6n9y9t` (`name_shop`),
  ADD UNIQUE KEY `UK_2rx5ntkul8em8nh090fnxv0pk` (`number_passport`),
  ADD UNIQUE KEY `UK_la248s6fr2dor955rrkrbihni` (`phone`),
  ADD UNIQUE KEY `UK_erwnb6myf79r7q4tawumssotb` (`email`,`inn`,`number_passport`,`ogrnip`,`phone`),
  ADD UNIQUE KEY `UK_cc7cxawsf17g8p1qxwogoap2q` (`ogrnip`),
  ADD UNIQUE KEY `UK_72xst7f6a1ksjxivx7pdnxkfw` (`okved`),
  ADD UNIQUE KEY `UK_6fooa097vm52vkx7algg37kuc` (`token`),
  ADD UNIQUE KEY `UK_tpms5ogn2g0fagwmy00atdwe0` (`username`);

--
-- Индексы таблицы `model_seller_limited`
--
ALTER TABLE `model_seller_limited`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_osgjnbgg0lcp5x8p2niaj2a5v` (`email`,`inn`,`kpp`,`number_passport`,`ogrn`,`phone`),
  ADD UNIQUE KEY `UK_2yf6t1icnpt7beu231jrmyv0w` (`email`),
  ADD UNIQUE KEY `UK_aacf648w7kqgd1a2oeyjua6fd` (`inn`),
  ADD UNIQUE KEY `UK_cxj6t1bef47hp2h0ty4vutamd` (`kpp`),
  ADD UNIQUE KEY `UK_3cidfi6jvlv9isbla8i4vsmq9` (`name_shop`),
  ADD UNIQUE KEY `UK_csuarlbu8mjsp1u919fdbxgur` (`number_passport`),
  ADD UNIQUE KEY `UK_oaha1m3a2utkuav5bjnoffjg7` (`ogrn`),
  ADD UNIQUE KEY `UK_f7gepcp9y7p37du8fq3kqnih4` (`phone`),
  ADD UNIQUE KEY `UK_4vvyyfuejbinda4mdq85o7e0c` (`okved`),
  ADD UNIQUE KEY `UK_f8jefw6tjxew1p6y6ceim0n0m` (`token`),
  ADD UNIQUE KEY `UK_74u66b589x1f0ood33hnaoefo` (`username`);

--
-- Индексы таблицы `model_seller_person`
--
ALTER TABLE `model_seller_person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ohcmmi0t05yj7jitax3kw5bof` (`email`,`inn`,`inn_bank`,`kpp_bank`,`number_passport`,`phone`,`shop_name`),
  ADD UNIQUE KEY `UK_rfysbawal66pehvpusnbeibe6` (`bank_account`),
  ADD UNIQUE KEY `UK_mphqa2bqusjjltmhh75qo7hw9` (`email`),
  ADD UNIQUE KEY `UK_13otukuiu9sj8xkgje6wlbd3b` (`img_passport`),
  ADD UNIQUE KEY `UK_8x6x01a20a4lhlrbrmqs8vrxx` (`inn`),
  ADD UNIQUE KEY `UK_q5sxt2vjbcnncb7cymm25s2wb` (`number_passport`),
  ADD UNIQUE KEY `UK_4xyc983mol3gm6y5d15own5ls` (`phone`),
  ADD UNIQUE KEY `UK_4issv1ycq44vii77edrfqnici` (`shop_name`),
  ADD UNIQUE KEY `UK_r62ou52kucn9ah36435hpsisk` (`token`),
  ADD UNIQUE KEY `UK_moiwm0quc8si51kx85g45agd9` (`username`);

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
-- Индексы таблицы `not_activated_seller_individual`
--
ALTER TABLE `not_activated_seller_individual`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf0fqg8xm260vhbq2u60s40rxw` (`seller_individual_id`);

--
-- Индексы таблицы `not_activated_seller_limited`
--
ALTER TABLE `not_activated_seller_limited`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq0c4hgcuye7owcdy27fgcfvw4` (`seller_limited_id`);

--
-- Индексы таблицы `not_activated_seller_person`
--
ALTER TABLE `not_activated_seller_person`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6fmgj81sjaglhty0u849vlt1f` (`seller_person_id`);

--
-- Индексы таблицы `not_activated_user`
--
ALTER TABLE `not_activated_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKritv6ns7er9rvanshaaevgxa` (`user_id`);

--
-- Индексы таблицы `role_seller_individual`
--
ALTER TABLE `role_seller_individual`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_o7gqppwpsrgfnlurrdft1v6ae` (`name`);

--
-- Индексы таблицы `role_seller_limited`
--
ALTER TABLE `role_seller_limited`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_f6eudh12x40bv9oykvo7u8xvg` (`name`);

--
-- Индексы таблицы `role_seller_person`
--
ALTER TABLE `role_seller_person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_m58d18qa73jfkrpuakr5afj9n` (`name`);

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
-- Ограничения внешнего ключа таблицы `join_a_seller_individual_and_role_seller_individual`
--
ALTER TABLE `join_a_seller_individual_and_role_seller_individual`
  ADD CONSTRAINT `FK5nwlpq0648tsygmy8covwe1oy` FOREIGN KEY (`seller_individual_id`) REFERENCES `model_seller_individual` (`id`),
  ADD CONSTRAINT `FKtoqrbuengx0yg73hk1x6eobuy` FOREIGN KEY (`role_seller_individuals_id`) REFERENCES `role_seller_individual` (`id`);

--
-- Ограничения внешнего ключа таблицы `join_a_seller_limited_and_role_seller_limited`
--
ALTER TABLE `join_a_seller_limited_and_role_seller_limited`
  ADD CONSTRAINT `FK2d730cj3pmn45j4jn9iud6rgv` FOREIGN KEY (`role_users_id`) REFERENCES `role_seller_limited` (`id`),
  ADD CONSTRAINT `FK3h4brh5pdb040bu9fdfcpetvu` FOREIGN KEY (`seller_limited_id`) REFERENCES `model_seller_limited` (`id`);

--
-- Ограничения внешнего ключа таблицы `join_a_seller_person_and_role_seller_person`
--
ALTER TABLE `join_a_seller_person_and_role_seller_person`
  ADD CONSTRAINT `FK5tj5060hkqmvovvdw001h8bpy` FOREIGN KEY (`seller_person_id`) REFERENCES `model_seller_person` (`id`),
  ADD CONSTRAINT `FKmxe0qyuw4uuefg249bnavlc1y` FOREIGN KEY (`role_seller_person_id`) REFERENCES `role_seller_person` (`id`);

--
-- Ограничения внешнего ключа таблицы `join_a_user_and_role_user`
--
ALTER TABLE `join_a_user_and_role_user`
  ADD CONSTRAINT `FKgqokruma1y09emkcc9klhgft7` FOREIGN KEY (`role_users_id`) REFERENCES `role_user` (`id`),
  ADD CONSTRAINT `FKny11orpqklrfvgcbktcwgul0n` FOREIGN KEY (`user_id`) REFERENCES `model_users` (`id`);

--
-- Ограничения внешнего ключа таблицы `join_product_seller_individuals`
--
ALTER TABLE `join_product_seller_individuals`
  ADD CONSTRAINT `FKihpfx8nmgjkx9xijesrya374e` FOREIGN KEY (`product_id`) REFERENCES `model_product` (`id`),
  ADD CONSTRAINT `FKl9gu2xoaro971ge9m210kw5h7` FOREIGN KEY (`individuals_id`) REFERENCES `model_seller_individual` (`id`);

--
-- Ограничения внешнего ключа таблицы `join_product_seller_limiteds`
--
ALTER TABLE `join_product_seller_limiteds`
  ADD CONSTRAINT `FKarflu69oh49u1ykjieeavo9ku` FOREIGN KEY (`product_id`) REFERENCES `model_product` (`id`),
  ADD CONSTRAINT `FKqqtrhtkps8jd98y8dxway1bmg` FOREIGN KEY (`seller_limiteds_id`) REFERENCES `model_seller_limited` (`id`);

--
-- Ограничения внешнего ключа таблицы `join_product_seller_persons`
--
ALTER TABLE `join_product_seller_persons`
  ADD CONSTRAINT `FK6d9uqgvpaxhvovulwdw4owmrr` FOREIGN KEY (`seller_persons_id`) REFERENCES `model_seller_person` (`id`),
  ADD CONSTRAINT `FKqti6tit685pk6if43ckou22ed` FOREIGN KEY (`product_id`) REFERENCES `model_product` (`id`);

--
-- Ограничения внешнего ключа таблицы `join_product_users`
--
ALTER TABLE `join_product_users`
  ADD CONSTRAINT `FK17n1nljcfhk9ibjxtytdq674u` FOREIGN KEY (`product_id`) REFERENCES `model_product` (`id`),
  ADD CONSTRAINT `FKb6e1q4cvqmgyr309x2anll6y0` FOREIGN KEY (`users_id`) REFERENCES `model_users` (`id`);

--
-- Ограничения внешнего ключа таблицы `not_activated_seller_individual`
--
ALTER TABLE `not_activated_seller_individual`
  ADD CONSTRAINT `FKf0fqg8xm260vhbq2u60s40rxw` FOREIGN KEY (`seller_individual_id`) REFERENCES `model_seller_individual` (`id`);

--
-- Ограничения внешнего ключа таблицы `not_activated_seller_limited`
--
ALTER TABLE `not_activated_seller_limited`
  ADD CONSTRAINT `FKq0c4hgcuye7owcdy27fgcfvw4` FOREIGN KEY (`seller_limited_id`) REFERENCES `model_seller_limited` (`id`);

--
-- Ограничения внешнего ключа таблицы `not_activated_seller_person`
--
ALTER TABLE `not_activated_seller_person`
  ADD CONSTRAINT `FK6fmgj81sjaglhty0u849vlt1f` FOREIGN KEY (`seller_person_id`) REFERENCES `model_seller_person` (`id`);

--
-- Ограничения внешнего ключа таблицы `not_activated_user`
--
ALTER TABLE `not_activated_user`
  ADD CONSTRAINT `FKritv6ns7er9rvanshaaevgxa` FOREIGN KEY (`user_id`) REFERENCES `model_users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
