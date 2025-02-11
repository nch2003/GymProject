-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Gazdă: 127.0.0.1
-- Timp de generare: ian. 12, 2025 la 11:07 AM
-- Versiune server: 10.4.32-MariaDB
-- Versiune PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Bază de date: `gymproject`
--

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `gyms`
--

CREATE TABLE `gyms` (
  `id_sala` bigint(20) NOT NULL,
  `nume_sala` varchar(255) NOT NULL,
  `descriere` varchar(255) NOT NULL,
  `tip_ab` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Eliminarea datelor din tabel `gyms`
--

INSERT INTO `gyms` (`id_sala`, `nume_sala`, `descriere`, `tip_ab`) VALUES
(21, 'World gym academy', 'Fitness, Cardio', 'full-time'),
(22, 'Again Fitness Concept', 'Fitness, Cardio, Dance', 'day-time'),
(23, 'Lotus Sport', 'Fitness, Cardio', 'full-time'),
(24, 'Max Fitness Sud', 'Fitness, Cardio', 'full-time'),
(25, 'Magnetic Fit', 'Cardio, Dance, Zumba', 'day-time');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `tip_ab` varchar(255) DEFAULT NULL,
  `admin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Eliminarea datelor din tabel `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `tip_ab`, `admin`) VALUES
(10, 'Utilizator', '$2a$10$6GngAErVIKLRfm2Q/RQEdOBgh0OF2ZZqpTwoperkQCFc21bbz5ClO', 'utilizator@email.com', 'full-time', 0),
(11, 'admin', '$2a$10$qhHg.kq8MgTQ6mci4nOcNuVXTSAcyE8TyzQ/vLqwHQGvLUKM3ghmW', 'admin@admin.com', NULL, 1);

--
-- Indexuri pentru tabele eliminate
--

--
-- Indexuri pentru tabele `gyms`
--
ALTER TABLE `gyms`
  ADD PRIMARY KEY (`id_sala`);

--
-- Indexuri pentru tabele `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pentru tabele eliminate
--

--
-- AUTO_INCREMENT pentru tabele `gyms`
--
ALTER TABLE `gyms`
  MODIFY `id_sala` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pentru tabele `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
