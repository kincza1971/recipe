package recipe.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipe.commands.CreateIngredientCommand;
import recipe.commands.UpdateIngredientCommand;
import recipe.entities.Ingredient;
import recipe.entities.Recipe;
import recipe.repos.IngredientsRepository;
import recipe.repos.RecipeRepository;


@Service
@Data
@AllArgsConstructor
public class IngredientsService {

    private RecipeRepository recipeRepository;

    private IngredientsRepository ingredientsRepository;


    @Transactional
    public Ingredient saveIngredient(long recipeId, CreateIngredientCommand command) {
        Recipe recipe = recipeRepository
                .findById(recipeId).orElseThrow(() -> new IllegalArgumentException("Cannot find recipe with this id: " + recipeId));
        Ingredient ingredient = new Ingredient(recipe, command.getName(), command.getQuantity(), command.getUnit());
        ingredientsRepository.save(ingredient);
        return ingredient;
    }

    @Transactional
    public void deleteAllIngredients() {
        ingredientsRepository.deleteAll();
    }

    @Transactional
    public void deleteAllIngredientsByRecipe(long recipeId) {
        Recipe recipe = recipeRepository
                .findById(recipeId).orElseThrow(() -> new IllegalArgumentException("Cannot find recipe with this id: " + recipeId));
        ingredientsRepository.deleteByRecipe(recipe);
    }

    @Transactional
    public Ingredient updateIngredientById(long id, UpdateIngredientCommand command) {
        Ingredient ingredient = ingredientsRepository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find ingredient with this id: " + id));
        if (!command.getName().isBlank() && !ingredient.getName().equals(command.getName())) {
            ingredient.setName(command.getName());
        }
        if (!command.getName().isBlank() && !ingredient.getName().equals(command.getName())) {
            ingredient.setName(command.getName());
        }
        if (!command.getName().isBlank() && !ingredient.getName().equals(command.getName())) {
            ingredient.setName(command.getName());
        }
        return ingredient;
    }
}
