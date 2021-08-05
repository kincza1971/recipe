package recipe.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import recipe.entities.Direction;
import recipe.entities.Recipe;

import java.util.List;

public interface DirectionsRepository extends JpaRepository<Direction, Long> {
    void deleteDirectionByRecipe(Recipe recipe);

    List<Direction> findDirectionByRecipe(Recipe recipe);
}
