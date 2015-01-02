CREATE TABLE `clients` (
  `LOGIN` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `PASS` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `EMAIL` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `BALANCE` double NOT NULL DEFAULT '0',
  `REG_DATE` date NOT NULL,
  `ENABLED` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`LOGIN`),
  UNIQUE KEY `login_UNIQUE` (`LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
