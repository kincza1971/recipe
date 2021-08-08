
CREATE TABLE `directions` (
                              `dir_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                              `recipe_id` BIGINT(20) UNSIGNED NOT NULL,
                              `direction`VARCHAR(255) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
                              PRIMARY KEY (`dir_id`) USING BTREE,
                              INDEX `FK1_directions` (`recipe_id`) USING BTREE,
                              CONSTRAINT `FK1_directions` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
    COLLATE='utf8mb3_hungarian_ci'
ENGINE=InnoDB
;
