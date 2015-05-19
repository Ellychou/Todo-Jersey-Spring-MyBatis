CREATE TABLE `event` (
  `event_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `description` varchar(254) DEFAULT NULL,
  `done_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `user_id` int(10) unsigned DEFAULT NULL,
  `is_done` int(11) NOT NULL,
  `is_notify` int(11) NOT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `salt` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uniqueEmail` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `token` (
  `user_id` int(10) unsigned DEFAULT NULL,
  `token` varchar(50) NOT NULL,
  `timeStamp` date DEFAULT NULL,
  `expire` int(10) unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8