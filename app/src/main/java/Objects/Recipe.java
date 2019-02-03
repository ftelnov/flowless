package Objects;

import java.io.Serializable;

public class Recipe implements Serializable {
    private Integer id;
    private String title;
    private Integer time_of_cooking;
    private Integer recipe_type;
    private Integer calories;
    private Integer proteins;
    private Integer fats;
    private Integer carbohydrates;
    private String recipe_ingredients;
    private Integer portions;
    private String recipe_image;
    private Integer user_id;
    private String recipe;


    public Recipe(Integer Id, String title, Integer time_of_cooking, Integer recipe_type,
                  Integer calories, Integer proteins, Integer fats, String recipe_ingredients,
                  Integer portions, String recipe_image, Integer user_id, Integer carbohydrates, String recipe) {
        this.id = Id;
        this.title = title;
        this.time_of_cooking = time_of_cooking;
        this.recipe_type = recipe_type;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.recipe_ingredients = recipe_ingredients;
        this.portions = portions;
        this.recipe_image = recipe_image;
        this.user_id = user_id;
        this.carbohydrates = carbohydrates;
        this.recipe = recipe;
    }

    public Integer getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getTime_of_cooking() {
        return this.time_of_cooking;
    }

    public Integer getRecipe_type() {
        return this.recipe_type;
    }

    public Integer getCalories() {
        return this.calories;
    }

    public Integer getProteins() {
        return this.proteins;
    }

    public Integer getFats() {
        return this.fats;
    }

    public String getRecipe_ingredients() {
        return this.recipe_ingredients;
    }

    public Integer getPortions() {
        return this.portions;
    }

    public String getRecipe_image() {
        return this.recipe_image;
    }

    public Integer getUser_id() {
        return this.user_id;
    }

    public Integer getCarbohydrates() {
        return this.carbohydrates;
    }

    public String getRecipe(){
        return this.recipe;
    }

    //set

    public void setId(Integer id) {
        this.id = id;
    }
}
