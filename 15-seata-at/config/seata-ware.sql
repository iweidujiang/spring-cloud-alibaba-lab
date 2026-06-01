-- seata-ware 库初始化脚本

CREATE TABLE IF NOT EXISTS `t_ware`
(
    `id`          BIGINT   NOT NULL AUTO_INCREMENT,
    `sku_id`      BIGINT   NOT NULL,
    `stock`       INT      NOT NULL,
    `create_time` DATETIME NOT NULL,
    `update_time` DATETIME NOT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_sku_id` (`sku_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `t_ware` (`sku_id`, `stock`, `create_time`, `update_time`)
VALUES (10086, 1000, NOW(), NOW());

CREATE TABLE IF NOT EXISTS `undo_log`
(
    `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `branch_id`     BIGINT(20)   NOT NULL,
    `xid`           VARCHAR(100) NOT NULL,
    `context`       VARCHAR(128) NOT NULL,
    `rollback_info` LONGBLOB     NOT NULL,
    `log_status`    INT(11)      NOT NULL,
    `log_created`   DATETIME     NOT NULL,
    `log_modified`  DATETIME     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
