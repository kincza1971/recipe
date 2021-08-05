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
import recipe.repos.DirectionsRepository;
import recipe.repos.RecipeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DirectionsService {
    private DirectionsRepository repository;
    private RecipeRepository recipeRepository;
    private ModelMapper modelMapper;


    @Transactional
    public DirectionDTO saveDirection(long recipeId, CreateDirectionCommand command) {
        Recipe recipe = recipeRepository
                .findById(recipeId).orElseThrow(() -> new IllegalArgumentException("Cannot find recipe with this id: " + recipeId));
        Direction direction = new Direction(recipe, command.getDirectionText());
        recipe.addDirection(direction);
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
                .findById(recipeId).orElseThrow(() -> new IllegalArgumentException("Cannot find recipe with this id: " + recipeId));
        System.out.println(recipe);
        repository.deleteDirectionByRecipe(recipe);
    }

    @Transactional
    public DirectionDTO updateDirectionById(long id, UpdateDirectionCommand command) {
        Direction direction = repository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find direction by this id: " + id));
        if (!command.getDirection().isBlank() && !command.getDirection().equals(direction.getDirectionText())) {
            direction.setDirectionText(command.getDirection());
        }
        return  modelMapper.map(direction,DirectionDTO.class);
    }

    public List<DirectionDTO> findDirectionsByRecipeId(long recipeId) {
        Recipe recipe = recipeRepository
                .findById(recipeId).orElseThrow(() -> new IllegalArgumentException("Cannot find recipe with this id: " + recipeId));
        List<Direction> directions = repository.findDirectionByRecipe(recipe);
        return directions.stream().map( d -> modelMapper.map(d,DirectionDTO.class)).toList();
    }
}
