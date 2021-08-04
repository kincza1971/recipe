package recipe.controllers;

import org.springframework.web.bind.annotation.*;
import recipe.commands.CreateIngredientCommand;
import recipe.commands.UpdateIngredientCommand;
import recipe.entities.Ingredient;
import recipe.services.IngredientsService;

@RestController
@RequestMapping("/api/recipes")
public class IngredientsController {

    private IngredientsService service;

    public IngredientsController(IngredientsService service) {
        this.service = service;
    }

    @PostMapping("/{recipeid}/ingredients")
    public Ingredient saveIngredient(@PathVariable(name = "recipeid") long recipeId, @RequestBody CreateIngredientCommand command) {
        return service.saveIngredient(recipeId, command);
    }

    @DeleteMapping("/ingredients")
    public void deleteAllIngredients() {
        service.deleteAllIngredients();
    }

    @DeleteMapping("/{recipeId}/ingredients")
    public void deleteAllIngredientsByRecipe(@PathVariable long recipeId) {
        service.deleteAllIngredientsByRecipe(recipeId);
    }

    @PutMapping("/ingredients/{id}")
    public Ingredient updateIngredientById(@PathVariable long id, @RequestBody UpdateIngredientCommand command) {
        return service.updateIngredientById(id, command);
    };



}

