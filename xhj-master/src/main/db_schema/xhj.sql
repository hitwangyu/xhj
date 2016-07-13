DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `imgIds`varchar(100) NOT NULL,
  `classId` int NOT NULL,
  `price` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `status` int NOT NULL default 0,
  `introduction` varchar(200) NOT NULL,
  `description` text NOT NULL,
  `create_time` datetime NOT NULL default now(),
  PRIMARY KEY (`id`),
  KEY `index_classId` (`classId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_product_class`;
CREATE TABLE `tb_product_class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `parentId` int NOT NULL,
  `depth` int NOT NULL,
  `path` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL default now(),
  PRIMARY KEY (`id`),
  KEY `index_depth` (`depth`),
  KEY `index_path` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_img`;
CREATE TABLE `tb_img` (
  `id` int NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(100) NOT NULL,
  `link_addr` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL default now(),
  `type` int not null,
  `weight` int not null default 0,
  `description` varchar(20),
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_type_weight` (`type`,`weight`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE `tb_news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `author` varchar(20) NOT NULL,
  `type` int NOT NULL,
  `content` text not NULL,
  `status` int NOT NULL default 0,
  `create_time` datetime NOT NULL default now(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_ini`;
CREATE TABLE `tb_ini` (
  `ini_key` varchar(50) NOT NULL,
  `ini_value` varchar(200) NOT NULL,
  `expire_time` datetime,
  `description` varchar(50),
  PRIMARY KEY (`ini_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


