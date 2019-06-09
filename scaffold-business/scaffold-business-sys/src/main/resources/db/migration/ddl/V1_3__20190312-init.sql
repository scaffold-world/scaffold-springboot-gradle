DROP TABLE IF EXISTS `gjj_sys_i18n`;
CREATE TABLE `gjj_sys_i18n` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `model` varchar(32) NOT NULL COMMENT '模块',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `text` varchar(256) NOT NULL COMMENT '内容',
  `zh_CH` varchar(256) DEFAULT NULL COMMENT '中文内容',
  `en_US` varchar(256) DEFAULT NULL COMMENT '英文内容',
  `id_ID` varchar(256) DEFAULT NULL COMMENT '印尼内容',
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='国际化翻译表';