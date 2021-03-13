CREATE DATABASE `WeChatMini`;

USE `WeChatMini`;

DROP TABLE IF EXISTS `user`;
-- //用户表
CREATE TABLE `user` (
  `id` int(11) NOT NULL COMMENT 'id',
  `nickname` varchar(30) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `signature` varchar (50) COMMENT '个性签名',
--   `avatar` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `moment`;
-- //朋友圈表
create table `moment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT  'id',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `content` varchar (200) comment '朋友圈内容',
  `userid` int (11) not null comment '用户id',

  primary key (`id`),
  foreign key (userid) references user(id) on delete cascade on update cascade

)ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
