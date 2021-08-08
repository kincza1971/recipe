package recipe.commands;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipe.entities.Recipe;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateRecipeCommand {

    @NotBlank(message = "Name cannot be empty please fill it")
    @Schema(description = "Name of food", example = "Kakaspörkölt")
    private String name;

    @NotNull(message = "Type cannot be empty")
    @Schema(description = "Type of food accepted(SOUP, DISH, DESSERT)", example = "DISH")
    private Recipe.RecipeType type;

    @Schema(description = "Short description of food", example = "Könnyű fogás paleo diétához")
    private String description;

    @Schema(description = "Estimated time of preparation eg: 2 hours and 30 minutes", implementation = String.class, example = "02:30:00")
    private LocalTime preparationTime;

    @Schema(description = "Estimated time of cooking eg: 1 hours and 30 minutes",implementation = String.class ,example = "01:30:00")
    private LocalTime cookingTime;

}
