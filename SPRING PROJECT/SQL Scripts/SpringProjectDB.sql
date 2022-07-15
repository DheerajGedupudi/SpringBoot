USE `spring_project_db`;

#Table 1 = "userdetails" 

DROP TABLE IF EXISTS `userdetails`;
CREATE TABLE `userdetails` (
  `user_detail_id` int(11) NOT NULL auto_increment,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  primary key (`user_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `userdetails` 
VALUES 
('1','Suresh', 'Kumar', 'suresh@gmail.com'),
('2','Ramesh', 'Gupta', 'ramesh@gmail.com'),
('3','Mahesh', 'Rao', 'mahesh@gmail.com');

#Table 2 = "users" 

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `user_detail_id` int(11) not null,
  PRIMARY KEY (`username`),
  foreign key (`user_detail_id`) references `userdetails` (`user_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` 
VALUES 
('Suresh','{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW',1,1),
('Ramesh','{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW',1,2),
('Mahesh','{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW',1,3);

#Table 3 = "authorities" 

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) not null,
  `authority` varchar(50) NOT NULL,
  unique KEY (`username`,`authority`),
  FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `authorities` 
VALUES 
('Suresh','ROLE_EMPLOYEE'),
('Ramesh','ROLE_EMPLOYEE'),
('Mahesh','ROLE_EMPLOYEE'),
('Mahesh','ROLE_MANAGER');

#Table 4 = "tickets" 

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE `tickets` (
  `ticket_id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_name` varchar(50) NOT NULL,
  `assigned_by` varchar(50) Not Null,
  `assigned_to` varchar(50),
  `status` varchar(50),
  primary key (`ticket_id`),
  foreign key (`assigned_by`) references `users` (`username`),
  foreign key (`assigned_to`) references `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `tickets` 
VALUES 
('1','Learn Java', 'Mahesh', 'Suresh','To Do'),
('2','Learn MySQL', 'Mahesh', 'Suresh','To Do'),
('3','Learn Hibernate', 'Mahesh', 'Ramesh','Done');
