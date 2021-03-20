CREATE DATABASE `WeChatMini`;

USE `WeChatMini`;

DROP TABLE IF EXISTS `user`;
-- //用户表
CREATE TABLE `user` (
  `id` int(11) NOT NULL COMMENT 'id',
  `nickname` varchar(30) DEFAULT NULL COMMENT '昵称',
  `username` varchar (30) NOT null ,
  `initialLetter` varchar (2) NOT NULL,
  `contact` int ,
  `modifyNicknameTimestamp` int ,
  `modifyInitialLetterTimestamp` int,
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `signature` varchar (50) COMMENT '个性签名',
  `avatar` varchar (100) comment '头像',
  `background` varchar (100) comment '背景'
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- //好友表
drop table if exists `contact`;
create table `contact`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `friendId` int(11) NOT NULL ,
  `contact` int(11) comment '黑名单',
  primary key (`id`),
  foreign key (friendId) references user(id) on delete cascade on update cascade,
  foreign key (userId) references user(id) on delete cascade on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- //朋友圈发布时间表
drop table if exists `timeline`;
create table `timeline`(
  `id` int not null AUTO_INCREMENT,
  `userId` int not null,
  `momentId` bigint not null,
  `timestamp` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  primary key (`id`),
  foreign key (userId) references user(id) on delete cascade on update cascade,
  foreign key (momentId) references moment(id) on delete cascade on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `moment`;
-- //朋友圈表
create table `moment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT  'id',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `content` varchar (200) comment '朋友圈内容',
  `userid` int (11) not null comment '用户id',
  `imageNum` int,
  `img1` varchar (100),
  `img2` varchar (100),
  `img3` varchar (100),
  `img4` varchar (100),
  `img5` varchar (100),
  `img6` varchar (100),
  `img7` varchar (100),
  `img8` varchar (100),
  `img9` varchar (100),
  primary key (`id`),
  foreign key (userid) references user(id) on delete cascade on update cascade

)ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table `praise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `praiseUserNickname` varchar (30) NOT null ,
  `praiseUserId` int (11) not null ,
  `momentId` bigint(20) NOT NULL,
  primary key (`id`),
   foreign key (momentId) references moment(id) on delete cascade on update cascade
)ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
