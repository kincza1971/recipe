package recipe.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDirectionCommand {
    @NotBlank(message = "Direction text cannot be empty")
    private String direction;
}
