package recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import recipe.commands.CreateRecipeCommand;
import recipe.entities.Recipe;
import recipe.entities.RecipeDTO;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from recipes")
public class RecipeTestRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void createRecipeTest() {
        CreateRecipeCommand command =
                new CreateRecipeCommand("Borsóleves",
                        Recipe.RecipeType.SOUP, "Könnyű nyári leves",
                        LocalTime.of(0,30),
                        LocalTime.of(1,30)
                );
        RecipeDTO recipe1 = template.postForObject("/api/recipes/",command, RecipeDTO.class);

        assertEquals("Borsóleves",recipe1.getName());
//        command =
//                new CreateRecipeCommand("Almaleves",
//                        Recipe.RecipeType.SOUP, "Könnyű nyári leves",
//                        LocalTime.of(0,30),
//                        LocalTime.of(1,30);
//        template.postForObject("/api/recipes/",command, RecipeDTO.class);
//        command =
//                new CreateRecipeCommand("Paprikáskrumpli",
//                        Recipe.RecipeType.DISH, "egytálétel",
//                        LocalTime.of(0,30),
//                        LocalTime.of(1,30);
//        template.postForObject("/api/recipes/",command, RecipeDTO.class);


    }

}
