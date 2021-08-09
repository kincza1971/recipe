package recipe.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import recipe.commands.CreateDirectionCommand;
import recipe.commands.UpdateDirectionCommand;
import recipe.entities.DirectionDTO;
import recipe.services.DirectionsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@AllArgsConstructor
public class DirectionsController {

    private DirectionsService service;
    private EntityNotFoundExceptionHandler handler;


    @GetMapping("/{recipeid}/directions")
    public List<DirectionDTO> getDirectionsByRecipeId(@PathVariable(name = "recipeid") long recipeId) {
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

    @DeleteMapping("/directions/{id}")
    public void deleteAllDirections(@PathVariable long id) {

        service.deleteDirectionById(id);
    }

    @DeleteMapping("/{recipeid}/directions")
    public void deleteAllDirectionsByRecipe(@PathVariable(name = "recipeid") long recipeId) {
        service.deleteAllDirectionsByRecipe(recipeId);
    }

    @PutMapping("/directions/{id}")
    public DirectionDTO updateDirectionById(@PathVariable long id, @RequestBody UpdateDirectionCommand command) {
        return service.updateDirectionById(id, command);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
        return handler.handleNotFound(iae,"/recipe/entity-not-found", "Entity Not Found");
    }




}
