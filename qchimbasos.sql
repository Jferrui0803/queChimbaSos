-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-05-2024 a las 09:44:35
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `qchimbasos`
--
CREATE DATABASE IF NOT EXISTS `qchimbasos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci;
USE `qchimbasos`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `almacena`
--

CREATE TABLE `almacena` (
  `ID_P` varchar(5) NOT NULL,
  `CODIGO` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `formato`
--

CREATE TABLE `formato` (
  `COD_FORM` varchar(3) NOT NULL,
  `FORMATO` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materiales`
--

CREATE TABLE `materiales` (
  `ID_M` varchar(5) NOT NULL,
  `COD_FORM` varchar(3) NOT NULL,
  `NumeroSerie` varchar(50) DEFAULT NULL,
  `Nombre` varchar(100) DEFAULT NULL,
  `Localizacion` varchar(50) DEFAULT NULL,
  `UbicacionPrecisa` varchar(100) DEFAULT NULL,
  `Subcategoria` varchar(50) DEFAULT NULL,
  `StockMinimo` int(11) DEFAULT NULL,
  `Descripcion` text DEFAULT NULL,
  `FechaCompra` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `ID_P` varchar(5) NOT NULL,
  `COD_FORM` varchar(3) NOT NULL,
  `Nombre` varchar(100) DEFAULT NULL,
  `Cantidad` int(11) DEFAULT NULL,
  `Localizacion` varchar(50) DEFAULT NULL,
  `UbicacionPrecisa` varchar(100) DEFAULT NULL,
  `StockMinimo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productosauxiliares`
--

CREATE TABLE `productosauxiliares` (
  `ID_A` varchar(5) NOT NULL,
  `COD_FORM` varchar(3) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Formato` varchar(50) DEFAULT NULL,
  `Cantidad` int(11) DEFAULT NULL,
  `Localizacion` varchar(50) DEFAULT NULL,
  `UbicacionPrecisa` varchar(100) DEFAULT NULL,
  `StockMinimo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reactivos`
--

CREATE TABLE `reactivos` (
  `ID_R` varchar(5) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `COD_FORM` varchar(3) NOT NULL,
  `Cantidad` int(11) DEFAULT NULL,
  `Localizacion` varchar(50) DEFAULT NULL,
  `UbicacionPrecisa` varchar(100) DEFAULT NULL,
  `Riesgos` varchar(100) DEFAULT NULL,
  `GradoPurity` varchar(50) DEFAULT NULL,
  `FechaCaducidad` date DEFAULT NULL,
  `StockMinimo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salas`
--

CREATE TABLE `salas` (
  `CODIGO` varchar(10) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `CAJON` varchar(10) DEFAULT NULL,
  `ESTANTERIA` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `NOMBRE` varchar(50) NOT NULL,
  `CONTRASEÑA` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `almacena`
--
ALTER TABLE `almacena`
  ADD PRIMARY KEY (`ID_P`,`CODIGO`);

--
-- Indices de la tabla `formato`
--
ALTER TABLE `formato`
  ADD PRIMARY KEY (`COD_FORM`);

--
-- Indices de la tabla `materiales`
--
ALTER TABLE `materiales`
  ADD PRIMARY KEY (`ID_M`),
  ADD KEY `FK_M2` (`COD_FORM`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`ID_P`);

--
-- Indices de la tabla `productosauxiliares`
--
ALTER TABLE `productosauxiliares`
  ADD PRIMARY KEY (`Nombre`),
  ADD KEY `FK_A` (`ID_A`),
  ADD KEY `FK_A2` (`COD_FORM`);

--
-- Indices de la tabla `reactivos`
--
ALTER TABLE `reactivos`
  ADD PRIMARY KEY (`Nombre`),
  ADD KEY `FK_R` (`ID_R`),
  ADD KEY `FK_R2` (`COD_FORM`);

--
-- Indices de la tabla `salas`
--
ALTER TABLE `salas`
  ADD PRIMARY KEY (`CODIGO`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`NOMBRE`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `materiales`
--
ALTER TABLE `materiales`
  ADD CONSTRAINT `FK_M1` FOREIGN KEY (`ID_M`) REFERENCES `producto` (`ID_P`),
  ADD CONSTRAINT `FK_M2` FOREIGN KEY (`COD_FORM`) REFERENCES `formato` (`COD_FORM`);

--
-- Filtros para la tabla `productosauxiliares`
--
ALTER TABLE `productosauxiliares`
  ADD CONSTRAINT `FK_A` FOREIGN KEY (`ID_A`) REFERENCES `producto` (`ID_P`),
  ADD CONSTRAINT `FK_A2` FOREIGN KEY (`COD_FORM`) REFERENCES `formato` (`COD_FORM`);

--
-- Filtros para la tabla `reactivos`
--
ALTER TABLE `reactivos`
  ADD CONSTRAINT `FK_R` FOREIGN KEY (`ID_R`) REFERENCES `producto` (`ID_P`),
  ADD CONSTRAINT `FK_R2` FOREIGN KEY (`COD_FORM`) REFERENCES `formato` (`COD_FORM`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
