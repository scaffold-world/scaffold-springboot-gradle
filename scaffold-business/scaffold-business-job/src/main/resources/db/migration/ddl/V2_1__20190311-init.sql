CREATE TABLE `gjj_job_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(50) DEFAULT NULL COMMENT '任务分组',
  `job_description` varchar(50) DEFAULT NULL COMMENT '任务描述',
  `start_withrun` smallint(2) DEFAULT '0' COMMENT '是否随着程序启动自动启动任务 0否 1是',
  `job_status` smallint(2) DEFAULT '1' COMMENT '1正在运行 0已经停止',
  `cron_expression` varchar(20) DEFAULT NULL COMMENT 'cron表达式',
  `bean_class` varchar(150) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `spring_id` varchar(20) DEFAULT NULL COMMENT 'Spring bean 名',
  `method_name` varchar(35) DEFAULT NULL,
  `param_json` varchar(255) DEFAULT NULL COMMENT '方法执行需要的参数，配置为json',
  `is_concurrent` smallint(2) DEFAULT NULL COMMENT '任务是否可以并发(一个还没完就执行下一个） 1可以 0不可以',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;