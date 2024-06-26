-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-05-2024 a las 14:30:46
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
CREATE DATABASE IF NOT EXISTS `qchimbasos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE `qchimbasos`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `almacenes`
--

CREATE TABLE `almacenes` (
  `ID` int(7) NOT NULL,
  `Nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `almacenes`
--

INSERT INTO `almacenes` (`ID`, `Nombre`) VALUES
(1, 'Almacen 1 /principal'),
(2, 'Almacen General'),
(3, 'Laboratorio Instrumental');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auxiliares`
--

CREATE TABLE `auxiliares` (
  `ID` int(7) NOT NULL,
  `Nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `Tipo_Material` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `auxiliares`
--

INSERT INTO `auxiliares` (`ID`, `Nombre`, `Tipo_Material`) VALUES
(1, 'Bandejas', 'Plástico , verdes, medianas'),
(2, 'Vaso medidor', 'Plástico'),
(3, 'Botellas con cierre manual', 'Cristal'),
(4, 'Exprimidor', 'Plástico'),
(5, 'Vasos variados', 'Plástico'),
(6, 'Coladores', 'Plástico, metal, tela'),
(7, 'Ladrillos', 'De cerámica, 6 cuadrados enteros'),
(8, 'Jeringa', 'Plástico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `formato`
--

CREATE TABLE `formato` (
  `ID` int(5) NOT NULL,
  `Formato` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `formato`
--

INSERT INTO `formato` (`ID`, `Formato`) VALUES
(1, '1Kg'),
(2, '100 g'),
(3, '250 g'),
(4, '500 g'),
(5, '5 g'),
(6, 'No viene reflejado'),
(7, '1 L'),
(8, '500 mL'),
(9, '5 Kg'),
(10, '2.5 L'),
(11, '250 mL'),
(12, '100 mL'),
(13, '250 g,1 kg'),
(14, '10 g');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario_auxiliares`
--

CREATE TABLE `inventario_auxiliares` (
  `ID` int(7) NOT NULL,
  `ID_Almacen` int(7) NOT NULL,
  `ID_Auxiliares` int(7) NOT NULL,
  `ID_Ubicacion` int(7) NOT NULL,
  `Cantidad` int(7) NOT NULL,
  `Stock_Minimo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inventario_auxiliares`
--

INSERT INTO `inventario_auxiliares` (`ID`, `ID_Almacen`, `ID_Auxiliares`, `ID_Ubicacion`, `Cantidad`, `Stock_Minimo`) VALUES
(1, 2, 1, 13, 4, 0),
(2, 2, 2, 13, 2, 0),
(3, 2, 3, 13, 2, 0),
(4, 2, 4, 13, 1, 0),
(5, 2, 5, 13, 10, 0),
(6, 2, 6, 13, 3, 0),
(7, 2, 7, 13, 4, 0),
(8, 2, 8, 13, 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario_materiales`
--

CREATE TABLE `inventario_materiales` (
  `ID` int(7) NOT NULL,
  `ID_Almacen` int(7) NOT NULL,
  `ID_Material` int(7) NOT NULL,
  `ID_Ubicacion` int(7) NOT NULL,
  `Cantidad` int(7) NOT NULL,
  `Fecha_compra` varchar(100) NOT NULL,
  `Stock_Minimo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inventario_materiales`
--

INSERT INTO `inventario_materiales` (`ID`, `ID_Almacen`, `ID_Material`, `ID_Ubicacion`, `Cantidad`, `Fecha_compra`, `Stock_Minimo`) VALUES
(1, 2, 1, 7, 2, '', 0),
(2, 2, 2, 7, 2, '', 0),
(3, 2, 3, 7, 1, '', 0),
(4, 2, 4, 7, 1, '', 0),
(5, 2, 5, 7, 0, '', 0),
(6, 2, 6, 7, 0, '', 0),
(7, 2, 7, 7, 0, '', 0),
(8, 2, 8, 8, 0, '', 0),
(9, 2, 9, 8, 0, '', 0),
(10, 3, 10, 9, 1, '', 0),
(11, 3, 11, 10, 1, '', 0),
(12, 3, 12, 11, 1, '', 0),
(13, 3, 13, 12, 1, '', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario_reactivos`
--

CREATE TABLE `inventario_reactivos` (
  `ID` int(7) NOT NULL,
  `ID_Almacen` int(7) NOT NULL,
  `ID_Producto` int(7) NOT NULL,
  `ID_Ubicacion` int(7) NOT NULL,
  `Cantidad` int(30) NOT NULL,
  `Fecha_caducidad` varchar(100) NOT NULL,
  `Stock_Minimo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inventario_reactivos`
--

INSERT INTO `inventario_reactivos` (`ID`, `ID_Almacen`, `ID_Producto`, `ID_Ubicacion`, `Cantidad`, `Fecha_caducidad`, `Stock_Minimo`) VALUES
(1, 1, 1, 5, 1, '', 0),
(2, 1, 2, 5, 2, '', 0),
(3, 1, 3, 5, 2, '', 0),
(4, 1, 4, 5, 2, '', 0),
(5, 1, 5, 5, 1, '', 0),
(6, 1, 6, 5, 1, '', 0),
(7, 1, 7, 5, 2, '', 0),
(8, 1, 8, 5, 2, '', 0),
(9, 1, 9, 5, 1, '', 0),
(10, 1, 10, 5, 1, '', 0),
(11, 1, 11, 5, 1, '', 0),
(12, 1, 12, 5, 1, '', 0),
(13, 1, 13, 5, 1, '', 0),
(14, 1, 14, 5, 1, '', 0),
(15, 1, 15, 5, 1, '', 0),
(16, 1, 16, 5, 1, '', 0),
(17, 1, 17, 5, 1, '', 0),
(18, 1, 18, 4, 1, '', 0),
(19, 1, 19, 4, 1, '11-2012', 0),
(20, 1, 20, 4, 1, '7-2016', 0),
(21, 1, 21, 4, 1, '', 0),
(22, 1, 22, 4, 1, '3-2016', 0),
(23, 1, 23, 4, 1, '9-2010', 0),
(24, 1, 24, 4, 1, '', 0),
(25, 1, 25, 4, 1, '', 0),
(26, 1, 26, 4, 1, '', 0),
(27, 1, 27, 4, 1, '', 0),
(28, 1, 28, 4, 1, '', 0),
(29, 1, 29, 4, 1, '', 0),
(30, 1, 30, 4, 1, '', 0),
(31, 1, 31, 4, 1, '', 0),
(32, 1, 32, 4, 2, '', 0),
(33, 1, 33, 4, 1, '10-2015', 0),
(34, 1, 34, 4, 2, '', 0),
(35, 1, 35, 4, 1, '2-2015', 0),
(36, 1, 36, 4, 1, '', 0),
(37, 1, 37, 4, 1, '', 0),
(38, 1, 38, 4, 1, '', 0),
(39, 1, 39, 4, 1, '', 0),
(40, 1, 40, 4, 1, '', 0),
(41, 1, 41, 4, 1, '', 0),
(42, 1, 42, 4, 1, '8-2019', 0),
(43, 1, 43, 4, 1, '', 0),
(44, 1, 44, 4, 1, '', 0),
(45, 1, 45, 4, 1, '', 0),
(46, 1, 46, 4, 1, '', 0),
(47, 1, 47, 4, 1, '', 0),
(48, 1, 48, 3, 4, '', 0),
(49, 1, 49, 3, 1, '', 0),
(50, 1, 50, 3, 1, '', 0),
(51, 1, 51, 3, 1, '', 0),
(52, 1, 52, 3, 1, '', 0),
(53, 1, 53, 3, 1, '7-2011', 0),
(54, 1, 54, 3, 2, '', 0),
(55, 1, 55, 3, 1, '', 0),
(56, 1, 56, 3, 1, '', 0),
(57, 1, 57, 3, 1, '', 0),
(58, 1, 58, 3, 1, '', 0),
(59, 1, 59, 3, 2, '5-2009 y 11-2012', 0),
(60, 1, 60, 3, 1, '', 0),
(61, 1, 61, 3, 2, '', 0),
(62, 1, 62, 3, 1, '', 0),
(63, 1, 63, 3, 1, '', 0),
(64, 1, 64, 3, 1, '', 0),
(65, 1, 65, 3, 2, '1-2-2022 y no viene reflejada', 0),
(66, 1, 66, 3, 1, '31-5-2015', 0),
(67, 1, 67, 3, 1, '6-2028', 0),
(68, 1, 68, 3, 1, '', 0),
(69, 1, 69, 3, 2, '', 0),
(70, 1, 70, 3, 1, '', 0),
(71, 1, 71, 3, 1, '', 0),
(72, 1, 72, 3, 1, '', 0),
(73, 1, 73, 3, 1, '', 0),
(74, 1, 74, 3, 1, '3-2015', 0),
(75, 1, 75, 3, 1, '10-2012', 0),
(76, 1, 76, 3, 1, '10/2009', 0),
(77, 1, 77, 3, 1, '5/2009', 0),
(78, 1, 78, 3, 1, '', 0),
(79, 1, 79, 3, 1, '', 0),
(80, 1, 80, 2, 1, '', 0),
(81, 1, 81, 2, 1, '9-2016', 0),
(82, 1, 82, 2, 1, '', 0),
(83, 1, 83, 2, 1, '', 0),
(84, 1, 84, 2, 1, '', 0),
(85, 1, 85, 2, 1, '31-7-2007', 0),
(86, 1, 86, 2, 1, '', 0),
(87, 1, 87, 2, 1, '2-2029', 0),
(88, 1, 88, 2, 1, '6-2010', 0),
(89, 1, 89, 2, 1, '10-11-2004', 0),
(90, 1, 90, 2, 3, '', 0),
(91, 1, 91, 2, 1, '', 0),
(92, 1, 92, 2, 1, '', 0),
(93, 1, 93, 2, 1, '10-2013', 0),
(94, 1, 94, 2, 1, '10-2017', 0),
(95, 1, 95, 2, 1, '', 0),
(96, 1, 96, 2, 1, '', 0),
(97, 1, 97, 2, 1, '', 0),
(98, 1, 98, 2, 1, '1-2025', 0),
(99, 1, 99, 2, 1, '12-2011', 0),
(100, 1, 100, 2, 1, '9-2010', 0),
(101, 1, 101, 2, 1, '', 0),
(102, 1, 102, 2, 1, '', 0),
(103, 1, 103, 2, 1, '', 0),
(104, 1, 104, 2, 1, '', 0),
(105, 1, 105, 2, 1, '', 0),
(106, 1, 106, 2, 2, '', 0),
(107, 1, 107, 2, 1, '10-2011', 0),
(108, 1, 108, 2, 1, '', 0),
(109, 1, 109, 2, 2, '', 0),
(110, 1, 110, 2, 1, '', 0),
(111, 1, 111, 1, 1, '7-2028', 0),
(112, 1, 112, 1, 1, '', 0),
(113, 1, 113, 1, 1, '', 0),
(114, 1, 114, 1, 1, '7-2014', 0),
(115, 1, 115, 1, 1, '', 0),
(116, 1, 116, 1, 1, '', 0),
(117, 1, 117, 1, 4, '5-6-2029', 0),
(118, 1, 118, 1, 3, '2-2028', 0),
(119, 1, 119, 1, 1, '2-11-2013', 0),
(120, 1, 120, 1, 2, '1-2030', 0),
(121, 1, 121, 1, 1, '6-2010', 0),
(122, 1, 122, 1, 1, '9-2014', 0),
(123, 1, 123, 1, 1, '12-2008', 0),
(124, 1, 124, 1, 2, '11-2024', 0),
(125, 1, 125, 1, 1, '8-2024', 0),
(126, 1, 126, 1, 3, '', 0),
(127, 1, 127, 1, 3, '', 0),
(128, 1, 128, 1, 1, '3-2025', 0),
(129, 1, 129, 1, 1, '', 0),
(130, 1, 130, 1, 1, '', 0),
(131, 1, 131, 1, 1, '12-2008', 0),
(132, 1, 132, 1, 3, '', 0),
(133, 1, 133, 1, 1, '11-2012', 0),
(134, 1, 134, 1, 1, '', 0),
(135, 1, 135, 1, 1, '', 0),
(136, 1, 136, 6, 1, '', 0),
(137, 1, 137, 6, 1, '', 0),
(138, 1, 138, 6, 2, '', 0),
(139, 1, 139, 6, 1, '', 0),
(140, 1, 140, 6, 4, '', 0),
(141, 1, 141, 6, 1, '', 0),
(142, 1, 142, 6, 2, '', 0),
(143, 1, 143, 6, 1, '', 0),
(144, 1, 144, 6, 1, '2-2012', 0),
(145, 1, 145, 6, 1, '', 0),
(146, 1, 146, 6, 2, '', 0),
(147, 1, 147, 6, 1, '', 0),
(148, 1, 148, 6, 1, '', 0),
(149, 1, 149, 6, 1, '28-2-2007', 0),
(150, 1, 150, 6, 1, '', 0),
(151, 1, 151, 6, 1, '', 0),
(152, 1, 152, 6, 1, '', 0),
(153, 1, 153, 6, 1, '11-2011', 0),
(154, 1, 154, 6, 1, '', 0),
(155, 1, 155, 6, 1, '', 0),
(156, 1, 156, 6, 2, '', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materiales`
--

CREATE TABLE `materiales` (
  `ID` int(7) NOT NULL,
  `Nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `Tipo_Material` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `Descripcion` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `Nº_Serie` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `materiales`
--

INSERT INTO `materiales` (`ID`, `Nombre`, `Tipo_Material`, `Descripcion`, `Nº_Serie`) VALUES
(1, 'Vasos de precipitados', 'plástico', '1000 ml', 0),
(2, 'Vasos de precipitados', 'plástico', '500ml', 0),
(3, 'Vaso de precipitados', 'plástico', '1000 ml', 0),
(4, 'Matraz erlenmeyer grande', 'cristal', '3 L', 0),
(5, 'Matraz erlenmeyer con tapón de cristal', 'cristal', '1000 ml', 0),
(6, 'Matraz erlenmeyer', 'cristal', '2L', 0),
(7, 'Matraz erlenmeyer', 'cristal', '1000 ml', 0),
(8, 'Recipiente aséptico', 'plástico', '2L', 0),
(9, 'Tubos de ensayo graduados', 'cristal', '50 ml', 0),
(10, 'Conductímetro', 'Instrumental Electrónico', '', 534031),
(11, 'Conductímetro', 'Instrumental Electrónico', '', 531030),
(12, 'Potenciómetro', 'Instrumental Electrónico', '', 539010),
(13, 'Potenciómetro', 'Instrumental Electrónico', '', 539007);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_quimicos`
--

CREATE TABLE `productos_quimicos` (
  `ID` int(5) NOT NULL,
  `Nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `ID_Formato` int(7) NOT NULL,
  `Grado_Pureza` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos_quimicos`
--

INSERT INTO `productos_quimicos` (`ID`, `Nombre`, `ID_Formato`, `Grado_Pureza`) VALUES
(1, 'AMONIO NITRATO', 1, 'No viene reflejado'),
(2, 'BROMURO DE AMONIO (PARA ANÁLISIS)', 1, '99%'),
(3, 'AZUFRE SUBLIMADO (AZUFRE FLOR)', 1, 'Puro'),
(4, 'ALUMINIO NITRATO 9-HIDRATO', 1, '98 %'),
(5, 'ACETANILIDA CRISTALIZADA', 1, 'Purísima'),
(6, 'AMINOMETANO', 2, '99 %'),
(7, 'ESTAÑO II CLORURO', 3, '99 %'),
(8, 'ÁCIDO TARTÁRICO', 4, '99 %'),
(9, 'ÁCIDO TARTÁRICO', 1, '99.7%'),
(10, 'BIPIRIDINA', 5, 'No viene reflejado'),
(11, 'HIDROXIQUINOLEINA', 2, '98 %'),
(12, 'HIDROXIQUINOLEINA', 4, '99 %'),
(13, 'CELITE 545', 6, 'No viene reflejado'),
(14, 'REACTIVO DE BENEDICT', 7, 'No viene reflejado'),
(15, 'REACTIVO FEHLING-A', 8, 'No viene reflejado'),
(16, 'REACTIVO FEHLING-B', 8, 'No viene reflejado'),
(17, 'UREA', 6, '99 %'),
(18, 'AMONIO MOLIBDATO 4-HIDRATO', 3, '99 %'),
(19, 'AMONIO OXALATO 1-HIDRATO', 4, '99.5%'),
(20, 'AMONIO OXALATO 1-HIDRATO', 1, '99 %'),
(21, 'PERSULFATO AMÓNICO', 6, '98 %'),
(22, 'AMÓNIO TIOCIANATO', 1, '99 %'),
(23, 'AMÓNIO TIOCIANATO', 1, '99 %'),
(24, 'AMONIO META-VANADATO', 3, '99 %'),
(25, 'CADMIO NITRATO 4-HIDRATO', 3, '98 %'),
(26, 'COBALTO II NITRATO 6-HIDRATO', 3, '98 %'),
(27, 'COBRE (II) NITRATO 3-HIDRATO', 4, '99 %'),
(28, 'ÓXIDO DE COBRE (II)', 4, '95 %'),
(29, 'COBRE (II) SULFATO 5-HIDRATO', 1, '99 %'),
(30, 'COBRE (II) SULFATO 5-HIDRATO', 6, 'No viene reflejado'),
(31, 'ESTRONCIO NITRATO ANHIDRO', 1, '98 %'),
(32, 'HIERRO (III) CLORURO 6-HIDRATO', 1, '98 %'),
(33, 'HIERRO (III) NITRATO 9-HIDRATO', 1, '98 %'),
(34, 'HIERRO (II) SULFATO ~ 2-HIDRATO', 1, '80 %'),
(35, 'HIERRO (II) SULFATO 7-HIDRATO', 1, '99.5%'),
(36, 'HIERRO (II) SULFATO 7-HIDRATO', 1, '98 %'),
(37, 'LANTANO (III) CLORURO 7-HIDRATO', 3, '98 %'),
(38, 'MANGANESO (IV) ÓXIDO', 4, '85 %'),
(39, 'SULFATO DE MANGANESO 1-HIDRATO', 1, 'Purísima'),
(40, 'MANGANESO (II) SULFATO 1-HIDRATO', 1, '99 %'),
(41, 'CARBONATO DE NÍQUEL', 6, 'No viene reflejado'),
(42, 'NÍQUEL (II) NITRATO 6-HIDRATO PRS', 1, '98 %'),
(43, 'PLOMO (II) ACETATO 3-HIDRATO', 1, '99 %'),
(44, 'ZINC NITRATO 6-HIDRATO', 4, '98 %'),
(45, 'ÓXIDO DE ZINC', 6, '98 %'),
(46, 'SULFATO DE ZINC 7-HIDRATO', 6, 'Puro'),
(47, 'ZINC SULFATO 1-HIDRATO', 1, '98 %'),
(48, 'SODIO CARBONATO 10-HIDRATO', 1, '99 %'),
(49, 'SODIO CARBONATO ANHIDRO', 2, 'Puro'),
(50, 'SODIO CARBONATO ANHIDRO', 1, 'Puro'),
(51, 'SODIO OXALATO', 1, '98 %'),
(52, 'SODIO SALICILATO', 3, '99.5%'),
(53, 'SODIO SILICATO', 7, 'No viene reflejado'),
(54, 'SODIO TUNGSTATO 2-HIDRATO', 3, '99 %'),
(55, 'POTASIO DE BROMURO', 4, '98 %'),
(56, 'POTASIO DE BROMURO', 4, '99 %'),
(57, 'POTASIO DE BROMURO', 3, 'Puro'),
(58, 'POTASIO DE BROMURO', 3, '98 %'),
(59, 'POTASIO CARBONATO PRS', 1, '99 %'),
(60, 'POTASIO CLORATO', 6, '98.5%'),
(61, 'OXALATO DE POTASIO 1-HIDRATO', 4, '99 %'),
(62, 'POTASIO OXALATO', 1, 'No viene reflejado'),
(63, 'POTASIO BIOXALATO', 4, '99 %'),
(64, 'POTASIO BIOXALATO', 1, '99 %'),
(65, 'PERMANGANATO DE POTASIO', 3, 'No viene reflejado'),
(66, 'PERMANGANATO DE POTASIO', 2, 'No viene reflejado'),
(67, 'PERMANGANATO DE POTASIO', 1, '99 %'),
(68, 'PERMANGANATO DE POTASIO', 6, 'Puro'),
(69, 'POTASIO TIOCIANATO', 4, '99 %'),
(70, 'POTASIO TIOCIANATO', 4, '98 %'),
(71, 'BARIO ACETATO', 4, '98 %'),
(72, 'BARIO HIDRÓXIDO 8-HIDRATO', 4, 'Puro'),
(73, 'BARIO HIDRÓXIDO 8-HIDRATO', 1, '98 %'),
(74, 'CALCIO CLORURO ANHIDRO QP', 1, '95 %'),
(75, 'CALCIO CLORURO 2-HIDRATO PRS', 9, '99 %'),
(76, 'CALCIO OXOLATO-1-HIDRATO', 4, '98 %'),
(77, 'CALCIO HIDROXISO', 1, '95 %'),
(78, 'HIDROXIHAMONIO CLORURO', 3, '99.55%'),
(79, 'AMONIO CLORURO', 1, '100 %'),
(80, 'ÁCIDO BENZOICO', 4, '99.5%'),
(81, 'ÁCIDO BENZOICO', 1, '99.5%'),
(82, '4-DICLOROBENCENO', 4, 'Puro'),
(83, 'ÁCIDO 2-CLOROBENZOICO', 4, '99 %'),
(84, 'ÁCIDO 4-CLOROBENZOICO', 3, '97 %'),
(85, 'ÁCIDO 4-AMINOBENZOICO', 3, '99 %'),
(86, 'ÁCIDO 4-NITROBENZOICO', 6, '99 %'),
(87, 'AMONÍACO', 7, '30 %'),
(88, 'ISOBUTANOL', 7, '99 %'),
(89, '2-BUTANOL', 7, '99 %'),
(90, '3-METIL-1-BUTANOL SEGÚN GERBER', 7, '98.5%'),
(91, 'CLORAMINA T', 3, '98 %'),
(92, 'CLORAMINA T TRIHIDRATADA', 3, '99 %'),
(93, 'DIMETILGLIOXIMA', 4, '99 %'),
(94, 'ÁCIDO 3,5-DINITROSALICÍLICO PS', 2, '99 %'),
(95, 'ETILENGLICOL', 7, '99 %'),
(96, 'AMONIO FORMATO', 1, '99 %'),
(97, 'CICLOHEXANOL', 6, 'Purísimo'),
(98, 'DICLOROMETANO', 7, 'No viene reflejado'),
(99, 'NAFTALENO PRS', 4, '98 %'),
(100, 'NAFTALENO PRS', 4, '98 %'),
(101, 'NAFTALENO', 6, 'Purísimo'),
(102, '1-NAFTILAMINA PS', 2, '99 %'),
(103, '1-NAFTOL', 3, '99 %'),
(104, 'p-NITROFENOL', 2, '96 %'),
(105, 'p-NITROFENOL', 3, '96 %'),
(106, 'ÁCIDO OXÁLICO 2-HIDRATO', 4, '99 %'),
(107, 'ÁCIDO OXÁLICO', 7, 'No viene reflejado'),
(108, 'ÁCIDO OXÁLICO CRISTALIZADO', 6, 'Purísimo'),
(109, 'ÁCIDO SULFANÍLICO', 3, '98 %'),
(110, 'TRITÓN X', 6, 'No viene reflejado'),
(111, 'ÁCIDO ACÉTICO GLACIAL', 7, '99.7%'),
(112, 'ÁCIDO ACÉTICO GLACIAL', 7, '99.9%'),
(113, 'ÁCIDO ACÉTICO GLACIAL', 10, '99.9%'),
(114, 'ÁCIDO ACÉTICO GLACIAL', 11, '99.5%'),
(115, 'ÁCIDO ACÉTICO', 7, '99.7%'),
(116, 'ANHÍDRICO ACÉTICO', 7, '98 %'),
(117, 'ÁCIDO CLORHÍDRICO', 10, '37 %'),
(118, 'ÁCIDO CLORHÍDRICO', 7, '37 %'),
(119, 'ÁCIDO CLORHÍDRICO', 7, '37 %'),
(120, 'ÁCIDO PERCLÓRICO EN ÁCIDO ACÉTICO', 7, 'No viene reflejado'),
(121, 'ÁCIDO PERCLÓRICO', 7, '60 %'),
(122, 'ÁCIDO ORTOFOSFÓRICO', 7, '85 %'),
(123, 'ÁCIDO ORTOFOSFÓRICO', 7, '85 %'),
(124, 'ÁCIDO NÍTRICO', 7, '65 %'),
(125, 'ÁCIDO NÍTRICO', 7, '65 %'),
(126, 'ÁCIDO NÍTRICO', 7, '70 %'),
(127, 'ÁCIDO SULFÚRICO', 6, '96 %'),
(128, 'ÁCIDO SULFÚRICO', 7, '98 %'),
(129, 'ÁCIDO FLUORHÍDRICO', 11, 'No viene reflejado'),
(130, 'ÁCIDO FÓRMICO', 8, '95 %'),
(131, 'ÁCIDO ORTO-FOSFÓRICO', 7, '85 %'),
(132, 'REACTIVO DE HANUS', 7, 'No viene reflejado'),
(133, 'REACTIVO DE HANUS', 7, 'No viene reflejado'),
(134, 'DIETANOLAMINA', 12, '99 %'),
(135, 'CLOROACETILO CLORURO RPE', 12, 'Puro'),
(136, 'SODIO ACETATO ANHIDRO', 6, '99 %'),
(137, 'SODIO TETRATO 2 HIDRATO', 4, '99 %'),
(138, 'SODIO TETRA-BORATO 10 HIDRATO', 1, 'No viene reflejado'),
(139, 'TETRABORATO SODICO', 6, 'No viene reflejado'),
(140, 'SODIO TETRA-FENILBORATO', 14, '99 %'),
(141, 'SODIO HIDROGENOCARBONATO', 1, '99.9%'),
(142, 'SODIO HIDROGENOCARBONATO', 1, '99 %'),
(143, 'CARBOXIMETILCELULOSA SAL SODICA', 3, '99.5%'),
(144, 'SODIO FOSFATO MONO-BASICO 2-HIDRATO', 3, '99.10%'),
(145, 'SODIO FOSFATO MONO-BASICO 2-HIDRATO', 3, 'Puro'),
(146, 'SODIO FOSFATO MONO-BASICO 1-HIDRATO', 4, '99 %'),
(147, 'SODIO FOSFATO MONO-BASICO 2-HIDRATO', 4, '99 %'),
(148, 'SODIO FOSFATO DI-BASICO ANHIDRATO', 4, '98 %'),
(149, 'SODIO HIDRÓGENO FOSFATO ANHIDRO', 1, '99 %'),
(150, 'FOSFATO TRISÓDICO ANHIDRO', 4, 'No viene reflejado'),
(151, 'DI-SODIO HIDRÓGENO FOSFATO ANHIDRO', 4, '98 %'),
(152, 'DI-SODIO HIDRÓGENO FOSFATO ANHIDRO', 4, '99 %'),
(153, 'ÁCIDO ETILENDIAMINOTETRAACÉTICO SAL DISÓDICA 2-HIDRATO', 3, '99 %'),
(154, 'SODIO MOLIBDATO 2-HIDRATO', 1, '99 %'),
(155, 'SODIO MOLIBDATO CRIST', 1, '99 %'),
(156, 'MOLIBDENO (VI) ÓXIDO', 3, '99 %'),
(158, 'BROMOOOK', 2, '20%');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relacion_prod_riesgo`
--

CREATE TABLE `relacion_prod_riesgo` (
  `ID` int(7) NOT NULL,
  `ID_Producto` int(7) NOT NULL,
  `ID_Riesgo` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `relacion_prod_riesgo`
--

INSERT INTO `relacion_prod_riesgo` (`ID`, `ID_Producto`, `ID_Riesgo`) VALUES
(1, 1, 1),
(2, 1, 4),
(3, 2, 3),
(4, 3, 3),
(5, 4, 1),
(6, 4, 5),
(7, 5, 9),
(8, 6, 9),
(9, 7, 5),
(10, 8, 4),
(11, 9, 4),
(12, 10, 3),
(13, 11, 9),
(14, 12, 9),
(15, 13, 5),
(16, 14, 5),
(17, 15, 9),
(18, 16, 2),
(19, 17, 9),
(20, 18, 9),
(21, 19, 5),
(22, 20, 5),
(23, 21, 9),
(24, 22, 5),
(25, 23, 5),
(26, 24, 5),
(27, 25, 5),
(28, 25, 6),
(29, 26, 1),
(30, 26, 5),
(31, 27, 1),
(32, 27, 2),
(33, 27, 6),
(34, 28, 2),
(35, 29, 5),
(36, 29, 6),
(37, 29, 2),
(38, 30, 5),
(39, 30, 6),
(40, 30, 2),
(41, 31, 1),
(42, 32, 5),
(43, 33, 1),
(44, 33, 4),
(45, 34, 9),
(46, 35, 5),
(47, 36, 5),
(48, 37, 9),
(49, 38, 5),
(50, 39, 9),
(51, 40, 9),
(52, 41, 9),
(53, 42, 5),
(54, 42, 1),
(55, 42, 2),
(56, 42, 6),
(57, 42, 7),
(58, 43, 5),
(59, 44, 5),
(60, 44, 1),
(61, 45, 9),
(62, 46, 9),
(63, 47, 1),
(64, 47, 6),
(65, 48, 4),
(66, 49, 4),
(67, 50, 4),
(68, 51, 5),
(69, 52, 5),
(70, 53, 4),
(71, 54, 5),
(72, 55, 9),
(73, 56, 9),
(74, 57, 9),
(75, 58, 9),
(76, 59, 5),
(77, 60, 1),
(78, 60, 5),
(79, 61, 3),
(80, 62, 9),
(81, 63, 5),
(82, 64, 5),
(83, 65, 1),
(84, 65, 5),
(85, 65, 6),
(86, 66, 1),
(87, 66, 5),
(88, 66, 6),
(89, 67, 1),
(90, 67, 5),
(91, 67, 6),
(92, 68, 1),
(93, 68, 5),
(94, 68, 6),
(95, 69, 5),
(96, 70, 5),
(97, 71, 5),
(98, 72, 5),
(99, 73, 5),
(100, 74, 4),
(101, 75, 4),
(102, 76, 5),
(103, 77, 4),
(104, 78, 2),
(105, 78, 6),
(106, 78, 7),
(107, 79, 9),
(108, 80, 9),
(109, 81, 5),
(110, 82, 3),
(111, 83, 9),
(112, 84, 5),
(113, 85, 9),
(114, 86, 5),
(115, 87, 2),
(116, 87, 6),
(117, 87, 5),
(118, 87, 4),
(119, 88, 4),
(120, 89, 4),
(121, 90, 5),
(122, 91, 4),
(123, 92, 2),
(124, 93, 5),
(125, 94, 5),
(126, 94, 4),
(127, 95, 5),
(128, 96, 9),
(129, 97, 9),
(130, 98, 5),
(131, 98, 4),
(132, 98, 7),
(133, 99, 5),
(134, 99, 6),
(135, 100, 5),
(136, 100, 6),
(137, 101, 8),
(138, 102, 5),
(139, 102, 6),
(140, 103, 5),
(141, 104, 5),
(142, 105, 5),
(143, 106, 5),
(144, 107, 9),
(145, 108, 3),
(146, 109, 5),
(147, 110, 9),
(148, 111, 8),
(149, 111, 2),
(150, 112, 8),
(151, 112, 2),
(152, 113, 8),
(153, 113, 2),
(154, 114, 2),
(155, 115, 3),
(156, 115, 2),
(157, 116, 2),
(158, 117, 2),
(159, 117, 5),
(160, 117, 4),
(161, 118, 2),
(162, 118, 5),
(163, 118, 4),
(164, 119, 2),
(165, 119, 5),
(166, 119, 4),
(167, 120, 8),
(168, 120, 2),
(169, 121, 1),
(170, 121, 2),
(171, 122, 2),
(172, 123, 2),
(173, 124, 1),
(174, 124, 3),
(175, 124, 2),
(176, 125, 1),
(177, 125, 3),
(178, 125, 2),
(179, 126, 2),
(180, 127, 9),
(181, 128, 2),
(182, 129, 3),
(183, 129, 2),
(184, 130, 2),
(185, 131, 2),
(186, 132, 2),
(187, 133, 2),
(188, 134, 9),
(189, 135, 2),
(190, 136, 9),
(191, 137, 9),
(192, 138, 9),
(193, 139, 9),
(194, 140, 9),
(195, 141, 9),
(196, 142, 9),
(197, 143, 9),
(198, 144, 9),
(199, 145, 9),
(200, 146, 9),
(201, 147, 9),
(202, 148, 9),
(203, 149, 9),
(204, 150, 9),
(205, 151, 9),
(206, 152, 9),
(207, 153, 5),
(208, 154, 9),
(209, 155, 9),
(210, 156, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_riesgos`
--

CREATE TABLE `tipo_riesgos` (
  `ID` int(7) NOT NULL,
  `Nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `Descripcion` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_riesgos`
--

INSERT INTO `tipo_riesgos` (`ID`, `Nombre`, `Descripcion`) VALUES
(1, 'Comburente', 'El comburente es una sustancia que oxida al combustible en las reacciones de combustión.'),
(2, 'Corrosivo', 'Sustancia que puede destruir o dañar irreversiblemente otra superficie o sustancia con la cual entra en contacto'),
(3, 'Toxicidad aguda', 'Está en presencia de un producto químico que es extremadamente tóxico en contacto con la piel, si se inhala o ingiere, y que puede ser mortal.'),
(4, 'Irritante ', 'Sustancias químicas que causan enrojecimiento, resequedad y grietas al contacto con la piel.'),
(5, 'Nocivo', 'Sustancias que pueden causar daño en el organismo humano, bien sea por inhalación, en contacto con la piel, o por ingestión.'),
(6, 'Peligroso para el medio ambiente', 'En caso de contacto con el medioambiente, constituiría o podría constituir un peligro inmediato o futuro para uno o más componentes del medioambiente.'),
(7, 'Carcinógeno', 'Sustancia o mezcla de sustancias que induce cáncer o aumenta su incidencia.'),
(8, 'Inflamable', 'Sustancia que se quema con facilidad'),
(9, 'Atención', 'Precaución con el producto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ubicaciones`
--

CREATE TABLE `ubicaciones` (
  `ID` int(7) NOT NULL,
  `Ubicacion` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ubicaciones`
--

INSERT INTO `ubicaciones` (`ID`, `Ubicacion`) VALUES
(1, '1CA'),
(2, '2N'),
(3, '3N'),
(4, '4N'),
(5, '5N'),
(6, '8l'),
(7, 'estantería 1, balda 3'),
(8, 'estantería 1,balda 4'),
(9, 'C1'),
(10, 'C2'),
(11, 'P1'),
(12, 'P2'),
(13, 'Estantería 0, balda 4');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `Nombre` varchar(100) NOT NULL,
  `Contraseña` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`Nombre`, `Contraseña`) VALUES
('admin', 'admin');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `almacenes`
--
ALTER TABLE `almacenes`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `auxiliares`
--
ALTER TABLE `auxiliares`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `formato`
--
ALTER TABLE `formato`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `inventario_auxiliares`
--
ALTER TABLE `inventario_auxiliares`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Almacen` (`ID_Almacen`),
  ADD KEY `ID_Auxiliares` (`ID_Auxiliares`),
  ADD KEY `ID_Ubicacion` (`ID_Ubicacion`);

--
-- Indices de la tabla `inventario_materiales`
--
ALTER TABLE `inventario_materiales`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Almacen` (`ID_Almacen`),
  ADD KEY `ID_Material` (`ID_Material`),
  ADD KEY `ID_Ubicacion` (`ID_Ubicacion`);

--
-- Indices de la tabla `inventario_reactivos`
--
ALTER TABLE `inventario_reactivos`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Almacen` (`ID_Almacen`),
  ADD KEY `ID_Ubicacion` (`ID_Ubicacion`),
  ADD KEY `ID_Producto` (`ID_Producto`);

--
-- Indices de la tabla `materiales`
--
ALTER TABLE `materiales`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `productos_quimicos`
--
ALTER TABLE `productos_quimicos`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Formato` (`ID_Formato`);

--
-- Indices de la tabla `relacion_prod_riesgo`
--
ALTER TABLE `relacion_prod_riesgo`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Riesgo` (`ID_Riesgo`),
  ADD KEY `ID_Producto` (`ID_Producto`);

--
-- Indices de la tabla `tipo_riesgos`
--
ALTER TABLE `tipo_riesgos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `ubicaciones`
--
ALTER TABLE `ubicaciones`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `almacenes`
--
ALTER TABLE `almacenes`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `auxiliares`
--
ALTER TABLE `auxiliares`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `formato`
--
ALTER TABLE `formato`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `inventario_auxiliares`
--
ALTER TABLE `inventario_auxiliares`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `inventario_materiales`
--
ALTER TABLE `inventario_materiales`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `inventario_reactivos`
--
ALTER TABLE `inventario_reactivos`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=157;

--
-- AUTO_INCREMENT de la tabla `materiales`
--
ALTER TABLE `materiales`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `productos_quimicos`
--
ALTER TABLE `productos_quimicos`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=159;

--
-- AUTO_INCREMENT de la tabla `relacion_prod_riesgo`
--
ALTER TABLE `relacion_prod_riesgo`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=211;

--
-- AUTO_INCREMENT de la tabla `tipo_riesgos`
--
ALTER TABLE `tipo_riesgos`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `ubicaciones`
--
ALTER TABLE `ubicaciones`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `inventario_auxiliares`
--
ALTER TABLE `inventario_auxiliares`
  ADD CONSTRAINT `inventario_auxiliares_ibfk_1` FOREIGN KEY (`ID_Almacen`) REFERENCES `almacenes` (`ID`),
  ADD CONSTRAINT `inventario_auxiliares_ibfk_2` FOREIGN KEY (`ID_Auxiliares`) REFERENCES `auxiliares` (`ID`),
  ADD CONSTRAINT `inventario_auxiliares_ibfk_3` FOREIGN KEY (`ID_Ubicacion`) REFERENCES `ubicaciones` (`ID`);

--
-- Filtros para la tabla `inventario_materiales`
--
ALTER TABLE `inventario_materiales`
  ADD CONSTRAINT `inventario_materiales_ibfk_1` FOREIGN KEY (`ID_Almacen`) REFERENCES `almacenes` (`ID`),
  ADD CONSTRAINT `inventario_materiales_ibfk_2` FOREIGN KEY (`ID_Material`) REFERENCES `materiales` (`ID`),
  ADD CONSTRAINT `inventario_materiales_ibfk_3` FOREIGN KEY (`ID_Ubicacion`) REFERENCES `ubicaciones` (`ID`);

--
-- Filtros para la tabla `inventario_reactivos`
--
ALTER TABLE `inventario_reactivos`
  ADD CONSTRAINT `inventario_reactivos_ibfk_1` FOREIGN KEY (`ID_Almacen`) REFERENCES `almacenes` (`ID`),
  ADD CONSTRAINT `inventario_reactivos_ibfk_2` FOREIGN KEY (`ID_Ubicacion`) REFERENCES `ubicaciones` (`ID`),
  ADD CONSTRAINT `inventario_reactivos_ibfk_3` FOREIGN KEY (`ID_Producto`) REFERENCES `productos_quimicos` (`ID`);

--
-- Filtros para la tabla `productos_quimicos`
--
ALTER TABLE `productos_quimicos`
  ADD CONSTRAINT `productos_quimicos_ibfk_1` FOREIGN KEY (`ID_Formato`) REFERENCES `formato` (`ID`);

--
-- Filtros para la tabla `relacion_prod_riesgo`
--
ALTER TABLE `relacion_prod_riesgo`
  ADD CONSTRAINT `relacion_prod_riesgo_ibfk_1` FOREIGN KEY (`ID_Riesgo`) REFERENCES `tipo_riesgos` (`ID`),
  ADD CONSTRAINT `relacion_prod_riesgo_ibfk_2` FOREIGN KEY (`ID_Producto`) REFERENCES `productos_quimicos` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
