    -- https://liuyanzhao.com/7431.html
    SET NAMES utf8;
    SET FOREIGN_KEY_CHECKS = 0;
    -- ----------------------------
    --  Table structure for `authority`
    -- ----------------------------
    DROP TABLE IF EXISTS `authority`;
    CREATE TABLE `authority` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(255) NOT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
    -- ----------------------------
    --  Records of `authority`
    -- ----------------------------
    BEGIN;
    INSERT INTO `authority` VALUES ('1', 'ROLE_ADMIN'), ('2', 'ROLE_USER');
    COMMIT;
    -- ----------------------------
    --  Table structure for `user`
    -- ----------------------------
    DROP TABLE IF EXISTS `user`;
    CREATE TABLE `user` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT,
      `username` varchar(20) NOT NULL,
      `password` varchar(100) NOT NULL,
      `name` varchar(20) NOT NULL,
      `email` varchar(50) NOT NULL,
      `avatar` varchar(200) DEFAULT NULL,
      `create_time` datetime DEFAULT NULL,
      `last_login_time` datetime DEFAULT NULL,
      `status` varchar(10) DEFAULT NULL,
      PRIMARY KEY (`id`),
      UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
      UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
    -- ----------------------------
    --  Records of `user`
    -- ----------------------------
    BEGIN;
    INSERT INTO `user` VALUES ('1', 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '管理员', 'admin@liuyanzhao.com', null, null, null, 'normal'), ('2', 'saysky', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '言曌', '847064370@qq.com', null, null, null, 'normal'), ('3', 'lockuser', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '锁定账号', 'locked@qq.com', null, null, null, 'locked');
    COMMIT;
    -- ----------------------------
    --  Table structure for `user_authority`
    -- ----------------------------
    DROP TABLE IF EXISTS `user_authority`;
    CREATE TABLE `user_authority` (
      `user_id` bigint(20) NOT NULL,
      `authority_id` bigint(20) NOT NULL,
      KEY `FKgvxjs381k6f48d5d2yi11uh89` (`authority_id`),
      KEY `FKpqlsjpkybgos9w2svcri7j8xy` (`user_id`),
      CONSTRAINT `FKgvxjs381k6f48d5d2yi11uh89` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`),
      CONSTRAINT `FKpqlsjpkybgos9w2svcri7j8xy` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    -- ----------------------------
    --  Records of `user_authority`
    -- ----------------------------
    BEGIN;
    INSERT INTO `user_authority` VALUES ('1', '1'), ('2', '2'), ('1', '2');
    COMMIT;
    SET FOREIGN_KEY_CHECKS = 1;
