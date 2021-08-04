package recipe.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipe.commands.CreateDirectionCommand;
import recipe.commands.UpdateDirectionCommand;
import recipe.entities.Direction;
import recipe.entities.Recipe;
import recipe.repos.DirectionsRepository;
import recipe.repos.RecipeRepository;

@Service
@AllArgsConstructor
public class DirectionsService {
    private DirectionsRepository repository;
    private RecipeRepository recipeRepository;


    @Transactional
    public Direction saveDirection(long recipeId, CreateDirectionCommand command) {
        Recipe recipe = recipeRepository
                .findById(recipeId).orElseThrow(() -> new IllegalArgumentException("Cannot find recipe with this id: " + recipeId));
        Direction direction = new Direction(recipe, command.getDirectionText());
        recipe.addDirection(direction);
        System.out.println(direction);
        System.out.println(recipe);
        return repository.save(direction);
    }

    @Transactional
    public void deleteAllDirections() {
        repository.deleteAll();
    }

    @Transactional
    public void deleteAllDirectionsByRecipe(long recipeId) {
        Recipe recipe = recipeRepository
                .findById(recipeId).orElseThrow(() -> new IllegalArgumentException("Cannot find recipe with this id: " + recipeId));
        repository.deleteDirectionByRecipe(recipe);
    }

    @Transactional
    public Direction updateDirectionById(long id, UpdateDirectionCommand command) {
        Direction direction = repository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find direction by this id: " + id));
        if (command.getDirection().isBlank() && command.getDirection().equals(direction.getDirectionText())) {
            direction.setDirectionText(command.getDirection());
        }
        return direction;
    }
}
