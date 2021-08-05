CREATE TABLE `ingredients` (
                               `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                               `recipe_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0',
                               `ing_name` VARCHAR(255) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
                               `qty` DOUBLE,
                               `unit` VARCHAR(50) COLLATE 'utf8mb3_hungarian_ci',
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `fk_recipe` (`recipe_id`) USING BTREE,
                               CONSTRAINT `fk_recipe` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
    COLLATE='utf8mb3_hungarian_ci'
ENGINE=InnoDB
;