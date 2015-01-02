CREATE TABLE `client_roles` (
  `USER_ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `ROLE` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  UNIQUE KEY `uni_username_role` (`ROLE`,`LOGIN`),
  UNIQUE KEY `UK_99o83w9fto4b7as1ull1202fw` (`ROLE`,`LOGIN`),
  KEY `fk_username_idx` (`LOGIN`),
  CONSTRAINT `fk_username` FOREIGN KEY (`LOGIN`) REFERENCES `clients` (`LOGIN`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
