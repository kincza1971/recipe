package recipe.commands;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipe.entities.Ingredient;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateIngredientCommand {

    @NotBlank(message = "Name cannot be blank")
    @Schema(description = "The name of ingredient", example = "Sütőtök")
    private String name;

    @Schema(description = "Quantity of ingredients", example = "1.5")
    private double quantity;

    @Schema(description = "Measurement unit accepted: (GRAM, KG, TABLESPOON, TEASPOON, COFFEE_SPOON, CUPFUL, PIECE, PIECES)"
    ,example = "KG")
    private Ingredient.MeasurementUnit unit;
}

//CREATE TABLE `ingredients` (
//	`id` BIGINT NOT NULL AUTO_INCREMENT,
//	`recipe_id` BIGINT UNSIGNED NOT NULL DEFAULT 0,
//	`ing_name` VARCHAR(255) NOT NULL,
//	`qty` DOUBLE NOT NULL,
//	`unit` VARCHAR(50) NOT NULL,
//	PRIMARY KEY (`id`),
