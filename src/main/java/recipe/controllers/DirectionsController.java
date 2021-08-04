package recipe.controllers;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import recipe.commands.CreateDirectionCommand;
import recipe.commands.UpdateDirectionCommand;
import recipe.commands.UpdateIngredientCommand;
import recipe.entities.Direction;
import recipe.entities.Ingredient;
import recipe.services.DirectionsService;

@RestController
@RequestMapping("/api/recipes")
public class DirectionsController {

    private DirectionsService service;

    public DirectionsController(DirectionsService service) {
        this.service = service;
    }

    @PostMapping("/{recipeid}/directions")
    public Direction saveIngredient(@PathVariable(name = "recipeid") long recipeId, @RequestBody CreateDirectionCommand command) {
        return service.saveDirection(recipeId, command);
    }

    @DeleteMapping("/directions")
    public void deleteAllDirections() {
        service.deleteAllDirections();
    }

    @DeleteMapping("/{recipeId}/directions")
    public void deleteAllIngredientsByRecipe(@PathVariable long recipeId) {
        service.deleteAllDirectionsByRecipe(recipeId);
    }

    @PutMapping("/directions/{id}")
    public Direction updateIngredientById(@PathVariable long id, @RequestBody UpdateDirectionCommand command) {
        return service.updateDirectionById(id, command);
    };



}
