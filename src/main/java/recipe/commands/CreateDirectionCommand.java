package recipe.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipe.entities.Recipe;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDirectionCommand {

    private String directionText;

}
