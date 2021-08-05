package recipe.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipe.entities.Direction;
import recipe.entities.Ingredient;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {


    public enum RecipeType {SOUP, DISH, DESSERT}

    private String name;

    private RecipeType type;

    private String description;

    private LocalTime preparationTime;

    private LocalTime cookingTime;

    private List<Ingredient> ingredients;

    private List<Direction> directions;

}
