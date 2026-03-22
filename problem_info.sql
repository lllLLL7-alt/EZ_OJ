CREATE TABLE `problem_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `problem_num` varchar(50) NOT NULL COMMENT '题目编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `problem_name` varchar(200) NOT NULL COMMENT '题目名称',
  `problem_description` text COMMENT '题目描述',
  `compile_cmd` varchar(200) DEFAULT 'g++ %s -o %s -O2' COMMENT '编译指令，默认c++',
  `time_limit` bigint NOT NULL DEFAULT 1000 COMMENT '时间限制（ms），默认1秒',
  `memory_limit` bigint NOT NULL DEFAULT 256 COMMENT '内存限制（MB），默认256MB',
  `test_case_json` text COMMENT '测试点配置（JSON格式）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_problem_num` (`problem_num`) COMMENT '题目编号唯一索引，避免重复'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='OJ系统题目表';