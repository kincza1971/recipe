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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from recipes")
public class IngredientsTestRestTemplateIT {

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
    void saveAndGetIngredientTest() throws NullPointerException{
        RecipeDTO recipe1 = template.postForObject(API_MAP,createRecipeCommand3, RecipeDTO.class);

        String url = API_MAP + recipe1.getId()+"/ingredients/";

        IngredientDTO ingredientDTO = template.postForObject(url,createIngredientCommand1,IngredientDTO.class);
        IngredientDTO ingredientDTO2 = template.postForObject(url,createIngredientCommand2,IngredientDTO.class);

        List<IngredientDTO> ingredientDTOS = template.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<IngredientDTO>>() {}
        ).getBody();

        assertEquals(2,ingredientDTOS.size());

    }

    @Test
    void updateIngredientTest() throws NullPointerException{
        RecipeDTO recipe1 = template.postForObject(API_MAP,createRecipeCommand3, RecipeDTO.class);

        String url = API_MAP + recipe1.getId()+"/ingredients/";

        IngredientDTO ingredientDTO = template.postForObject(url,createIngredientCommand1,IngredientDTO.class);
        IngredientDTO ingredientDTO2 = template.postForObject(url,createIngredientCommand2,IngredientDTO.class);

        url = API_MAP + "/ingredients/"+ingredientDTO.getId();
        template.put(url,updateIngredientCommand1,IngredientDTO.class);

        url = API_MAP + "/ingredients/"+ingredientDTO2.getId();
        template.put(url,updateIngredientCommand2,IngredientDTO.class);

        url = API_MAP + recipe1.getId() + "/ingredients/";
        List<IngredientDTO> ingredientDTOS = template.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<IngredientDTO>>() {}
        ).getBody();

        assertEquals(2,ingredientDTOS.size());
        List<String> strings = ingredientDTOS.stream().map(IngredientDTO::getName).toList();
        assertEquals(List.of("Alma - updated", "Banán - updated"), strings);

    }

    @Test
    void deleteIngredientsByRecipe() throws NullPointerException{
        RecipeDTO recipe1 = template.postForObject(API_MAP, createRecipeCommand3, RecipeDTO.class);

        String url = API_MAP + recipe1.getId() + "/ingredients/";

        template.postForObject(url, createIngredientCommand1, IngredientDTO.class);
        template.postForObject(url, createIngredientCommand2, IngredientDTO.class);

        url = API_MAP + recipe1.getId() + "/ingredients/";
        template.delete(url);

        List<IngredientDTO> ingredientDTOS = template.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<IngredientDTO>>() {
                }
        ).getBody();

        assertEquals(0, ingredientDTOS.size());
    }

    @Test
    void deleteIngredientsByRecipeNotExits() {

        String url = API_MAP + 0 + "/ingredients/";

        System.out.println(template.exchange(URI.create(url),
                HttpMethod.DELETE,
                null,
                Problem.class).getBody());
        //assertEquals("Recipe/Not-found", problem.getDetail());
    }


    @Test
    void deleteAllIngredients() throws NullPointerException{
        RecipeDTO recipe1 = template.postForObject(API_MAP,createRecipeCommand3, RecipeDTO.class);

        String url = API_MAP + recipe1.getId()+"/ingredients/";

        template.postForObject(url,createIngredientCommand1,IngredientDTO.class);
        template.postForObject(url,createIngredientCommand2,IngredientDTO.class);

        url =API_MAP + "ingredients/";
        template.delete(url);

        url=API_MAP + recipe1.getId() + " /ingredients/";
        List<IngredientDTO> ingredientDTOS = template.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<IngredientDTO>>() {}
        ).getBody();

        assertEquals(0,ingredientDTOS.size());



    }

}