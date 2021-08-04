package recipe.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipe.commands.CreateRecipeCommand;
import recipe.entities.Recipe;
import recipe.services.RecipeService;
import recipe.commands.UpdateRecipeCommand;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService service;

    @GetMapping
    public List<Recipe> getRecipes() {
        return service.getRecipes();
    }

    @PostMapping
    public Recipe saveRecipe(@RequestBody CreateRecipeCommand command) {
        return service.saveRecipe(command);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipeById(@PathVariable long id, @RequestBody UpdateRecipeCommand command) {
        return service.updateRecipeById(command, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipeById(@PathVariable long id) {
        service.deleteRecipeById(id);
    }

    @DeleteMapping
    public void deleteAllRecipe() {
        service.deleteAllRecipe();
    }

}