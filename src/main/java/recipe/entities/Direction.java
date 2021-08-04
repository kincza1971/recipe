package recipe.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "directions")
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dir_id")
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Recipe recipe;

    @Column(name = "pos")
    private int pos;

    @Column(name = "direction")
    private String directionText;

    public Direction(Recipe recipe, String directionText) {
        this.recipe = recipe;
        this.directionText = directionText;
    }
}
//CREATE TABLE `directions` (
//	`dir_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
//	`recipe_id` BIGINT(20) UNSIGNED NOT NULL,
//	`pos` INT(10) UNSIGNED NOT NULL DEFAULT '0',
//	`direction` INT(10) UNSIGNED NOT NULL DEFAULT '0',
//	PRIMARY KEY (`dir_id`) USING BTREE,
//	INDEX `FK1_directions` (`recipe_id`) USING BTREE,
//	CONSTRAINT `FK1_directions` FOREIGN KEY (`recipe_id`) REFERENCES `recipe`.`recipes` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
//)
//COLLATE='utf8mb3_hungarian_ci'
//ENGINE=InnoDB
//;
