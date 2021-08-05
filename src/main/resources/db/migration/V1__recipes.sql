CREATE TABLE `recipes` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`recipe_name` VARCHAR(255) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
	`recipe_type` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
	`recipe_desc` VARCHAR(255)  COLLATE 'utf8mb3_hungarian_ci',
	`prep_time` TIME,
	`cook_time` TIME,
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb3_hungarian_ci'
ENGINE=InnoDB;
