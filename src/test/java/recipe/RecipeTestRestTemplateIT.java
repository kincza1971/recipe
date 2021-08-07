package recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import recipe.commands.*;
import recipe.entities.Ingredient;
import recipe.entities.Recipe;
import recipe.entities.RecipeDTO;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from recipes")
public class RecipeTestRestTemplateIT {

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

    UpdateRecipeCommand UpdateRecipeCommand1;
    UpdateRecipeCommand UpdateRecipeCommand2;
    UpdateRecipeCommand UpdateRecipeCommand3;

    UpdateDirectionCommand UpdateDirectionCommand1;
    UpdateDirectionCommand UpdateDirectionCommand2;
    UpdateDirectionCommand UpdateDirectionCommand3;

    UpdateIngredientCommand UpdateIngredientCommand1;
    UpdateIngredientCommand UpdateIngredientCommand2;
    UpdateIngredientCommand UpdateIngredientCommand3;

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

        createIngredientCommand1 = new CreateIngredientCommand("Alma",1.0, Ingredient.MeasurementUnit.KG);
        createIngredientCommand1 = new CreateIngredientCommand("Banán",1200, Ingredient.MeasurementUnit.GRAM);
        createIngredientCommand1 = new CreateIngredientCommand("Fokhagyma",2.5, Ingredient.MeasurementUnit.TABLESPOON);

    }

    @Test
    void createRecipeTest() {
        RecipeDTO recipe1 = template.postForObject("/api/recipes/",createRecipeCommand1, RecipeDTO.class);
        assertEquals("Borsóleves",recipe1.getName());
    }

    @Test
    void findRecipeTest() {
        RecipeDTO recipe1 = template.postForObject("/api/recipes/",createRecipeCommand2, RecipeDTO.class);
        RecipeDTO recipeDTO = template.getForObject("/api/recipes/"+recipe1.getId(),RecipeDTO.class);
        assertEquals(recipe1.getName(),recipeDTO.getName());
    }

    @Test
    void PutRecipeTest() {
        RecipeDTO recipe1 = template.postForObject("/api/recipes/",createRecipeCommand3, RecipeDTO.class);
        template.put("/api/recipes/"+recipe1.getId(),updateRecipeCommand2);
        RecipeDTO recipeDTO = template.getForObject("/api/recipes/"+recipe1.getId(),RecipeDTO.class);
        assertEquals("Description changed 2",recipeDTO.getDescription());
    }

    @Test
    void deleteByIdTest() {
        RecipeDTO recipe1 = template.postForObject("/api/recipes/",createRecipeCommand2, RecipeDTO.class);
        RecipeDTO recipe2 = template.postForObject("/api/recipes/",createRecipeCommand1, RecipeDTO.class);
        template.delete("/api/recipes/"+recipe1.getId());
        List<RecipeDTO> result= template.getForObject("/api/recipes/"+recipe1.getId(),List.class);
        assertEquals(result.size(),1);
    }

}
