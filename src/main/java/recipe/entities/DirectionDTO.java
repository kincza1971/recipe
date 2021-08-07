package recipe.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectionDTO {

    @JsonBackReference
    private Recipe recipe;
    private long id;
    private int pos;
    private String directionText;

}