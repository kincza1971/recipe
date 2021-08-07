package recipe;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import recipe.controllers.EntityNotFoundExceptionHandler;

@SpringBootApplication
public class RecipesApplication {

    @Bean
    ModelMapper modelMapper () {
        return new ModelMapper();
    }

    @Bean
    EntityNotFoundExceptionHandler handler() {
        return new EntityNotFoundExceptionHandler();
    }

    public static void main(String[] args) {
        SpringApplication.run(RecipesApplication.class, args);
    }


}
