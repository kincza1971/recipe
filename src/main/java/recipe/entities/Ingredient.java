package recipe.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingredients")
public class Ingredient {

    public enum MeasurementUnit {GRAM, KG, TABLESPOON, TEASPOON, COFFEE_SPOON, CUPFUL, PIECE, PIECES}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Recipe recipe;

    @Column(name = "ing_name")
    private String name;

    @Column(name = "qty")
    private double quantity;

    @Column(name = "unit")
    @Enumerated(value = EnumType.STRING)
    private MeasurementUnit unit;

    public Ingredient(Recipe recipe, String name, double quantity, MeasurementUnit unit) {
        this.recipe = recipe;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
}

//CREATE TABLE `ingredients` (
//	`id` BIGINT NOT NULL AUTO_INCREMENT,
//	`recipe_id` BIGINT UNSIGNED NOT NULL DEFAULT 0,
//	`ing_name` VARCHAR(255) NOT NULL,
//	`qty` DOUBLE NOT NULL,
//	`unit` VARCHAR(50) NOT NULL,
//	PRIMARY KEY (`id`),
//	CONSTRAINT `fk_recipe` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
//)
//COLLATE='utf8mb3_hungarian_ci'
//;
