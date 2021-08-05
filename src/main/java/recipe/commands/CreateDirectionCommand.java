package recipe.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateDirectionCommand {
    @NotBlank(message = "Text of direction cannot be empty, please give a direction")
    private String directionText;

}
