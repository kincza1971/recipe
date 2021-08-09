package recipe.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipe.entities.Direction;
import recipe.entities.Ingredient;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="recipes")
public class Recipe {


    public Recipe(String name, RecipeType type, String description, LocalTime preparationTime, LocalTime cookingTime) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.preparationTime = preparationTime;
        this.cookingTime = cookingTime;
    }

    public enum RecipeType {SOUP, DISH, DESSERT}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="recipe_name", nullable = false)
    private String name;

    @Column(name = "recipe_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RecipeType type;

    @Column(name = "recipe_desc")
    private String description;

    @Column(name="prep_time")
    private LocalTime preparationTime;

    @Column(name = "cook_time")
    private LocalTime cookingTime;

    @OneToMany(
            mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Ingredient> ingredients;

    @OneToMany(
            mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<Direction> directions;

}

//	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
//	`recipe_name` VARCHAR(255) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
//	`recipe_type` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
//	`recipe_desc` VARCHAR(255) NOT NULL COLLATE 'utf8mb3_hungarian_ci',
//	`prep_time` TIME NOT NULL,
//	`cook_time` TIME NOT NULL,
