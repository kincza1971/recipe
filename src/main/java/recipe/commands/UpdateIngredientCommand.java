package recipe.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipe.entities.Ingredient;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateIngredientCommand {

    private String name;

    private double quantity;

    private Ingredient.MeasurementUnit unit;
}
