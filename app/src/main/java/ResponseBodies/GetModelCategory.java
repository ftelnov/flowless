
package ResponseBodies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetModelCategory {

    @SerializedName("Recipe_id")
    @Expose
    public Integer recipeId;
    @SerializedName("Recipe_title")
    @Expose
    public String recipeTitle;
    @SerializedName("Time_of_cooking")
    @Expose
    public Integer timeOfCooking;
    @SerializedName("Recipe_image")
    @Expose
    public String recipeImage;


}
