#--------------数据库操作 开始---------------
#删除数据库
drop DATABASE IF EXISTS webapp;
#新建数据库
create DATABASE webapp;
#使用数据库webapp
use webapp;
#--------------数据库操作 结束---------------

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `name` varchar(20) DEFAULT 'no-name',
                           `age` int default 0,
                           `gender` varchar(20) default 'xxs',
                           `createTime` datetime DEFAULT NULL,
                           `loginTime` datetime DEFAULT NULL,
                           `email` varchar(20) default '',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

INSERT INTO `teacher` VALUES ('1', 'lld', 20, 'xxs', '2017-06-22 13:54:15', '2017-06-22 13:54:15', '1111@qq.com');
INSERT INTO `teacher` VALUES ('2', 'lla', 20, 'xxs', '2017-06-22 13:54:15', '2017-06-22 13:54:15', '1111@qq.com');
INSERT INTO `teacher` VALUES ('3', 'llb', 20, 'xxs', '2017-06-22 13:54:15', '2017-06-22 13:54:15', '1111@qq.com');

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(20) DEFAULT 'no-name',
                        `age` int default 0,
                        `email` varchar(20) default '',
                        `loginTime` datetime DEFAULT NULL,
                        `teacherId` int(11) NOT NULL,
                        `gender` varchar(20) default 'xxs',
                        PRIMARY KEY (`id`),
                        FOREIGN KEY (`teacherId`) references `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES ('1', 'kkd', 20, '1111@qq.com', '2017-06-22 13:54:15', '1', 'xxs');
INSERT INTO `user` VALUES ('2', 'kka', 20, '1111@qq.com', '2017-06-22 13:54:15', '2', 'xxs');
INSERT INTO `user` VALUES ('3', 'kkb', 20, '1111@qq.com', '2017-06-22 13:54:15', '3', 'xxs');
INSERT INTO `user` VALUES ('4', 'kkc', 20, '1111@qq.com', '2017-06-22 13:54:15', '1', 'xxs');