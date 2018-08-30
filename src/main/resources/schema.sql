CREATE TABLE IF NOT EXISTS `Person` (
    `id` bigint(20) NOT NULL auto_increment,
    `first_name` varchar(200) NOT NULL,
    `last_name` varchar(200) NOT NULL,
    `phone` varchar(200) NOT NULL,
    `email` varchar(200) NOT NULL,
    `birth_date` TIMESTAMP, 
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `User` (
    `id` bigint(20) NOT NULL auto_increment,
    `username` varchar(200) NOT NULL,
    `password` varchar(200) NOT NULL,
    `fullname` varchar(200) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Expenses` (
    `id` bigint(20) NOT NULL auto_increment,
    `description` varchar(200) NOT NULL,
    `spentby` bigint(20) NOT NULL,
    `spentdate` TIMESTAMP, 
    `amount`  DECIMAL(20, 2) NOT NULL,
    PRIMARY KEY (`id`)
);