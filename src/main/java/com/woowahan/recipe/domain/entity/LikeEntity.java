package com.woowahan.recipe.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipe;

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;

        // 무한루프에 빠지지 않도록 체크
        if(!recipe.getLikeList().contains(this)) {
            recipe.getLikeList().add(this);
        }
    }

    public static LikeEntity of(UserEntity user, RecipeEntity recipe) {
        return LikeEntity.builder()
                .user(user)
                .recipe(recipe)
                .build();
    }
}
