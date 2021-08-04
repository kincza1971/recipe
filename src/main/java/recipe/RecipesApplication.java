package recipe;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecipesApplication {

    @Bean
    ModelMapper modelMapper () {
        return new ModelMapper();
    }

    @Bean
    ObjectMapper objectMapper () {
        return new ObjectMapper(){}.findAndRegisterModules();
    }
    public static void main(String[] args) {
        SpringApplication.run(RecipesApplication.class, args);
    }

}
