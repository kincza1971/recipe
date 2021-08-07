package recipe.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {

    public enum MeasurementUnit {GRAM, KG, TABLESPOON, TEASPOON, COFFEE_SPOON, CUPFUL, PIECE, PIECES}

    @ToStringExclude
    @JsonBackReference
    private Recipe recipe;
    private long id;
    private String name;
    private double quantity;
    private MeasurementUnit unit;

}
