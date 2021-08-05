package recipe.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectionDTO {

    private Recipe recipe;
    private int pos;
    private String directionText;

}