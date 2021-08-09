package recipe.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipe.commands.CreateIngredientCommand;
import recipe.commands.UpdateIngredientCommand;
import recipe.entities.Ingredient;
import recipe.entities.IngredientDTO;
import recipe.entities.Recipe;
import recipe.repos.IngredientsRepository;
import recipe.repos.RecipeRepository;

import java.util.List;


@Service
@Data
@AllArgsConstructor
public class IngredientsService {

    private RecipeRepository recipeRepository;

    private IngredientsRepository ingredientsRepository;

    private ModelMapper modelMapper;


    @Transactional
    public IngredientDTO saveIngredient(long recipeId, CreateIngredientCommand command) {
        Recipe recipe = recipeRepository
                .findById(recipeId).orElseThrow(() -> new IllegalArgumentException("Cannot find recipe with this id: " + recipeId));
        Ingredient ingredient = new Ingredient(recipe, command.getName(), command.getQuantity(), command.getUnit());
        ingredientsRepository.save(ingredient);
        return modelMapper.map(ingredient, IngredientDTO.class);
    }

    @Transactional
    public void deleteAllIngredients() {
        ingredientsRepository.deleteAll();
    }

    @Transactional
    public void deleteAllIngredientsByRecipe(long recipeId) throws IllegalArgumentException {
        Recipe recipe = recipeRepository
                .findById(recipeId).orElseThrow(() -> new IllegalArgumentException("Cannot find recipe with this id: " + recipeId));
        ingredientsRepository.deleteByRecipeId(recipe.getId());
    }

    @Transactional
    public IngredientDTO updateIngredientById(long id, UpdateIngredientCommand command) throws IllegalArgumentException{
        Ingredient ingredient = ingredientsRepository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find ingredient with this id: " + id));
        if (command.getName() != null && !ingredient.getName().equals(command.getName())) {
            ingredient.setName(command.getName());
        }

        ingredient.setQuantity(command.getQuantity());
        if (command.getUnit() != null && !ingredient.getUnit().equals(command.getUnit())) {
            ingredient.setUnit(command.getUnit());
        }
        return  modelMapper.map(ingredient, IngredientDTO.class);
    }

    @Transactional
    public void deleteIngredientById(long id) {
        ingredientsRepository.deleteById(id);
    }

    public List<IngredientDTO> findIngredientsByRecipe(long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new IllegalArgumentException("Cannot find recipe with this id: " + recipeId));
        List<Ingredient> ingredients = ingredientsRepository.findIngredientByRecipe(recipe);
        return ingredients.stream().map(i -> modelMapper.map(i, IngredientDTO.class)).toList();
    }
}
