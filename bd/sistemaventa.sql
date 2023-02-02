-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 01-02-2023 a las 22:58:13
-- Versión del servidor: 5.7.31
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemaventa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dni` int(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `telefono` varchar(100) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `razon` varchar(100) NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `dni`, `nombre`, `telefono`, `direccion`, `razon`, `fecha`) VALUES
(11, 1112226560, 'oscar echeverry', '3107644116', 'diagona 4B # 15-24', 'Ingeniero de Sistemas', '2022-11-16 13:43:37'),
(18, 29699898, 'Eucaris Gonzalez', '3146762269', 'Diagonal 4B # 15-24', 'Profesora', '2022-12-01 20:09:01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

DROP TABLE IF EXISTS `configuracion`;
CREATE TABLE IF NOT EXISTS `configuracion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `ruc` varchar(20) NOT NULL,
  `telefono` int(20) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `razon` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`id`, `nombre`, `ruc`, `telefono`, `direccion`, `razon`) VALUES
(1, 'EL BUNKER DE LA TECNOLOGIA ', '9000454789', 2671176, 'Diagonal 4B # 15-24', 'Tecnologia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

DROP TABLE IF EXISTS `detalle`;
CREATE TABLE IF NOT EXISTS `detalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cod_pro` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `id_venta` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`id`, `cod_pro`, `cantidad`, `precio`, `id_venta`) VALUES
(113, 1313, 2, '500.00', 69),
(112, 1111, 1, '50.00', 69),
(111, 1010, 100, '50.00', 68),
(110, 1313, 1, '500.00', 67),
(109, 1313, 10, '500.00', 66),
(108, 1313, 10, '500.00', 65),
(107, 1313, 10, '500.00', 64),
(106, 1313, 10, '500.00', 63),
(105, 1313, 10, '500.00', 62),
(104, 1313, 10, '500.00', 61),
(103, 1313, 10, '500.00', 60),
(102, 1212, 50, '50.00', 59),
(101, 1212, 50, '50.00', 58),
(100, 1111, 100, '50.00', 57),
(99, 1010, 100, '50.00', 56),
(114, 1010, 50, '50.00', 70),
(115, 1010, 25, '50.00', 71),
(116, 1010, 10, '50.00', 72),
(117, 1010, 10, '50.00', 73),
(118, 1010, 5, '50.00', 74),
(119, 1111, 10, '50.00', 75),
(120, 1111, 10, '50.00', 76),
(121, 1111, 10, '50.00', 77),
(122, 1010, 100, '50.00', 78),
(123, 1212, 50, '50.00', 79),
(124, 1313, 2, '500.00', 80),
(125, 1010, 50, '50.00', 81),
(126, 1010, 10, '50.00', 82),
(127, 1010, 10, '50.00', 83);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(100) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `proveedor` varchar(100) NOT NULL,
  `cantidad` int(100) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `codigo`, `descripcion`, `proveedor`, `cantidad`, `precio`, `fecha`) VALUES
(13, '1212', 'maouse gamers', 'bunker de la tecnologia ', 100, '50000.00', '2022-11-24 12:10:39'),
(11, '1010', 'teclado gamers', 'bunker de la tecnologia ', 30, '50000.00', '2022-11-24 12:06:55'),
(12, '1111', 'audifonos gamers', 'bunker de la tecnologia ', 100, '50000.00', '2022-11-24 12:07:12'),
(14, '1313', 'pantalla de 50 pulgadas', 'bunker de la tecnologia ', 100, '500000.00', '2022-11-24 12:29:58');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE IF NOT EXISTS `proveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dni` int(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `razon` varchar(100) NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id`, `dni`, `nombre`, `telefono`, `direccion`, `razon`, `fecha`) VALUES
(1, 1, 'bunker de la tecnologia ', '3104545784', 'calle 2 # 2-22 cali colombia', 'tecnologia', '2022-11-16 14:19:54');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `rol` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `correo`, `pass`, `rol`) VALUES
(1, 'oscar echeverry', 'oscar', '123', 'Administrador'),
(2, 'Paula Echeverry', 'paula ', '123', 'Supervisor'),
(3, 'gustavo echeverry', 'gustavo', '123', 'Administrador'),
(4, 'breyner', 'Breyner ', '123', 'Supervisor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE IF NOT EXISTS `ventas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente` varchar(100) NOT NULL,
  `vendedor` varchar(100) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `fecha` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id`, `cliente`, `vendedor`, `total`, `fecha`) VALUES
(83, 'Eucaris Gonzalez', '      OSCAR', '500000.00', '13/12/2022'),
(82, 'oscar echeverry', 'oscar', '500000.00', '13/12/2022'),
(81, 'oscar echeverry', 'Oscar', '2500000.00', '13/12/2022'),
(80, 'oscar echeverry', 'Oscar', '1000000.00', '13/12/2022'),
(79, 'oscar echeverry', 'Oscar', '2500000.00', '13/12/2022'),
(78, 'Eucaris Gonzalez', 'Oscar', '5000000.00', '13/12/2022');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
