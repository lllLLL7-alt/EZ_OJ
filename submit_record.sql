
CREATE TABLE `submit_record` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键（对应实体类Integer类型）',
  `user_id` int DEFAULT NULL COMMENT '用户ID（预留多用户场景）',
  `problem_num` varchar(50) NOT NULL COMMENT '题目编号（如P1001）',
  `task_id` varchar(64) NOT NULL COMMENT '判题任务ID（关联JudgeTask的taskId）',
  `source_code` text COMMENT '用户提交的源代码（长文本存储）',
  `language` varchar(20) DEFAULT 'c++' COMMENT '提交语言（如c++、java）',
  `judge_status` varchar(30) DEFAULT 'WAITING' COMMENT '判题状态：WAITING/AC/WA/TLE/MLE/CE/RE/UNKNOWN',
  `judge_message` text COMMENT '判题详情（多测试点结果、错误日志等）',
  `used_time` bigint DEFAULT 0 COMMENT '程序总运行时间（单位：ms）',
  `used_memory` bigint DEFAULT 0 COMMENT '程序最大内存占用（单位：字节）',
  `score` int DEFAULT 0 COMMENT '最终得分（根据测试点计算）',
  `error_msg` varchar(500) DEFAULT '' COMMENT '核心错误信息（简化版，便于前端展示）',
  PRIMARY KEY (`id`),
  KEY `idx_problem_num` (`problem_num`) COMMENT '题目编号索引（快速筛选某题的提交记录）',
  KEY `idx_task_id` (`task_id`) COMMENT '任务ID索引（追溯判题任务）',
  KEY `idx_user_id` (`user_id`) COMMENT '用户ID索引（多用户场景筛选）',
  KEY `idx_judge_status` (`judge_status`) COMMENT '判题状态索引（筛选通过/未通过记录）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='OJ系统提交记录表';

ALTER TABLE `submit_record` 
ADD CONSTRAINT `fk_submit_record_problem_num` 
FOREIGN KEY (`problem_num`) REFERENCES `problem_info` (`problem_num`) 
ON DELETE RESTRICT 
ON UPDATE CASCADE;