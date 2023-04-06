-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 06 Kwi 2023, 21:23
-- Wersja serwera: 10.4.24-MariaDB
-- Wersja PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `api`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rooms`
--

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL,
  `playerX` text NOT NULL,
  `playerO` text NOT NULL,
  `tour` varchar(1) NOT NULL,
  `board` text NOT NULL,
  `xnext` tinyint(1) NOT NULL,
  `onext` tinyint(1) NOT NULL,
  `xscore` int(11) NOT NULL,
  `oscore` int(11) NOT NULL,
  `moves` int(11) NOT NULL,
  `state` varchar(6) NOT NULL,
  `networkX` bigint(20) NOT NULL,
  `networkO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `rooms`
--

INSERT INTO `rooms` (`id`, `playerX`, `playerO`, `tour`, `board`, `xnext`, `onext`, `xscore`, `oscore`, `moves`, `state`, `networkX`, `networkO`) VALUES
(1, '0', '0', 'x', '0,0,0,:0,0,0,:0,0,0,:', 0, 0, 0, 0, 0, 'stop', 0, 0),
(2, '0', '0', 'x', '0,0,0,:0,0,0,:0,0,0,:', 0, 0, 0, 0, 0, 'stop', 0, 0),
(3, '0', '0', 'x', '0,0,0,:0,0,0,:0,0,0,:', 0, 0, 0, 0, 0, 'stop', 0, 0),
(4, '0', '0', 'x', '0,0,0,:0,0,0,:0,0,0,:', 0, 0, 0, 0, 0, 'stop', 0, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` text NOT NULL,
  `password` text NOT NULL,
  `role` text NOT NULL,
  `win` int(11) NOT NULL,
  `losed` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
