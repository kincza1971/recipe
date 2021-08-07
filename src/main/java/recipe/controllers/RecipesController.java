package recipe.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import recipe.commands.CreateRecipeCommand;
import recipe.entities.RecipeDTO;
import recipe.services.RecipeService;
import recipe.commands.UpdateRecipeCommand;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@AllArgsConstructor
public class RecipesController {

    private RecipeService service;
    private EntityNotFoundExceptionHandler handler;

    @GetMapping
    public List<RecipeDTO> getRecipes() {
        return service.getRecipes();
    }
    @GetMapping("/{id}")
    public RecipeDTO getRecipesById(@PathVariable long id) {
        return service.getRecipeById(id);
    }

    @PostMapping
    public RecipeDTO saveRecipe(@Valid @RequestBody CreateRecipeCommand command) {
        return service.saveRecipe(command);
    }

    @PutMapping("/{id}")
    public RecipeDTO updateRecipeById(@PathVariable long id, @RequestBody UpdateRecipeCommand command) {
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

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
        return handler.handleNotFound(iae,"/recipe/entity-not-found", "Entity Not Found");
    }


}
