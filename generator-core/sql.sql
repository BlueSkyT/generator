
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for meta_column
-- ----------------------------
CREATE TABLE `meta_column`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `tableId` int NOT NULL,
  `tableName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表名',
  `
chinese` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '中文名称',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字段名',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型',
  `length` int NULL DEFAULT NULL COMMENT '长度',
  `nullable` tinyint NULL DEFAULT NULL COMMENT '可为空',
  `charset` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字符集',
  `def` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '默认值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meta_column
-- ----------------------------
INSERT INTO `meta_column` VALUES (1, 1, 'testa', 'id', 'id', 'int', NULL, 0, 'UTF-8', NULL);
INSERT INTO `meta_column` VALUES (2, 1, 'testa', '中文名', 'cn', 'varchar', 200, 1, 'UTF-8', NULL);
INSERT INTO `meta_column` VALUES (3, 2, 'testb', '中文名', 'name', 'varchar', 200, 1, 'UTF-8', NULL);
INSERT INTO `meta_column` VALUES (4, 2, 'testb', '用户名', 'username', 'varchar', 200, 1, 'UTF-8', NULL);

-- ----------------------------
-- Table structure for meta_config
-- ----------------------------
CREATE TABLE `meta_config`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meta_config
-- ----------------------------
INSERT INTO `meta_config` VALUES (2, '啊的', '的额', '哇哇哇哇');

-- ----------------------------
-- Table structure for meta_table
-- ----------------------------
CREATE TABLE `meta_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `chinese` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '中文名',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `charset` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meta_table
-- ----------------------------
INSERT INTO `meta_table` VALUES (1, '测试1', 'testa', 'UTF-8');
INSERT INTO `meta_table` VALUES (2, '测试2', 'testb', 'UTF-8');

SET FOREIGN_KEY_CHECKS = 1;
