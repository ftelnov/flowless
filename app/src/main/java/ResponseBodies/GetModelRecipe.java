
package ResponseBodies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetModelRecipe {

    @SerializedName("Recipe_id")
    @Expose
    public Integer recipeId;
    @SerializedName("Recipe_title")
    @Expose
    public String recipeTitle;
    @SerializedName("Time_of_cooking")
    @Expose
    public Integer timeOfCooking;
    @SerializedName("Recipe_type")
    @Expose
    public Integer recipeType;
    @SerializedName("Calories")
    @Expose
    public Integer calories;
    @SerializedName("Proteins")
    @Expose
    public Integer proteins;
    @SerializedName("Fats")
    @Expose
    public Integer fats;
    @SerializedName("Carbohydrates")
    @Expose
    public Integer carbohydrates;
    @SerializedName("Recipe_ingredients")
    @Expose
    public String recipeIngredients;
    @SerializedName("Portions")
    @Expose
    public Integer portions;
    @SerializedName("Recipe")
    @Expose
    public String recipe;
    @SerializedName("Recipe_image")
    @Expose
    public String recipeImage;
    @SerializedName("User_id")
    @Expose
    public Integer userId;

}
