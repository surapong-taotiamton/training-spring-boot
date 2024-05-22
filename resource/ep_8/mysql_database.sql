CREATE TABLE `book` (
  `bookId` bigint NOT NULL AUTO_INCREMENT,
  `bookName` varchar(255) DEFAULT NULL,
  `createAt` datetime(6) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `updateAt` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;