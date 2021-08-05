package recipe.controllers;

import org.springframework.web.bind.annotation.*;
import recipe.commands.CreateDirectionCommand;
import recipe.commands.UpdateDirectionCommand;
import recipe.entities.Direction;
import recipe.entities.DirectionDTO;
import recipe.services.DirectionsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class DirectionsController {

    private DirectionsService service;

    public DirectionsController(DirectionsService service) {
        this.service = service;
    }

    @GetMapping("/{recipeid}/directions")
    public List<DirectionDTO> getDiretionsByRecipeId(@PathVariable(name = "recipeid") long recipeId) {
        return service.findDirectionsByRecipeId(recipeId);
    }

    @PostMapping("/{recipeid}/directions")
    public DirectionDTO saveIngredient(@Valid @RequestBody CreateDirectionCommand command, @PathVariable(name = "recipeid") long recipeId) {
        return service.saveDirection(recipeId, command);
    }

    @DeleteMapping("/directions")
    public void deleteAllDirections() {
        service.deleteAllDirections();
    }

    @DeleteMapping("/{recipeid}/directions")
    public void deleteAllIngredientsByRecipe(@PathVariable(name = "recipeid") long recipeId) {
        service.deleteAllDirectionsByRecipe(recipeId);
    }

    @PutMapping("/directions/{id}")
    public DirectionDTO updateIngredientById(@PathVariable long id, @RequestBody UpdateDirectionCommand command) {
        return service.updateDirectionById(id, command);
    }



}
