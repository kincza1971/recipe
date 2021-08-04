package recipe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipe.commands.CreateRecipeCommand;
import recipe.entities.Recipe;
import recipe.RecipeRepository;
import recipe.commands.UpdateRecipeCommand;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository repository;

    public List<Recipe> getRecipes() {
        return repository.findAll();
    }

    public Recipe saveRecipe(CreateRecipeCommand command) {
        Recipe recipe = new Recipe(
                command.getName(),
                command.getType(),
                command.getDescription(),
                command.getPreparationTime(),
                command.getCookingTime()
        );
        repository.save(recipe);
        return recipe;
    }

    @Transactional
    public Recipe updateRecipeById(UpdateRecipeCommand command, long id) {
        Recipe recipe= repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find recipe withthis id: " +id));

        if (!command.getDescription().isBlank() && !recipe.getName().equals(command.getDescription())) {
            recipe.setDescription(command.getDescription());
        }

        if (command.getCookingTime() != null && !recipe.getCookingTime().equals(command.getCookingTime())) {
            recipe.setCookingTime(command.getCookingTime());
        }

        if (command.getPreparationTime() != null && !recipe.getPreparationTime().equals(command.getPreparationTime())) {
            recipe.setPreparationTime(command.getPreparationTime());
        }
        return recipe;
    }

    @Transactional
    public void deleteRecipeById(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteAllRecipe() {
        repository.deleteAll();
    }
}
