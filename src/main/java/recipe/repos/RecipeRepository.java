package recipe.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import recipe.entities.Recipe;
import recipe.services.IngredientsService;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {

}
