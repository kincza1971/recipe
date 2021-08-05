package recipe.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe.entities.Ingredient;
import recipe.entities.Recipe;

import java.util.List;

public interface IngredientsRepository extends JpaRepository<Ingredient, Long> {
    void deleteByRecipe(Recipe recipe);

    List<Ingredient> findIngredientByRecipe(Recipe recipe);
}
