package recipe.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipe.commands.CreateDirectionCommand;
import recipe.commands.UpdateDirectionCommand;
import recipe.entities.Direction;
import recipe.entities.DirectionDTO;
import recipe.entities.Recipe;
import recipe.exceptions.EntityNotFoundException;
import recipe.repos.DirectionsRepository;
import recipe.repos.RecipeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DirectionsService {
    public static final String RECIPE_NOT_FOUND = "Cannot find recipe with this id: ";
    public static final String DIRECTION_NOT_FOUND = "Cannot find direction by this id: ";
    public static final String RECIPE_URI = "Recipe/Not-found";
    public static final String DIRECTION_URI = "Recipe/Direction/Not-found";
    private DirectionsRepository repository;
    private RecipeRepository recipeRepository;
    private ModelMapper modelMapper;


    @Transactional
    public DirectionDTO saveDirection(long recipeId, CreateDirectionCommand command) {
        Recipe recipe = recipeRepository
                .findById(recipeId).orElseThrow(() -> new EntityNotFoundException(RECIPE_URI, RECIPE_NOT_FOUND + recipeId));
        Direction direction = new Direction(recipe, command.getDirectionText());
        repository.save(direction);
        return modelMapper.map(direction,DirectionDTO.class);
    }

    @Transactional
    public void deleteAllDirections() {
        repository.deleteAll();
    }

    @Transactional
    public void deleteAllDirectionsByRecipe(long recipeId) {
        Recipe recipe = recipeRepository
                .findById(recipeId).orElseThrow(() -> new EntityNotFoundException(RECIPE_URI, RECIPE_NOT_FOUND + recipeId));
        repository.deleteDirectionByRecipe(recipe);
    }

    @Transactional
    public DirectionDTO updateDirectionById(long id, UpdateDirectionCommand command) {
        System.out.println(command);
        Direction direction = repository
                .findById(id).orElseThrow(() -> new EntityNotFoundException(DIRECTION_URI, DIRECTION_NOT_FOUND + id));
        if (!command.getDirection().isBlank() && !command.getDirection().equals(direction.getDirectionText())) {
            direction.setDirectionText(command.getDirection());
        }
        return  modelMapper.map(direction,DirectionDTO.class);
    }

    public List<DirectionDTO> findDirectionsByRecipeId(long recipeId) {
        Recipe recipe = recipeRepository
                .findById(recipeId).orElseThrow(() -> new EntityNotFoundException(RECIPE_URI, RECIPE_NOT_FOUND + recipeId));
        List<Direction> directions = repository.findDirectionByRecipe(recipe);
        return directions.stream().map( d -> modelMapper.map(d,DirectionDTO.class)).toList();
    }

    @Transactional
    public void deleteDirectionById(long id) {
        repository.deleteById(id);
    }
}
