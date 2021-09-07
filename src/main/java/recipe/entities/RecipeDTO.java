package recipe.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {


    public enum RecipeType {SOUP, DISH, DESSERT}

    private long id;

    private String name;

    private RecipeType type;

    private String description;

    private LocalTime preparationTime;

    private LocalTime cookingTime;

    private List<IngredientDTO> ingredients;

    private List<DirectionDTO> directions;

}
