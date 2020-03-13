CREATE TABLE `zglu_test` (
  `id` int NOT NULL AUTO_INCREMENT,
  `int_null` int DEFAULT NULL COMMENT '整数可空',
  `int_not_null` int NOT NULL COMMENT '整数不为空',
  `int_default` int DEFAULT '1' COMMENT '整数默认1',
  `string_null` varchar(255) DEFAULT NULL COMMENT '字符窜可空',
  `string_not_null` varchar(255) NOT NULL COMMENT '字符窜不为空',
  `string_default` varchar(255) DEFAULT 'a' COMMENT '字符窜默认a',
  `bolean_null` bit(1) DEFAULT NULL COMMENT '布尔可空',
  `bolean_not_null` bit(1) NOT NULL COMMENT '布尔不为空',
  `bolean_default` bit(1) DEFAULT b'1' COMMENT '布尔默认true',
  `decimal_null` double DEFAULT NULL COMMENT '小数可空',
  `decimal_not_null` double NOT NULL COMMENT '小数不为空',
  `decimal_default` double DEFAULT '1.11' COMMENT '小数默认1.11',
  `date_null` datetime DEFAULT NULL COMMENT '日期可空',
  `date_not_null` datetime NOT NULL COMMENT '日期不为空',
  `date_default` datetime DEFAULT '1989-04-25 00:00:00' COMMENT '日期默认89年',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='测试';