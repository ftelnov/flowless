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
    private String recipe_ingredients;
    private Integer portions;
    private String recipe_image;
    private Integer user_id;


    public Recipe(Integer Id, String title, Integer time_of_cooking, Integer recipe_type,
                  Integer calories, Integer proteins, Integer fats, String recipe_ingredients,
                  Integer portions, String recipe_image, Integer user_id) {
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
    }
    public Integer getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public Integer getTime_of_cooking(){
        return this.time_of_cooking;
    }
    public Integer getRecipe_type(){
        return this.recipe_type;
    }
    public Integer getCalories(){
        return this.calories;
    }
    public Integer getProteins(){
        return this.proteins;
    }
    public Integer getFats(){
        return this.fats;
    }
    public String getRecipe_ingredients(){
        return this.recipe_ingredients;
    }
    public Integer getPortions(){
        return this.portions;
    }
    public String getRecipe_image(){
        return this.recipe_image;
    }
    public Integer getUser_id(){
        return this.user_id;
    }

    //set

    public void setId(Integer id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setTime_of_cooking(Integer time){
        this.time_of_cooking = time;
    }
    public void setRecipe_type(Integer type){
        this.recipe_type = type;
    }
    public void setCalories(Integer cal){
        this.calories = cal;
    }
    public void setProteins(Integer proteins){
        this.proteins = proteins;
    }
    public void setFats(Integer fats){
        this.fats = fats;
    }
    public void setRecipe_ingredients(String ingredients){
        this.recipe_ingredients = ingredients;
    }
    public void setPortions(Integer portions){
        this.portions = portions;
    }
    public void setRecipe_image(String root){
        this.recipe_image = root;
    }
    public void setUser_id(Integer id){
        this.user_id = id;
    }
}
