package recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import recipe.commands.*;
import recipe.entities.*;

import java.net.URI;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from recipes")

public class DirectionsTestRestTemplateIT {

    public static final String API_MAP = "/api/recipes/";
    @Autowired
    TestRestTemplate template;

    CreateRecipeCommand createRecipeCommand1;
    CreateRecipeCommand createRecipeCommand2;
    CreateRecipeCommand createRecipeCommand3;

    UpdateRecipeCommand updateRecipeCommand1;
    UpdateRecipeCommand updateRecipeCommand2;
    UpdateRecipeCommand updateRecipeCommand3;

    CreateDirectionCommand createDirectionCommand1;
    CreateDirectionCommand createDirectionCommand2;
    CreateDirectionCommand createDirectionCommand3;

    CreateIngredientCommand createIngredientCommand1;
    CreateIngredientCommand createIngredientCommand2;
    CreateIngredientCommand createIngredientCommand3;

    UpdateDirectionCommand updateDirectionCommand1;
    UpdateDirectionCommand updateDirectionCommand2;
    UpdateDirectionCommand updateDirectionCommand3;

    UpdateIngredientCommand updateIngredientCommand1;
    UpdateIngredientCommand updateIngredientCommand2;
    UpdateIngredientCommand updateIngredientCommand3;

    @BeforeEach
    void init() {
        createRecipeCommand1 = new CreateRecipeCommand("Borsóleves",
                Recipe.RecipeType.SOUP, "Könnyű nyári leves",
                LocalTime.of(0,30),
                LocalTime.of(1,30)
        );
        createRecipeCommand2 = new CreateRecipeCommand("Almaleves",
                Recipe.RecipeType.SOUP, "Könnyű nyári leves",
                LocalTime.of(1,0),
                LocalTime.of(2,30)
        );
        createRecipeCommand3 = new CreateRecipeCommand("Marhapöri",
                Recipe.RecipeType.SOUP, "Bográcsos egytálétel",
                LocalTime.of(1,30),
                LocalTime.of(3,30)
        );


        updateRecipeCommand1 = new UpdateRecipeCommand("Description changed 1",
                LocalTime.of(0,30),
                LocalTime.of(1,30)
        );
        updateRecipeCommand2 = new UpdateRecipeCommand("Description changed 2",
                LocalTime.of(1,0),
                LocalTime.of(2,30)
        );
        updateRecipeCommand3 = new UpdateRecipeCommand("Description changed 3",
                LocalTime.of(1,30),
                LocalTime.of(3,30)
        );

        createDirectionCommand1 = new CreateDirectionCommand("Forró serpenyőben pirítjuk fehéredésig");
        createDirectionCommand2 = new CreateDirectionCommand("Lassan pároljuk, szükség esetén kevés folyadékot pótolunk");
        createDirectionCommand3 = new CreateDirectionCommand("A húst apró kockákra vágjuk");

        updateDirectionCommand1 = new UpdateDirectionCommand("Forró serpenyőben pirítjuk fehéredésig - updated");
        updateDirectionCommand2 = new UpdateDirectionCommand("Lassan pároljuk, szükség esetén kevés folyadékot pótolunk - updated");
        updateDirectionCommand3 = new UpdateDirectionCommand("A húst apró kockákra vágjuk - updated");

        createIngredientCommand1 = new CreateIngredientCommand("Alma",1.0, Ingredient.MeasurementUnit.KG);
        createIngredientCommand2 = new CreateIngredientCommand("Banán",1200, Ingredient.MeasurementUnit.GRAM);
        createIngredientCommand3 = new CreateIngredientCommand("Fokhagyma",2.5, Ingredient.MeasurementUnit.TABLESPOON);

        updateIngredientCommand1 = new UpdateIngredientCommand("Alma - updated",1.0, Ingredient.MeasurementUnit.KG);
        updateIngredientCommand2 = new UpdateIngredientCommand("Banán - updated",1200, Ingredient.MeasurementUnit.GRAM);
        updateIngredientCommand3 = new UpdateIngredientCommand("Fokhagyma - updated",2.5, Ingredient.MeasurementUnit.TABLESPOON);

    }


