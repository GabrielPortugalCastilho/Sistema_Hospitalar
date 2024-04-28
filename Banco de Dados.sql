-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.25-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para hospital_2ºinfod
DROP DATABASE IF EXISTS `hospital_2ºinfod`;
CREATE DATABASE IF NOT EXISTS `hospital_2ºinfod` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `hospital_2ºinfod`;

-- Copiando estrutura para tabela hospital_2ºinfod.atendimento
DROP TABLE IF EXISTS `atendimento`;
CREATE TABLE IF NOT EXISTS `atendimento` (
  `codAtendimento` int(11) NOT NULL AUTO_INCREMENT,
  `plano` varchar(20) NOT NULL,
  `data` date NOT NULL,
  `codPaciente` int(11) NOT NULL,
  `codMedico` int(11) NOT NULL,
  PRIMARY KEY (`codAtendimento`),
  KEY `FK__paciente` (`codPaciente`),
  KEY `FK__medico` (`codMedico`),
  CONSTRAINT `FK__medico` FOREIGN KEY (`codMedico`) REFERENCES `medico` (`codMedico`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK__paciente` FOREIGN KEY (`codPaciente`) REFERENCES `paciente` (`codPaciente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela hospital_2ºinfod.atendimento: ~1 rows (aproximadamente)
INSERT INTO `atendimento` (`codAtendimento`, `plano`, `data`, `codPaciente`, `codMedico`) VALUES
	(2, 'Unimed Nacional', '2023-08-12', 1, 1),
	(3, 'SUS', '2022-11-26', 2, 2);

-- Copiando estrutura para tabela hospital_2ºinfod.exame
DROP TABLE IF EXISTS `exame`;
CREATE TABLE IF NOT EXISTS `exame` (
  `codExame` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `descricao` varchar(1000) NOT NULL,
  `data` date NOT NULL,
  `hora` varchar(5) NOT NULL,
  `codPaciente` int(11) NOT NULL,
  `codMedico` int(11) NOT NULL,
  PRIMARY KEY (`codExame`),
  KEY `FK_exame_paciente` (`codPaciente`),
  KEY `FK_exame_medico` (`codMedico`),
  CONSTRAINT `FK_exame_medico` FOREIGN KEY (`codMedico`) REFERENCES `medico` (`codMedico`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_exame_paciente` FOREIGN KEY (`codPaciente`) REFERENCES `paciente` (`codPaciente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela hospital_2ºinfod.exame: ~0 rows (aproximadamente)
INSERT INTO `exame` (`codExame`, `nome`, `descricao`, `data`, `hora`, `codPaciente`, `codMedico`) VALUES
	(1, 'Raio x', 'Radiografia do braço direito', '2022-11-25', '23:24', 1, 1);

-- Copiando estrutura para tabela hospital_2ºinfod.medico
DROP TABLE IF EXISTS `medico`;
CREATE TABLE IF NOT EXISTS `medico` (
  `codMedico` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `salario` double NOT NULL,
  PRIMARY KEY (`codMedico`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela hospital_2ºinfod.medico: ~3 rows (aproximadamente)
INSERT INTO `medico` (`codMedico`, `nome`, `cpf`, `salario`) VALUES
	(1, 'Gabriel Portugal', '088.920.766-63', 35000),
	(2, 'Gustavo Deodoro', '121.929.386-59', 100000),
	(3, 'Pâmela Andrade', '140.013.296-70', 20000);

-- Copiando estrutura para tabela hospital_2ºinfod.paciente
DROP TABLE IF EXISTS `paciente`;
CREATE TABLE IF NOT EXISTS `paciente` (
  `codPaciente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `sexo` varchar(30) NOT NULL,
  `cpf` varchar(16) NOT NULL,
  `nascimento` date NOT NULL,
  PRIMARY KEY (`codPaciente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela hospital_2ºinfod.paciente: ~2 rows (aproximadamente)
INSERT INTO `paciente` (`codPaciente`, `nome`, `sexo`, `cpf`, `nascimento`) VALUES
	(1, 'Joaquim Galdino', 'Masculino', '123.123.123-12', '1960-11-29'),
	(2, 'Damon Salvatore', 'Masculino', '140.012.159-40', '1487-10-30');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
