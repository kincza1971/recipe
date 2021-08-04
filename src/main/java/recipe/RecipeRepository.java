package recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe.entities.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
}
