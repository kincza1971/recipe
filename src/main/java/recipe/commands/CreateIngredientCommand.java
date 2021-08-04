package recipe.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipe.entities.Ingredient;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateIngredientCommand {

    private String name;

    private double quantity;

    private Ingredient.MeasurementUnit unit;
}

//CREATE TABLE `ingredients` (
//	`id` BIGINT NOT NULL AUTO_INCREMENT,
//	`recipe_id` BIGINT UNSIGNED NOT NULL DEFAULT 0,
//	`ing_name` VARCHAR(255) NOT NULL,
//	`qty` DOUBLE NOT NULL,
//	`unit` VARCHAR(50) NOT NULL,
//	PRIMARY KEY (`id`),
