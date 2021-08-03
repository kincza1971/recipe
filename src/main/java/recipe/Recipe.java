package recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="recipes")
public class Recipe {

    public enum RecipeType {SOUP, DISH, DESSERT}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="recipe_name")
    private String name;

    @Column(name = "recipe_type")
    @Enumerated(value = EnumType.STRING)
    private RecipeType type;

    @Column(name="prep_time")
    private LocalTime preparationTime;

    @Column(name = "cook_time")
    private LocalTime cooking_time;
}

//	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
//	`recipe_name` VARCHAR(255) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
//	`recipe_type` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
//	`recipe_desc` VARCHAR(255) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
//	`prep_time` TIME NOT NULL,
//	`cook_time` TIME NOT NULL,
