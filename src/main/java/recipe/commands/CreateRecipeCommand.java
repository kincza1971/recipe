package recipe.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipe.entities.Recipe;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateRecipeCommand {

    @NotBlank(message = "Name cannot be empty please fill it")
    private String name;

    @NotBlank(message = "Type cannot be empty")
    private Recipe.RecipeType type;

    private String description;

    private LocalTime preparationTime;

    private LocalTime cookingTime;

}
