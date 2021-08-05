package recipe.controllers;

import org.springframework.web.bind.annotation.*;
import recipe.commands.CreateIngredientCommand;
import recipe.commands.UpdateIngredientCommand;
import recipe.entities.Ingredient;
import recipe.entities.IngredientDTO;
import recipe.services.IngredientsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class IngredientsController {

    private IngredientsService service;

    public IngredientsController(IngredientsService service) {
        this.service = service;
    }

    @GetMapping("/{recipeid}/ingredients")
    public List<IngredientDTO> getInngredientsByRecipe(@PathVariable (name = "recipeid") long recipeId) {
        return service.findIngredientsByRecipe(recipeId);
    }

    @PostMapping("/{recipeid}/ingredients")
    public IngredientDTO saveIngredient(@PathVariable(name = "recipeid") long recipeId, @Valid @RequestBody CreateIngredientCommand command) {
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

    @DeleteMapping("/ingredients/{id}")
    public void deleteIngredientById(@PathVariable long id) {
        service.deleteIngredientById(id);
    }

    @PutMapping("/ingredients/{id}")
    public IngredientDTO updateIngredientById(@PathVariable long id, @RequestBody UpdateIngredientCommand command) {
        return service.updateIngredientById(id, command);
    }



}

