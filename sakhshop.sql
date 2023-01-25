-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Янв 25 2023 г., 08:11
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
-- Структура таблицы `join_a_admin_and_role_admin`
--

CREATE TABLE `join_a_admin_and_role_admin` (
  `admin_id` bigint NOT NULL,
  `role_admin_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `join_a_admin_and_role_admin`
--

INSERT INTO `join_a_admin_and_role_admin` (`admin_id`, `role_admin_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `join_a_logistics_company_and_role_logistics_company`
--

CREATE TABLE `join_a_logistics_company_and_role_logistics_company` (
  `logistics_company_id` bigint NOT NULL,
  `role_logistics_company_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `join_a_logistics_person_and_role_logistics_person`
--

CREATE TABLE `join_a_logistics_person_and_role_logistics_person` (
  `logistics_person_id` bigint NOT NULL,
  `role_logistics_person_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `join_a_seller_and_role_seller`
--

CREATE TABLE `join_a_seller_and_role_seller` (
  `seller_id` bigint NOT NULL,
  `role_seller_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `join_a_user_and_role_user`
--

CREATE TABLE `join_a_user_and_role_user` (
  `user_id` bigint NOT NULL,
  `role_user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
-- Структура таблицы `model_admin`
--

CREATE TABLE `model_admin` (
  `id` bigint NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `email` varchar(58) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `password` varchar(65) NOT NULL,
  `phone` bigint DEFAULT NULL,
  `token` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `model_admin`
--

INSERT INTO `model_admin` (`id`, `account_non_locked`, `email`, `enabled`, `name`, `password`, `phone`, `token`) VALUES
(1, b'1', 'ddd@dd.dd', b'1', 'Геннадий', '$2a$10$Nq0fThbckgprYc5zc3Dx1.AlcqxvR/N1F/2ci2/MvOGZzeOfo.Lk6', 79242852535, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `model_logistics_company`
--

CREATE TABLE `model_logistics_company` (
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
  `company_name` varchar(30) NOT NULL,
  `correspondent_account` varchar(20) NOT NULL,
  `date_birth` date NOT NULL,
  `date_created_logistics_company` datetime(6) NOT NULL,
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
  `kpp` int NOT NULL,
  `kpp_bank` int NOT NULL,
  `middle_name` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `number_passport` bigint NOT NULL,
  `ogrn` bigint NOT NULL,
  `password` varchar(65) NOT NULL,
  `phone` bigint NOT NULL,
  `region` varchar(7) NOT NULL,
  `street` varchar(50) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `token` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `model_logistics_person`
--

CREATE TABLE `model_logistics_person` (
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
-- Структура таблицы `not_activated_logistics_company`
--

CREATE TABLE `not_activated_logistics_company` (
  `id` bigint NOT NULL,
  `active` bit(1) NOT NULL,
  `date_deletion_logistics_company` datetime(6) DEFAULT NULL,
  `logistics_company_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
-- Структура таблицы `role_admin`
--

CREATE TABLE `role_admin` (
  `id` bigint NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `role_admin`
--

INSERT INTO `role_admin` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Структура таблицы `role_logistics_company`
--

CREATE TABLE `role_logistics_company` (
  `id` bigint NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `role_logistics_company`
--

INSERT INTO `role_logistics_company` (`id`, `name`) VALUES
(1, 'ROLE_LOGISTICS_COMPANY');

-- --------------------------------------------------------

--
-- Структура таблицы `role_logistics_person`
--

CREATE TABLE `role_logistics_person` (
  `id` bigint NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `role_logistics_person`
--

INSERT INTO `role_logistics_person` (`id`, `name`) VALUES
(1, 'ROLE_LOGISTICS_PERSON');

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
-- Структура таблицы `seq_a_admin`
--

CREATE TABLE `seq_a_admin` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_a_admin`
--

INSERT INTO `seq_a_admin` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_a_logistics_company`
--

CREATE TABLE `seq_a_logistics_company` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_a_logistics_company`
--

INSERT INTO `seq_a_logistics_company` (`next_val`) VALUES
(13);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_a_logistics_person`
--

CREATE TABLE `seq_a_logistics_person` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_a_logistics_person`
--

INSERT INTO `seq_a_logistics_person` (`next_val`) VALUES
(1);

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
(4);

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
(3);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_not_activated_logistics_company`
--

CREATE TABLE `seq_not_activated_logistics_company` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_not_activated_logistics_company`
--

INSERT INTO `seq_not_activated_logistics_company` (`next_val`) VALUES
(13);

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
(4);

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
(3);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_role_admin`
--

CREATE TABLE `seq_role_admin` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_role_admin`
--

INSERT INTO `seq_role_admin` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_role_logistics_company`
--

CREATE TABLE `seq_role_logistics_company` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_role_logistics_company`
--

INSERT INTO `seq_role_logistics_company` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_role_logistics_person`
--

CREATE TABLE `seq_role_logistics_person` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `seq_role_logistics_person`
--

INSERT INTO `seq_role_logistics_person` (`next_val`) VALUES
(1);

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
-- Индексы таблицы `join_a_admin_and_role_admin`
--
ALTER TABLE `join_a_admin_and_role_admin`
  ADD PRIMARY KEY (`admin_id`,`role_admin_id`),
  ADD KEY `FKet0lqfx6kkl2jv0e8eci5pxvc` (`role_admin_id`);

--
-- Индексы таблицы `join_a_logistics_company_and_role_logistics_company`
--
ALTER TABLE `join_a_logistics_company_and_role_logistics_company`
  ADD PRIMARY KEY (`logistics_company_id`,`role_logistics_company_id`),
  ADD KEY `FKhuss54o7jg9yxc5jac46a5mj7` (`role_logistics_company_id`);

--
-- Индексы таблицы `join_a_logistics_person_and_role_logistics_person`
--
ALTER TABLE `join_a_logistics_person_and_role_logistics_person`
  ADD PRIMARY KEY (`logistics_person_id`,`role_logistics_person_id`),
  ADD KEY `FKdpf2fcwrifdg8lmbeik040y13` (`role_logistics_person_id`);

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
  ADD PRIMARY KEY (`user_id`,`role_user_id`),
  ADD KEY `FK6ij71oulwk0p1m33thf44d11c` (`role_user_id`);

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
-- Индексы таблицы `model_admin`
--
ALTER TABLE `model_admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_9hn5ivkklwgix4bh00pcdgk4b` (`email`),
  ADD UNIQUE KEY `UK_6w9vauy50gqirr4pvee69iw29` (`email`,`phone`),
  ADD UNIQUE KEY `UK_nh9cvnuv8o4ddmfnxvc5vocdp` (`name`),
  ADD UNIQUE KEY `UK_lvl2v1degmvosnxu2qf6vxidj` (`phone`),
  ADD UNIQUE KEY `UK_9kvi70khfdn1bw4hqa0eot90i` (`token`);

--
-- Индексы таблицы `model_logistics_company`
--
ALTER TABLE `model_logistics_company`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_1h79v3a60c96yhoof5m1991fo` (`email`,`inn`,`inn_bank`,`kpp`,`kpp_bank`,`number_passport`,`ogrn`,`phone`),
  ADD UNIQUE KEY `UK_4oln28brwhhmngejwaf9qtrom` (`bank_account`),
  ADD UNIQUE KEY `UK_2o9mm02n9vbaqr8aqxoc1uncc` (`email`),
  ADD UNIQUE KEY `UK_k37gs7n5io1glh9x98ihvc2p8` (`img_passport`),
  ADD UNIQUE KEY `UK_eam98wxylbc2r26nb8do1h1pg` (`inn`),
  ADD UNIQUE KEY `UK_g3tm6kinm3ve1d92frtyuu2bs` (`kpp`),
  ADD UNIQUE KEY `UK_9970bss1gq8cn7l5pled08qj2` (`number_passport`),
  ADD UNIQUE KEY `UK_o6j988398533fuomrypuhu22y` (`ogrn`),
  ADD UNIQUE KEY `UK_ks96epibiru7qfbtyjt1vfamv` (`phone`),
  ADD UNIQUE KEY `UK_k4u34ogywoyttndwrv50gf6wn` (`token`);

--
-- Индексы таблицы `model_logistics_person`
--
ALTER TABLE `model_logistics_person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_kburri74cl7qsnuravbfr34ha` (`email`,`inn_bank`,`kpp_bank`,`number_passport`,`phone`,`shop_name`),
  ADD UNIQUE KEY `UK_ld4ek8ut1l0hfxcr5bvylrauh` (`bank_account`),
  ADD UNIQUE KEY `UK_kk1oswxspomtajy5cal1hwmml` (`email`),
  ADD UNIQUE KEY `UK_5ux2ovy5ff5pc7dnyptynles8` (`img_passport`),
  ADD UNIQUE KEY `UK_h4d36i1md6tmrfs0syx0ms0qa` (`number_passport`),
  ADD UNIQUE KEY `UK_2o7npg39fbdw2iir97skylf39` (`phone`),
  ADD UNIQUE KEY `UK_sbnu8e494h8ax3j4icpwqbrh1` (`shop_name`),
  ADD UNIQUE KEY `UK_ipfus2rgqxpqliyfq7ndgcag0` (`token`),
  ADD UNIQUE KEY `UK_58r5yqbdhjx9c3o7lb93e2ua6` (`username`);

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
-- Индексы таблицы `not_activated_logistics_company`
--
ALTER TABLE `not_activated_logistics_company`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK38qy7ciqvmbaqgp8jehihcitb` (`logistics_company_id`);

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
-- Индексы таблицы `role_admin`
--
ALTER TABLE `role_admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ly8tds7h2m1bfstn7yb5592gs` (`name`);

--
-- Индексы таблицы `role_logistics_company`
--
ALTER TABLE `role_logistics_company`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ok21anwt6j8e13i4ant7df2rj` (`name`);

--
-- Индексы таблицы `role_logistics_person`
--
ALTER TABLE `role_logistics_person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_7tixeefksr67cbb2w18j3a0m` (`name`);

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
-- Ограничения внешнего ключа таблицы `join_a_admin_and_role_admin`
--
ALTER TABLE `join_a_admin_and_role_admin`
  ADD CONSTRAINT `FKet0lqfx6kkl2jv0e8eci5pxvc` FOREIGN KEY (`role_admin_id`) REFERENCES `role_admin` (`id`),
  ADD CONSTRAINT `FKpr7sd7bvusj4y5ioc1m1e2wvu` FOREIGN KEY (`admin_id`) REFERENCES `model_admin` (`id`);

--
-- Ограничения внешнего ключа таблицы `join_a_logistics_company_and_role_logistics_company`
--
ALTER TABLE `join_a_logistics_company_and_role_logistics_company`
  ADD CONSTRAINT `FK8sar505uit38g1ec4g939186y` FOREIGN KEY (`logistics_company_id`) REFERENCES `model_logistics_company` (`id`),
  ADD CONSTRAINT `FKhuss54o7jg9yxc5jac46a5mj7` FOREIGN KEY (`role_logistics_company_id`) REFERENCES `role_logistics_company` (`id`);

--
-- Ограничения внешнего ключа таблицы `join_a_logistics_person_and_role_logistics_person`
--
ALTER TABLE `join_a_logistics_person_and_role_logistics_person`
  ADD CONSTRAINT `FKdpf2fcwrifdg8lmbeik040y13` FOREIGN KEY (`role_logistics_person_id`) REFERENCES `role_logistics_person` (`id`),
  ADD CONSTRAINT `FKq41k72uvbr5vn45g9wtdgyum8` FOREIGN KEY (`logistics_person_id`) REFERENCES `model_logistics_person` (`id`);

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
  ADD CONSTRAINT `FK6ij71oulwk0p1m33thf44d11c` FOREIGN KEY (`role_user_id`) REFERENCES `role_user` (`id`),
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
-- Ограничения внешнего ключа таблицы `not_activated_logistics_company`
--
ALTER TABLE `not_activated_logistics_company`
  ADD CONSTRAINT `FK38qy7ciqvmbaqgp8jehihcitb` FOREIGN KEY (`logistics_company_id`) REFERENCES `model_logistics_company` (`id`);

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
