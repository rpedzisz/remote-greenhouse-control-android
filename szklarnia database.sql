-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 30 Sty 2020, 17:48
-- Wersja serwera: 10.1.30-MariaDB
-- Wersja PHP: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `szklarnia`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `auto`
--

CREATE TABLE `auto` (
  `temperatura` int(11) NOT NULL,
  `wilgotnosc` int(11) NOT NULL,
  `jasnosc` int(11) NOT NULL,
  `tryb_naswietlania` int(11) NOT NULL,
  `czujnik_zmierzchu` int(11) NOT NULL,
  `godzina_naswietlania_od` int(11) NOT NULL,
  `godzina_naswietlania_do` int(11) NOT NULL,
  `wilgotnosc_gleby` int(11) NOT NULL,
  `godzina_podlewania_od` int(11) NOT NULL,
  `godzina_podlewania_do` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `auto`
--

INSERT INTO `auto` (`temperatura`, `wilgotnosc`, `jasnosc`, `tryb_naswietlania`, `czujnik_zmierzchu`, `godzina_naswietlania_od`, `godzina_naswietlania_do`, `wilgotnosc_gleby`, `godzina_podlewania_od`, `godzina_podlewania_do`) VALUES
(25, 50, 2000, 0, 5, 8, 20, 50, 11, 12);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `biezace_parametry`
--

CREATE TABLE `biezace_parametry` (
  `a_temp` float NOT NULL,
  `b_temp` float NOT NULL,
  `c_temp` float NOT NULL,
  `d_temp` float NOT NULL,
  `d_wilg` int(11) NOT NULL,
  `e_wilg_gleba` int(11) NOT NULL,
  `f_swiatlo` int(11) NOT NULL,
  `g_swiatlo` int(11) NOT NULL,
  `went` int(11) DEFAULT NULL,
  `serwo` int(11) DEFAULT NULL,
  `zarowka1` int(11) DEFAULT NULL,
  `zarowka2` int(11) DEFAULT NULL,
  `zarowka3` int(11) DEFAULT NULL,
  `zarowka4` int(11) DEFAULT NULL,
  `pompa` int(11) DEFAULT NULL,
  `grzalka` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `biezace_parametry`
--

INSERT INTO `biezace_parametry` (`a_temp`, `b_temp`, `c_temp`, `d_temp`, `d_wilg`, `e_wilg_gleba`, `f_swiatlo`, `g_swiatlo`, `went`, `serwo`, `zarowka1`, `zarowka2`, `zarowka3`, `zarowka4`, `pompa`, `grzalka`) VALUES
(45, 26, 21, 23, 48, 79, 0, 30, 0, 0, 0, 0, 0, 0, 0, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `historia_pomiarow`
--

CREATE TABLE `historia_pomiarow` (
  `id` int(11) NOT NULL,
  `a_temp` float NOT NULL,
  `b_temp` float NOT NULL,
  `c_temp` float NOT NULL,
  `d_temp` float NOT NULL,
  `d_wilg` int(11) NOT NULL,
  `e_gleba_wilg` int(11) NOT NULL,
  `f_swiatlo` int(11) NOT NULL,
  `g_swiatlo` int(11) NOT NULL,
  `data_pomiaru` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `historia_pomiarow`
--

INSERT INTO `historia_pomiarow` (`id`, `a_temp`, `b_temp`, `c_temp`, `d_temp`, `d_wilg`, `e_gleba_wilg`, `f_swiatlo`, `g_swiatlo`, `data_pomiaru`) VALUES
(4, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:29:37'),
(5, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:29:39'),
(6, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:29:41'),
(7, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:29:43'),
(8, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:29:45'),
(9, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:29:47'),
(10, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:29:49'),
(11, 23.5, 0, 24, 46, 0, 40, 11, 0, '2018-10-18 22:29:51'),
(12, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:29:53'),
(13, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:29:55'),
(14, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:29:57'),
(15, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:29:59'),
(16, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:01'),
(17, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:03'),
(18, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:05'),
(19, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:07'),
(20, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:09'),
(21, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:11'),
(22, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:13'),
(23, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:15'),
(24, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:17'),
(25, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:19'),
(26, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:21'),
(27, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:23'),
(28, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:25'),
(29, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:27'),
(30, 23, 0, 24, 47, 0, 39, 11, 0, '2018-10-18 22:30:29'),
(31, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:31'),
(32, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:33'),
(33, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:35'),
(34, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:37'),
(35, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:39'),
(36, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:41'),
(37, 23.5, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:43'),
(38, 23, 0, 24, 47, 0, 40, 11, 0, '2018-10-18 22:30:45'),
(39, 23.5, 0, 24, 50, 0, 40, 11, 0, '2018-10-18 22:40:47'),
(40, 23.5, 0, 24, 49, 0, 40, 11, 0, '2018-10-18 22:40:49'),
(41, 23.5, 0, 24, 50, 0, 40, 11, 0, '2018-10-18 22:40:51'),
(42, 23.5, 0, 24, 50, 0, 40, 11, 0, '2018-10-18 22:40:53'),
(43, 23, 0, 24, 50, 0, 40, 11, 0, '2018-10-18 22:40:55'),
(44, 23.5, 0, 24, 50, 0, 40, 11, 0, '2018-10-18 22:40:57'),
(45, 23.5, 0, 24, 49, 0, 40, 11, 0, '2018-10-18 22:41:15'),
(46, 23.5, 0, 24, 49, 0, 40, 11, 0, '2018-10-18 22:41:17'),
(47, 23, 0, 24, 49, 0, 40, 11, 0, '2018-10-18 22:41:19'),
(48, 23.5, 0, 24, 49, 0, 40, 11, 0, '2018-10-18 22:41:21'),
(49, 23.5, 0, 24, 49, 0, 40, 11, 0, '2018-10-18 22:41:23'),
(50, 23, 0, 24, 49, 0, 40, 11, 0, '2018-10-18 22:41:25'),
(51, 23, 0, 24, 49, 0, 40, 11, 0, '2018-10-18 22:41:27'),
(52, 23, 0, 24, 49, 0, 40, 11, 0, '2018-10-18 22:41:29'),
(53, 23.5, 0, 24, 49, 0, 40, 11, 0, '2018-10-18 22:41:31'),
(54, 23, 0, 24, 49, 0, 40, 11, 0, '2018-10-18 22:41:33'),
(55, 23.5, 0, 24, 49, 0, 40, 11, 0, '2018-10-18 22:41:35'),
(56, 23.5, 0, 24, 49, 0, 40, 11, 0, '2018-10-18 22:41:37'),
(57, 23.5, 0, 24, 50, 0, 40, 11, 0, '2018-10-18 22:41:39'),
(58, 23, 0, 24, 50, 0, 40, 11, 0, '2018-10-18 22:41:44'),
(59, 23.5, 0, 24, 50, 0, 40, 11, 0, '2018-10-18 22:41:49'),
(60, 23.5, 0, 24, 50, 0, 40, 11, 0, '2018-10-18 22:41:54'),
(61, 23, 0, 24, 50, 0, 40, 11, 0, '2018-10-18 22:41:59'),
(62, 23.5, 0, 24, 50, 0, 40, 11, 0, '2018-10-18 22:42:04'),
(63, 23.5, 0, 24, 50, 0, 40, 11, 0, '2018-10-18 22:42:09'),
(64, 23.5, 0, 25, 53, 0, 77, 296, 0, '2018-10-19 12:25:05'),
(65, 23.5, 0, 24, 52, 0, 77, 295, 0, '2018-10-19 12:25:10'),
(66, 23.5, 0, 24, 53, 0, 77, 297, 0, '2018-10-19 12:25:15'),
(67, 23.5, 0, 24, 53, 0, 77, 298, 0, '2018-10-19 12:25:20'),
(68, 23.5, 0, 24, 53, 0, 77, 298, 0, '2018-10-19 12:25:25'),
(69, 23.5, 0, 24, 53, 0, 77, 298, 0, '2018-10-19 12:25:30'),
(70, 23.5, 0, 24, 53, 0, 77, 298, 0, '2018-10-19 12:25:35'),
(71, 23.5, 0, 24, 53, 0, 77, 298, 0, '2018-10-19 12:25:40'),
(72, 23.5, 0, 24, 53, 0, 77, 298, 0, '2018-10-19 12:25:45'),
(73, 23.5, 0, 24, 53, 0, 77, 299, 0, '2018-10-19 12:25:50'),
(74, 23.5, 0, 24, 53, 0, 77, 299, 0, '2018-10-19 12:25:55'),
(75, 23.5, 0, 24, 53, 0, 77, 298, 0, '2018-10-19 12:26:00'),
(76, 23.5, 0, 24, 53, 0, 77, 298, 0, '2018-10-19 12:26:05'),
(77, 23.5, 0, 24, 53, 0, 77, 298, 0, '2018-10-19 12:26:10'),
(78, 23.5, 0, 24, 53, 0, 77, 298, 0, '2018-10-19 12:26:15'),
(79, 23.5, 0, 24, 53, 0, 77, 297, 0, '2018-10-19 12:26:20'),
(80, 23.5, 0, 24, 53, 0, 77, 297, 0, '2018-10-19 12:26:25'),
(81, 23.5, 0, 24, 53, 0, 76, 305, 0, '2018-10-19 12:26:30'),
(82, 23.5, 0, 24, 53, 0, 77, 300, 0, '2018-10-19 12:26:35'),
(83, 23.5, 0, 24, 53, 0, 77, 304, 0, '2018-10-19 12:26:40'),
(84, 23.5, 0, 24, 53, 0, 77, 295, 0, '2018-10-19 12:26:45'),
(85, 23.5, 0, 24, 53, 0, 77, 295, 0, '2018-10-19 12:26:50'),
(86, 23.5, 0, 24, 53, 0, 77, 294, 0, '2018-10-19 12:26:55'),
(87, 23.5, 0, 24, 53, 0, 77, 294, 0, '2018-10-19 12:27:00'),
(88, 23.5, 0, 24, 53, 0, 77, 288, 0, '2018-10-19 12:27:05'),
(89, 23.5, 0, 24, 53, 0, 77, 288, 0, '2018-10-19 12:27:10'),
(90, 23.5, 0, 24, 53, 0, 77, 288, 0, '2018-10-19 12:27:15'),
(91, 23.5, 0, 24, 53, 0, 77, 290, 0, '2018-10-19 12:27:20'),
(92, 23.5, 0, 24, 53, 0, 76, 295, 0, '2018-10-19 12:27:25'),
(93, 23.5, 0, 24, 53, 0, 77, 295, 0, '2018-10-19 12:27:30'),
(94, 23.5, 0, 24, 53, 0, 77, 295, 0, '2018-10-19 12:27:35'),
(95, 23.5, 0, 24, 53, 0, 77, 295, 0, '2018-10-19 12:27:40'),
(96, 23.5, 0, 24, 53, 0, 77, 295, 0, '2018-10-19 12:27:45'),
(97, 23.5, 0, 24, 53, 0, 77, 295, 0, '2018-10-19 12:27:50'),
(98, 23.5, 0, 24, 53, 0, 77, 295, 0, '2018-10-19 12:27:55'),
(99, 23.5, 0, 24, 53, 0, 77, 295, 0, '2018-10-19 12:28:00'),
(100, 23.5, 0, 24, 53, 0, 77, 295, 0, '2018-10-19 12:28:05'),
(101, 23.5, 0, 24, 53, 0, 77, 294, 0, '2018-10-19 12:28:10'),
(102, 23.5, 0, 24, 53, 0, 77, 294, 0, '2018-10-19 12:28:15'),
(103, 23.5, 0, 25, 53, 0, 77, 294, 0, '2018-10-19 12:28:20'),
(104, 23.5, 0, 24, 53, 0, 78, 294, 0, '2018-10-19 12:28:25'),
(105, 23.5, 0, 24, 53, 0, 77, 293, 0, '2018-10-19 12:28:30'),
(106, 23.5, 0, 24, 53, 0, 77, 293, 0, '2018-10-19 12:28:35'),
(107, 23.5, 0, 24, 53, 0, 77, 293, 0, '2018-10-19 12:28:41'),
(108, 23.5, 0, 24, 53, 0, 77, 293, 0, '2018-10-19 12:28:46'),
(109, 23.5, 0, 24, 53, 0, 77, 289, 0, '2018-10-19 12:28:51'),
(110, 23.5, 0, 24, 53, 0, 79, 223, 0, '2018-10-19 12:28:56'),
(111, 23.5, 0, 24, 53, 0, 54, 8, 0, '2018-10-19 12:29:01'),
(112, 23.5, 0, 24, 53, 0, 49, 7, 0, '2018-10-19 12:29:06'),
(113, 23.5, 0, 24, 55, 0, 48, 133, 0, '2018-10-19 12:40:44'),
(114, 23.5, 0, 24, 55, 0, 48, 133, 0, '2018-10-19 12:40:46'),
(115, 23.5, 0, 24, 55, 0, 59, 242, 0, '2018-10-19 12:40:48'),
(116, 23.5, 0, 24, 55, 0, 54, 255, 0, '2018-10-19 12:40:50'),
(117, 23.5, 0, 24, 55, 0, 58, 155, 0, '2018-10-19 12:40:52'),
(118, 23.5, 0, 24, 55, 0, 58, 275, 0, '2018-10-19 12:40:54'),
(119, 23.5, 0, 24, 55, 0, 60, 308, 0, '2018-10-19 12:40:56'),
(120, 23.5, 0, 24, 55, 0, 63, 288, 0, '2018-10-19 12:40:58'),
(121, 23.5, 0, 24, 55, 0, 63, 290, 0, '2018-10-19 12:41:00'),
(122, 23.5, 0, 24, 56, 0, 64, 289, 0, '2018-10-19 12:41:02'),
(123, 23.5, 0, 24, 56, 0, 58, 284, 0, '2018-10-19 12:41:04'),
(124, 23.5, 0, 24, 55, 0, 57, 290, 0, '2018-10-19 12:41:06'),
(125, 23.5, 0, 24, 55, 0, 57, 288, 0, '2018-10-19 12:41:08'),
(126, 23.5, 0, 24, 55, 0, 56, 285, 0, '2018-10-19 12:41:10'),
(127, 23.5, 0, 24, 55, 0, 57, 285, 0, '2018-10-19 12:41:12'),
(128, 23.5, 0, 24, 55, 0, 50, 288, 0, '2018-10-19 12:41:14'),
(129, 23.5, 0, 24, 55, 0, 52, 288, 0, '2018-10-19 12:41:16'),
(130, 23.5, 0, 24, 55, 0, 52, 288, 0, '2018-10-19 12:41:18'),
(131, 23.5, 0, 24, 55, 0, 52, 290, 0, '2018-10-19 12:41:20'),
(132, 23.5, 0, 24, 55, 0, 52, 315, 0, '2018-10-19 12:41:22'),
(133, 23.5, 0, 24, 55, 0, 52, 315, 0, '2018-10-19 12:41:24'),
(134, 23.5, 0, 24, 54, 0, 52, 315, 0, '2018-10-19 12:41:26'),
(135, 23.5, 0, 24, 54, 0, 52, 312, 0, '2018-10-19 12:41:28'),
(136, 23.5, 0, 24, 54, 0, 51, 311, 0, '2018-10-19 12:41:30'),
(137, 23.5, 0, 24, 54, 0, 52, 310, 0, '2018-10-19 12:41:32'),
(138, 23.5, 0, 24, 54, 0, 51, 310, 0, '2018-10-19 12:41:34'),
(139, 23.5, 0, 24, 54, 0, 52, 313, 0, '2018-10-19 12:41:36'),
(140, 23.5, 0, 24, 54, 0, 52, 315, 0, '2018-10-19 12:41:38'),
(141, 23.5, 0, 24, 54, 0, 51, 313, 0, '2018-10-19 12:41:40'),
(142, 23.5, 0, 24, 54, 0, 51, 314, 0, '2018-10-19 12:41:42'),
(143, 23.5, 0, 24, 54, 0, 51, 314, 0, '2018-10-19 12:41:44'),
(144, 23.5, 0, 24, 54, 0, 51, 315, 0, '2018-10-19 12:41:46'),
(145, 23.5, 0, 24, 54, 0, 51, 315, 0, '2018-10-19 12:41:48'),
(146, 23.5, 0, 24, 54, 0, 51, 315, 0, '2018-10-19 12:41:58'),
(147, 23.5, 0, 24, 54, 0, 50, 312, 0, '2018-10-19 12:42:08'),
(148, 23.5, 0, 24, 54, 0, 51, 312, 0, '2018-10-19 12:42:18'),
(149, 23.5, 0, 24, 54, 0, 51, 314, 0, '2018-10-19 12:42:28'),
(150, 23.5, 0, 24, 54, 0, 50, 285, 0, '2018-10-19 12:42:38'),
(151, 23.5, 0, 24, 54, 0, 50, 299, 0, '2018-10-19 12:43:38'),
(152, 23.5, 0, 24, 54, 0, 50, 302, 0, '2018-10-19 12:44:38'),
(153, 23.5, 0, 24, 54, 0, 50, 301, 0, '2018-10-19 12:45:38'),
(154, 23.5, 0, 24, 54, 0, 49, 300, 0, '2018-10-19 12:46:38'),
(155, 23.5, 0, 24, 54, 0, 49, 300, 0, '2018-10-19 12:47:38'),
(156, 23.5, 0, 24, 53, 0, 49, 300, 0, '2018-10-19 12:48:38'),
(157, 23.5, 0, 24, 54, 0, 49, 300, 0, '2018-10-19 12:49:38'),
(158, 23.5, 0, 24, 53, 0, 49, 315, 0, '2018-10-19 12:50:38'),
(159, 23.5, 0, 24, 54, 0, 49, 315, 0, '2018-10-19 12:51:38'),
(160, 23.5, 0, 24, 53, 0, 48, 315, 0, '2018-10-19 12:52:38'),
(161, 23.5, 0, 24, 54, 0, 48, 315, 0, '2018-10-19 12:53:38'),
(162, 23.5, 0, 24, 53, 0, 48, 310, 0, '2018-10-19 12:54:38'),
(163, 23.5, 0, 24, 53, 0, 48, 304, 0, '2018-10-19 12:55:38'),
(164, 23.5, 0, 24, 53, 0, 48, 313, 0, '2018-10-19 12:56:38'),
(165, 23.5, 0, 24, 53, 0, 48, 310, 0, '2018-10-19 12:57:38'),
(166, 23.5, 0, 24, 53, 0, 48, 313, 0, '2018-10-19 12:58:39'),
(167, 23.5, 0, 24, 53, 0, 48, 314, 0, '2018-10-19 12:59:39'),
(168, 23.5, 0, 24, 53, 0, 48, 311, 0, '2018-10-19 13:00:39'),
(169, 23.5, 0, 24, 53, 0, 48, 313, 0, '2018-10-19 13:01:39'),
(170, 23.5, 0, 24, 53, 0, 48, 310, 0, '2018-10-19 13:02:39'),
(171, 23.5, 0, 24, 53, 0, 48, 309, 0, '2018-10-19 13:03:39'),
(172, 23.5, 0, 24, 53, 0, 47, 289, 0, '2018-10-19 13:04:39'),
(173, 23.5, 0, 24, 53, 0, 48, 290, 0, '2018-10-19 13:05:39'),
(174, 23.5, 0, 24, 53, 0, 47, 299, 0, '2018-10-19 13:06:39'),
(175, 23.5, 0, 24, 53, 0, 47, 299, 0, '2018-10-19 13:07:39'),
(176, 23.5, 0, 24, 53, 0, 48, 299, 0, '2018-10-19 13:08:39'),
(177, 23.5, 0, 24, 53, 0, 47, 299, 0, '2018-10-19 13:09:39'),
(178, 23.5, 0, 24, 53, 0, 47, 300, 0, '2018-10-19 13:10:39'),
(179, 23.5, 0, 24, 53, 0, 47, 300, 0, '2018-10-19 13:11:39'),
(180, 23.5, 0, 24, 53, 0, 47, 300, 0, '2018-10-19 13:12:39'),
(181, 23.5, 0, 24, 53, 0, 47, 300, 0, '2018-10-19 13:13:39'),
(182, 23.5, 0, 24, 53, 0, 47, 300, 0, '2018-10-19 13:14:39'),
(183, 23.5, 0, 24, 53, 0, 47, 300, 0, '2018-10-19 13:15:39'),
(184, 23.5, 0, 24, 53, 0, 47, 300, 0, '2018-10-19 13:16:39'),
(185, 23.5, 0, 24, 53, 0, 47, 299, 0, '2018-10-19 13:17:39'),
(186, 23.5, 0, 24, 53, 0, 47, 300, 0, '2018-10-19 13:18:39'),
(187, 23.5, 0, 24, 53, 0, 47, 300, 0, '2018-10-19 13:19:39'),
(188, 23.5, 0, 24, 53, 0, 47, 301, 0, '2018-10-19 13:20:39'),
(189, 23.5, 0, 24, 53, 0, 47, 300, 0, '2018-10-19 13:21:39'),
(190, 23.5, 0, 24, 53, 0, 47, 301, 0, '2018-10-19 13:22:39'),
(191, 23.5, 0, 24, 53, 0, 47, 300, 0, '2018-10-19 13:23:39'),
(192, 23.5, 0, 24, 53, 0, 47, 300, 0, '2018-10-19 13:24:39'),
(193, 23.5, 0, 24, 53, 0, 47, 296, 0, '2018-10-19 13:25:39'),
(194, 23.5, 0, 24, 53, 0, 47, 296, 0, '2018-10-19 13:26:39'),
(195, 23.5, 0, 24, 53, 0, 47, 295, 0, '2018-10-19 13:27:39'),
(196, 23.5, 0, 24, 53, 0, 47, 295, 0, '2018-10-19 13:28:39'),
(197, 23.5, 0, 24, 53, 0, 46, 295, 0, '2018-10-19 13:29:39'),
(198, 23.5, 0, 24, 53, 0, 46, 295, 0, '2018-10-19 13:30:39'),
(199, 23.5, 0, 24, 53, 0, 46, 293, 0, '2018-10-19 13:31:39'),
(200, 23.5, 0, 24, 53, 0, 47, 294, 0, '2018-10-19 13:32:39'),
(201, 23.5, 0, 24, 53, 0, 46, 306, 0, '2018-10-19 13:33:39'),
(202, 23.5, 0, 24, 53, 0, 47, 305, 0, '2018-10-19 13:34:39'),
(203, 23.5, 0, 24, 53, 0, 47, 305, 0, '2018-10-19 13:35:39'),
(204, 23.5, 0, 24, 53, 0, 46, 305, 0, '2018-10-19 13:36:39'),
(205, 23.5, 0, 24, 53, 0, 46, 305, 0, '2018-10-19 13:37:39'),
(206, 23.5, 0, 24, 53, 0, 46, 291, 0, '2018-10-19 13:38:39'),
(207, 23.5, 0, 24, 53, 0, 46, 298, 0, '2018-10-19 13:39:39'),
(208, 23.5, 0, 24, 53, 0, 46, 312, 0, '2018-10-19 13:40:39'),
(209, 23.5, 0, 24, 53, 0, 46, 311, 0, '2018-10-19 13:41:39'),
(210, 23.5, 0, 24, 53, 0, 46, 315, 0, '2018-10-19 13:42:39'),
(211, 23.5, 0, 24, 53, 0, 46, 316, 0, '2018-10-19 13:43:39'),
(212, 23.5, 0, 24, 53, 0, 46, 316, 0, '2018-10-19 13:44:39'),
(213, 23.5, 0, 24, 53, 0, 46, 312, 0, '2018-10-19 13:45:39'),
(214, 23.5, 0, 24, 53, 0, 46, 313, 0, '2018-10-19 13:46:39'),
(215, 23.5, 0, 24, 53, 0, 46, 287, 0, '2018-10-19 13:47:39'),
(216, 23.5, 0, 24, 53, 0, 45, 314, 0, '2018-10-19 13:48:39'),
(217, 23.5, 0, 24, 53, 0, 46, 312, 0, '2018-10-19 13:49:39'),
(218, 23.5, 0, 24, 53, 0, 46, 312, 0, '2018-10-19 13:50:39'),
(219, 23.5, 0, 24, 53, 0, 45, 312, 0, '2018-10-19 13:51:39'),
(220, 23.5, 0, 24, 53, 0, 45, 311, 0, '2018-10-19 13:52:39'),
(221, 23.5, 0, 24, 53, 0, 45, 311, 0, '2018-10-19 13:53:39'),
(222, 23.5, 0, 24, 53, 0, 45, 310, 0, '2018-10-19 13:54:39'),
(223, 23.5, 0, 24, 53, 0, 45, 310, 0, '2018-10-19 13:55:39'),
(224, 23.5, 0, 24, 53, 0, 45, 310, 0, '2018-10-19 13:56:39'),
(225, 23.5, 0, 24, 53, 0, 45, 310, 0, '2018-10-19 13:57:39'),
(226, 23.5, 0, 24, 53, 0, 45, 310, 0, '2018-10-19 13:58:39'),
(227, 23.5, 0, 24, 53, 0, 45, 310, 0, '2018-10-19 13:59:39'),
(228, 23.5, 0, 24, 53, 0, 45, 310, 0, '2018-10-19 14:00:39'),
(229, 23.5, 0, 24, 53, 0, 45, 310, 0, '2018-10-19 14:01:39'),
(230, 23.5, 0, 24, 53, 0, 45, 309, 0, '2018-10-19 14:02:39'),
(231, 23.5, 0, 24, 53, 0, 45, 309, 0, '2018-10-19 14:03:39'),
(232, 23.5, 0, 24, 53, 0, 45, 309, 0, '2018-10-19 14:04:39'),
(233, 23.5, 0, 24, 53, 0, 45, 309, 0, '2018-10-19 14:05:39'),
(234, 23.5, 0, 24, 53, 0, 45, 309, 0, '2018-10-19 14:06:39'),
(235, 23.5, 0, 24, 53, 0, 45, 290, 0, '2018-10-19 14:07:39'),
(236, 23.5, 0, 24, 53, 0, 45, 285, 0, '2018-10-19 14:08:39'),
(237, 23.5, 0, 24, 53, 0, 45, 290, 0, '2018-10-19 14:09:39'),
(238, 23.5, 0, 24, 53, 0, 45, 306, 0, '2018-10-19 14:10:39'),
(239, 23.5, 0, 24, 53, 0, 45, 306, 0, '2018-10-19 14:11:39'),
(240, 23.5, 0, 24, 53, 0, 45, 306, 0, '2018-10-19 14:12:39'),
(241, 23.5, 0, 24, 53, 0, 45, 307, 0, '2018-10-19 14:13:39'),
(242, 23.5, 0, 24, 53, 0, 45, 306, 0, '2018-10-19 14:14:39'),
(243, 23.5, 0, 24, 53, 0, 45, 285, 0, '2018-10-19 14:15:39'),
(244, 23.5, 0, 24, 53, 0, 45, 293, 0, '2018-10-19 14:16:39'),
(245, 23.5, 0, 24, 52, 0, 45, 297, 0, '2018-10-19 14:17:39'),
(246, 23.5, 0, 24, 52, 0, 45, 297, 0, '2018-10-19 14:18:39'),
(247, 23.5, 0, 24, 52, 0, 45, 296, 0, '2018-10-19 14:19:39'),
(248, 23.5, 0, 24, 52, 0, 44, 296, 0, '2018-10-19 14:20:39'),
(249, 23.5, 0, 24, 52, 0, 44, 297, 0, '2018-10-19 14:21:39'),
(250, 23.5, 0, 24, 52, 0, 44, 288, 0, '2018-10-19 14:22:39'),
(251, 23.5, 0, 24, 52, 0, 44, 305, 0, '2018-10-19 14:23:39'),
(252, 23.5, 0, 24, 52, 0, 44, 303, 0, '2018-10-19 14:24:39'),
(253, 23.5, 0, 24, 52, 0, 44, 304, 0, '2018-10-19 14:25:39'),
(254, 23.5, 0, 24, 52, 0, 44, 304, 0, '2018-10-19 14:26:39'),
(255, 23.5, 0, 24, 52, 0, 44, 304, 0, '2018-10-19 14:27:39'),
(256, 23.5, 0, 24, 52, 0, 44, 304, 0, '2018-10-19 14:28:39'),
(257, 23.5, 0, 24, 52, 0, 44, 304, 0, '2018-10-19 14:29:39'),
(258, 23.5, 0, 24, 52, 0, 44, 304, 0, '2018-10-19 14:30:39'),
(259, 23.5, 0, 24, 52, 0, 44, 304, 0, '2018-10-19 14:31:39'),
(260, 23.5, 0, 24, 52, 0, 44, 303, 0, '2018-10-19 14:32:39'),
(261, 23.5, 0, 24, 52, 0, 45, 302, 0, '2018-10-19 14:33:39'),
(262, 23.5, 0, 24, 52, 0, 44, 303, 0, '2018-10-19 14:34:39'),
(263, 23.5, 0, 24, 52, 0, 44, 303, 0, '2018-10-19 14:35:39'),
(264, 23.5, 0, 24, 52, 0, 44, 302, 0, '2018-10-19 14:36:39'),
(265, 23.5, 0, 24, 52, 0, 44, 280, 0, '2018-10-19 14:37:39'),
(266, 23.5, 0, 24, 52, 0, 43, 295, 0, '2018-10-19 14:38:39'),
(267, 23.5, 0, 24, 52, 0, 44, 295, 0, '2018-10-19 14:39:39'),
(268, 23.5, 0, 24, 52, 0, 44, 297, 0, '2018-10-19 14:40:39'),
(269, 23.5, 0, 24, 52, 0, 44, 296, 0, '2018-10-19 14:41:39'),
(270, 23.5, 0, 24, 52, 0, 44, 293, 0, '2018-10-19 14:42:39'),
(271, 23.5, 0, 24, 52, 0, 44, 295, 0, '2018-10-19 14:43:39'),
(272, 23.5, 0, 24, 52, 0, 44, 296, 0, '2018-10-19 14:44:39'),
(273, 23.5, 0, 24, 52, 0, 44, 296, 0, '2018-10-19 14:45:40'),
(274, 23.5, 0, 24, 52, 0, 44, 296, 0, '2018-10-19 14:46:40'),
(275, 23.5, 0, 24, 52, 0, 44, 297, 0, '2018-10-19 14:47:40'),
(276, 23.5, 0, 24, 52, 0, 44, 298, 0, '2018-10-19 14:48:40'),
(277, 23.5, 0, 24, 52, 0, 44, 299, 0, '2018-10-19 14:49:40'),
(278, 23.5, 0, 24, 52, 0, 44, 299, 0, '2018-10-19 14:50:40'),
(279, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 14:51:40'),
(280, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 14:52:40'),
(281, 23.5, 0, 24, 52, 0, 44, 299, 0, '2018-10-19 14:53:40'),
(282, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 14:54:40'),
(283, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 14:55:40'),
(284, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 14:56:40'),
(285, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 14:57:40'),
(286, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 14:58:40'),
(287, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 14:59:40'),
(288, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 15:00:40'),
(289, 23.5, 0, 25, 52, 0, 43, 300, 0, '2018-10-19 15:01:40'),
(290, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 15:02:40'),
(291, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 15:03:40'),
(292, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 15:04:40'),
(293, 23.5, 0, 24, 52, 0, 44, 299, 0, '2018-10-19 15:05:40'),
(294, 23.5, 0, 24, 52, 0, 43, 298, 0, '2018-10-19 15:06:40'),
(295, 23.5, 0, 24, 52, 0, 44, 297, 0, '2018-10-19 15:07:40'),
(296, 23.5, 0, 24, 52, 0, 44, 295, 0, '2018-10-19 15:08:40'),
(297, 23.5, 0, 24, 52, 0, 43, 293, 0, '2018-10-19 15:09:40'),
(298, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 15:10:40'),
(299, 23.5, 0, 24, 52, 0, 43, 300, 0, '2018-10-19 15:11:40'),
(300, 23.5, 0, 24, 52, 0, 44, 301, 0, '2018-10-19 15:12:40'),
(301, 23.5, 0, 24, 52, 0, 44, 300, 0, '2018-10-19 15:13:40'),
(302, 23.5, 0, 24, 52, 0, 44, 301, 0, '2018-10-19 15:14:40'),
(303, 23.5, 0, 24, 52, 0, 43, 302, 0, '2018-10-19 15:15:40'),
(304, 23.5, 0, 24, 52, 0, 43, 302, 0, '2018-10-19 15:16:40'),
(305, 23.5, 0, 24, 52, 0, 44, 302, 0, '2018-10-19 15:17:40'),
(306, 23.5, 0, 24, 52, 0, 43, 302, 0, '2018-10-19 15:18:40'),
(307, 23.5, 0, 24, 52, 0, 43, 303, 0, '2018-10-19 15:19:40'),
(308, 23.5, 0, 25, 52, 0, 44, 303, 0, '2018-10-19 15:20:40'),
(309, 23.5, 0, 24, 52, 0, 44, 303, 0, '2018-10-19 15:21:40'),
(310, 23.5, 0, 24, 52, 0, 44, 302, 0, '2018-10-19 15:22:40'),
(311, 23.5, 0, 24, 52, 0, 44, 303, 0, '2018-10-19 15:23:40'),
(312, 23.5, 0, 24, 52, 0, 44, 302, 0, '2018-10-19 15:24:40'),
(313, 23.5, 0, 24, 52, 0, 43, 302, 0, '2018-10-19 15:25:40'),
(314, 23.5, 0, 24, 52, 0, 44, 302, 0, '2018-10-19 15:26:40'),
(315, 23.5, 0, 24, 52, 0, 43, 302, 0, '2018-10-19 15:27:40'),
(316, 23.5, 0, 24, 52, 0, 44, 302, 0, '2018-10-19 15:28:40'),
(317, 23.5, 0, 25, 52, 0, 44, 302, 0, '2018-10-19 15:29:40'),
(318, 23.5, 0, 24, 52, 0, 44, 301, 0, '2018-10-19 15:30:40'),
(319, 23.5, 0, 25, 52, 0, 44, 301, 0, '2018-10-19 15:31:40'),
(320, 23.5, 0, 25, 53, 0, 44, 301, 0, '2018-10-19 15:32:40'),
(321, 23.5, 0, 24, 52, 0, 44, 301, 0, '2018-10-19 16:36:46'),
(322, 23.5, 0, 24, 41, 0, 54, 429, 0, '2018-10-21 13:23:46'),
(323, 23.5, 0, 24, 42, 0, 44, 417, 0, '2018-10-21 13:25:20'),
(324, 23.5, 0, 24, 41, 0, 45, 416, 0, '2018-10-21 13:25:25'),
(325, 23.5, 0, 24, 42, 0, 45, 417, 0, '2018-10-21 13:25:30'),
(326, 23.5, 0, 24, 42, 0, 45, 419, 0, '2018-10-21 13:25:35'),
(327, 23.5, 0, 24, 42, 0, 45, 418, 0, '2018-10-21 13:25:40'),
(328, 23.5, 0, 24, 42, 0, 45, 430, 0, '2018-10-21 13:25:45'),
(329, 23.5, 0, 24, 42, 0, 44, 432, 0, '2018-10-21 13:25:50'),
(330, 23.5, 0, 24, 42, 0, 45, 433, 0, '2018-10-21 13:25:55');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `manual`
--

CREATE TABLE `manual` (
  `went` int(11) NOT NULL,
  `serwo` int(11) NOT NULL,
  `zarowka1` int(11) NOT NULL,
  `zarowka2` int(11) NOT NULL,
  `zarowka3` int(11) NOT NULL,
  `zarowka4` int(11) NOT NULL,
  `pompa` int(11) NOT NULL,
  `grzalka` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `manual`
--

INSERT INTO `manual` (`went`, `serwo`, `zarowka1`, `zarowka2`, `zarowka3`, `zarowka4`, `pompa`, `grzalka`) VALUES
(20, 1, 1, 1, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `user` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `user`, `password`) VALUES
(1, 'asd', '123');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ustawienia`
--

CREATE TABLE `ustawienia` (
  `tryb_sterowania` char(1) CHARACTER SET ascii COLLATE ascii_bin NOT NULL,
  `czestotliwosc_zapisywania_pomiarow` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `ustawienia`
--

INSERT INTO `ustawienia` (`tryb_sterowania`, `czestotliwosc_zapisywania_pomiarow`) VALUES
('0', 60);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `historia_pomiarow`
--
ALTER TABLE `historia_pomiarow`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `historia_pomiarow`
--
ALTER TABLE `historia_pomiarow`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=331;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;