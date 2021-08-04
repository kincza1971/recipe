package recipe.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipe.entities.Recipe;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateRecipeCommand {

    private String name;

    private Recipe.RecipeType type;

    private String description;

    private LocalTime preparationTime;

    private LocalTime cookingTime;

}