    @Test
    void SaveAndGetDirectionTest() {
        RecipeDTO recipe1 = template.postForObject(API_MAP,createRecipeCommand2, RecipeDTO.class);

        DirectionDTO directionDTO = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand1, DirectionDTO.class);
        DirectionDTO directionDTO2 = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand2, DirectionDTO.class);
        DirectionDTO directionDTO3 = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand1, DirectionDTO.class);
        DirectionDTO directionDTO4 = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand2, DirectionDTO.class);

        RecipeDTO recipeDTO = template.getForObject(API_MAP+recipe1.getId(),RecipeDTO.class);
        System.out.println(recipeDTO);

        assertEquals(createDirectionCommand1.getDirectionText(), recipeDTO.getDirections().get(0).getDirectionText());
        assertEquals(createDirectionCommand2.getDirectionText(), recipeDTO.getDirections().get(1).getDirectionText());

    }


    @Test
    void SaveAndGetDirectionsByRecipeTest() {
        RecipeDTO recipe1 = template.postForObject(API_MAP,createRecipeCommand2, RecipeDTO.class);

        DirectionDTO directionDTO = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand1, DirectionDTO.class);
        DirectionDTO directionDTO2 = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand2, DirectionDTO.class);

        List<DirectionDTO> directionDTOs =
                template.exchange(API_MAP+ recipe1.getId()+"/directions",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<DirectionDTO>>() {}
                ).getBody();
        List<String> directions = directionDTOs.stream().map(DirectionDTO::getDirectionText).collect(Collectors.toList());

        assertEquals(directions,List.of("Forró serpenyőben pirítjuk fehéredésig", "Lassan pároljuk, szükség esetén kevés folyadékot pótolunk"));

    }

    @Test
    void directionUpdateTest() {
        RecipeDTO recipe1 = template.postForObject(API_MAP,createRecipeCommand2, RecipeDTO.class);

        DirectionDTO directionDTO = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand1, DirectionDTO.class);
        DirectionDTO directionDTO2 = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand2, DirectionDTO.class);

        template.put(API_MAP  + "directions/" + directionDTO.getId(), updateDirectionCommand1);
        template.put(API_MAP +  "directions/" + directionDTO2.getId(), updateDirectionCommand2);

        List<DirectionDTO> directionDTOs =
                template.exchange(API_MAP+ recipe1.getId()+"/directions",HttpMethod.GET, null,new ParameterizedTypeReference<List<DirectionDTO>>() {}
                ).getBody();
        List<String> directions = directionDTOs.stream().map(DirectionDTO::getDirectionText).collect(Collectors.toList());

        assertEquals(List.of("Forró serpenyőben pirítjuk fehéredésig - updated", "Lassan pároljuk, szükség esetén kevés folyadékot pótolunk - updated"), directions);

    }

    @Test
    void deleteDirectionByIdTest() {
        RecipeDTO recipe1 = template.postForObject(API_MAP,createRecipeCommand2, RecipeDTO.class);

        DirectionDTO directionDTO = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand1, DirectionDTO.class);
        DirectionDTO directionDTO2 = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand2, DirectionDTO.class);

        template.delete(API_MAP + "directions/" + directionDTO.getId());

        RecipeDTO recipeDTO = template.getForObject(API_MAP+recipe1.getId(),RecipeDTO.class);

        assertEquals(recipeDTO.getDirections().size(),1);

    }
    @Test

    void deleteDirectionByRecipe() {
        RecipeDTO recipe1 = template.postForObject(API_MAP,createRecipeCommand2, RecipeDTO.class);

        DirectionDTO directionDTO = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand1, DirectionDTO.class);
        DirectionDTO directionDTO2 = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand2, DirectionDTO.class);

        template.delete(API_MAP +  recipe1.getId()+ "/directions/");

        RecipeDTO recipeDTO = template.getForObject(API_MAP+recipe1.getId(),RecipeDTO.class);
        System.out.println(recipeDTO);

        assertEquals(0,recipeDTO.getDirections().size());

    }

    @Test
    void deleteAllDirections() {
        RecipeDTO recipe1 = template.postForObject(API_MAP,createRecipeCommand2, RecipeDTO.class);

        DirectionDTO directionDTO = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand1, DirectionDTO.class);
        DirectionDTO directionDTO2 = template.postForObject(API_MAP + recipe1.getId() + "/directions",createDirectionCommand2, DirectionDTO.class);

        template.delete(API_MAP + "/directions/");

        RecipeDTO recipeDTO = template.getForObject(API_MAP+recipe1.getId(),RecipeDTO.class);
        System.out.println(recipeDTO);

        assertEquals(0,recipeDTO.getDirections().size());

    }


}
