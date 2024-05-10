-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-05-2024 a las 01:52:09
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
  `Cantidad` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `Fecha_compra` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `Fecha_caducidad` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materiales`
--

CREATE TABLE `materiales` (
  `ID` int(7) NOT NULL,
  `Nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `Tipo_Material` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `Descipcion` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `Nº Serie` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_quimicos`
--

CREATE TABLE `productos_quimicos` (
  `ID` int(5) NOT NULL,
  `Nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `ID_Formato` int(7) NOT NULL,
  `Grado Pureza` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos_quimicos`
--

INSERT INTO `productos_quimicos` (`ID`, `Nombre`, `ID_Formato`, `Grado Pureza`) VALUES
(1, 'AMONIO NITRATO', 1, 'No viene reflejado');

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
(2, 1, 4);

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
(8, 'Inflamable', 'Sustancia que se quema con facilidad');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ubicaciones`
--

CREATE TABLE `ubicaciones` (
  `ID` int(7) NOT NULL,
  `Ubicacion` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `formato`
--
ALTER TABLE `formato`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `inventario_auxiliares`
--
ALTER TABLE `inventario_auxiliares`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `inventario_materiales`
--
ALTER TABLE `inventario_materiales`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `inventario_reactivos`
--
ALTER TABLE `inventario_reactivos`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `materiales`
--
ALTER TABLE `materiales`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos_quimicos`
--
ALTER TABLE `productos_quimicos`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `relacion_prod_riesgo`
--
ALTER TABLE `relacion_prod_riesgo`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipo_riesgos`
--
ALTER TABLE `tipo_riesgos`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `ubicaciones`
--
ALTER TABLE `ubicaciones`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT;

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
