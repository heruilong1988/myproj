CREATE TABLE `order_depth` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`bid_price` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`bid_amount` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`ask_price` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`ask_amount` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`time` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;
