package recipe.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UpdateRecipeCommand {

    private String description;

    private LocalTime preparationTime;

    private LocalTime cookingTime;

}
