package recipe.commands;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateDirectionCommand {
    @NotBlank(message = "Text of direction cannot be empty, please give a direction")
    @Schema(description = "Text od direction", example = "Süssük mindkét oldalát 5 percig, forró sütőlapon, kevés zsiradékkal")
    private String directionText;

}
