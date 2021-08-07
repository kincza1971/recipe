package recipe.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipe.commands.CreateRecipeCommand;
import recipe.entities.Recipe;
import recipe.entities.RecipeDTO;
import recipe.repos.RecipeRepository;
import recipe.commands.UpdateRecipeCommand;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeService {
    private RecipeRepository repository;
    private ModelMapper modelMapper;


    public List<RecipeDTO> getRecipes() {

        List<Recipe> recipes= repository.findAll();
        return recipes.stream().map(r -> modelMapper.map(r, RecipeDTO.class)).toList();
    }


    public RecipeDTO saveRecipe(CreateRecipeCommand command) {
        Recipe recipe = new Recipe(
                command.getName(),
                command.getType(),
                command.getDescription(),
                command.getPreparationTime(),
                command.getCookingTime()
        );
        repository.save(recipe);
        return modelMapper.map(recipe, RecipeDTO.class);
    }

    @Transactional
    public RecipeDTO updateRecipeById(UpdateRecipeCommand command, long id) {
        Recipe recipe= repository.
                findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find recipe with this id: " +id));

        if (!command.getDescription().isBlank() && !recipe.getName().equals(command.getDescription())) {
            recipe.setDescription(command.getDescription());
        }

        if (command.getCookingTime() != null && !recipe.getCookingTime().equals(command.getCookingTime())) {
            recipe.setCookingTime(command.getCookingTime());
        }

        if (command.getPreparationTime() != null && !recipe.getPreparationTime().equals(command.getPreparationTime())) {
            recipe.setPreparationTime(command.getPreparationTime());
        }
        return modelMapper.map(recipe, RecipeDTO.class);
    }

    @Transactional
    public void deleteRecipeById(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteAllRecipe() {
        repository.deleteAll();
    }

    public RecipeDTO getRecipeById(long id) {
        Recipe recipe = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find recipe with this id: " + id));
        return modelMapper.map(recipe,RecipeDTO.class);
    }
}
