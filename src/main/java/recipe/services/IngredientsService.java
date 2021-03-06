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
import recipe.exceptions.EntityNotFoundException;
import recipe.repos.IngredientsRepository;
import recipe.repos.RecipeRepository;

import java.util.List;


@Service
@Data
@AllArgsConstructor
public class IngredientsService {

    public static final String RECIPE_URI = "Recipe/Not-found";
    public static final String INGREDIENT_URI = "Recipe/Ingredient/Not-found";
    public static final String RECIPE_NOT_FOUND = "Cannot find recipe with this id: ";
    public static final String INGREDIENT_NOT_FOUND = "Cannot find ingredient with this id: ";
    private RecipeRepository recipeRepository;

    private IngredientsRepository ingredientsRepository;

    private ModelMapper modelMapper;


    @Transactional
    public IngredientDTO saveIngredient(long recipeId, CreateIngredientCommand command) {
        Recipe recipe = recipeRepository
                .findById(recipeId).orElseThrow(() -> new EntityNotFoundException(RECIPE_URI, RECIPE_NOT_FOUND + recipeId));
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
                .findById(recipeId).orElseThrow(() -> new EntityNotFoundException(RECIPE_URI,RECIPE_NOT_FOUND + recipeId));
        ingredientsRepository.deleteByRecipeId(recipe.getId());
    }

    @Transactional
    public IngredientDTO updateIngredientById(long id, UpdateIngredientCommand command) throws IllegalArgumentException{
        Ingredient ingredient = ingredientsRepository
                .findById(id).orElseThrow(() ->new EntityNotFoundException(INGREDIENT_URI, INGREDIENT_NOT_FOUND + id));
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
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new EntityNotFoundException(RECIPE_URI, RECIPE_NOT_FOUND + recipeId));
        List<Ingredient> ingredients = ingredientsRepository.findIngredientByRecipe(recipe);
        return ingredients.stream().map(i -> modelMapper.map(i, IngredientDTO.class)).toList();
    }
}
