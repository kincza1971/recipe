CREATE TABLE `recipes` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`recipe_name` VARCHAR(255) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
	`recipe_type` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
	`recipe_desc` VARCHAR(255) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
	`prep_time` TIME NOT NULL,
	`cook_time` TIME NOT NULL,
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb3_hungarian_ci'
ENGINE=InnoDB;

CREATE TABLE `ingredients` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`recipe_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0',
	`ing_name` VARCHAR(255) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
	`qty` DOUBLE NOT NULL,
	`unit` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `fk_recipe` (`recipe_id`) USING BTREE,
	CONSTRAINT `fk_recipe` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8mb3_hungarian_ci'
ENGINE=InnoDB
;


CREATE TABLE `directions` (
	`dir_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`recipe_id` BIGINT(20) UNSIGNED NOT NULL,
	`pos` INT(10) UNSIGNED NOT NULL DEFAULT '0',
	`direction` INT(10) UNSIGNED NOT NULL DEFAULT '0',
	PRIMARY KEY (`dir_id`) USING BTREE,
	INDEX `FK1_directions` (`recipe_id`) USING BTREE,
	CONSTRAINT `FK1_directions` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8mb3_hungarian_ci'
ENGINE=InnoDB
;
