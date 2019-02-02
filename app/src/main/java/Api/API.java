package Api;

import java.util.List;

import ResponseBodies.AddToMenuBody;
import ResponseBodies.AuthBody;
import ResponseBodies.LoginContainer;

import ResponseBodies.GetMenuBody;
import ResponseBodies.GetModelCategory;
import ResponseBodies.GetModelFood;
import ResponseBodies.GetModelRecipe;
import ResponseBodies.RecipeParametersBody;
import ResponseBodies.RegisterBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    @POST("/food/{id}/allergen")
    Call<ResponseBody> addToAllergen(@Path("id") String resourceName, @Body LoginContainer flex);

    @POST("recipe/{id}/addtofav")
    Call<ResponseBody> addToFav(@Body LoginContainer registerBody, @Path("id") String flex);

    @POST("/user/menuadd/{time}")
    Call<ResponseBody> addToMenu(@Path("time") String timef, @Body AddToMenuBody registerBody);

    @POST("/auth")
    Call<ResponseBody> letsAuth(@Body AuthBody registrationBody);

    @GET("/category/{name}")
    Call<List<GetModelCategory>> getCategory(@Path("name") String resourceName);

    @GET("/recipe/{id}/Comment")
    Call<GetModelRecipe> getComment(@Path("id") String resourceName);

    @POST("recipe/{id}/deletefromfav")
    Call<ResponseBody> deleteFromFav(@Body LoginContainer registerBody, @Path("id") String flex);

    @POST("/food/{id}/delallergen")
    Call<ResponseBody> delAllergen(@Path("id") String resourceName, @Body LoginContainer flex);

    @GET("/user/getfavs")
    Call<List<GetModelCategory>> getFavouriteRecipes(@Body LoginContainer registerBody);

    @GET("/food")
    Call<List<GetModelFood>> getAllFood();

    @POST("/user/menu/{time}")
    Call<List<GetModelCategory>> getMenu(@Path("time") String time, @Body GetMenuBody addToMenuBody);

    @GET("/recipe/{name}")
    Call<GetModelRecipe> getRecipe(@Path("name") String resourceName);

    @POST("/filter")
    Call<List<GetModelCategory>> getRecipeByParameters(@Body RecipeParametersBody body);

    @POST("/register")
    Call<ResponseBody> letsRegister(@Body RegisterBody registerBody);

    @POST("/user/menudel/{meal}")
    Call<ResponseBody> deleteFromMenu(@Path("meal") String timef, @Body AddToMenuBody registerBody);

    @POST("/user/allergens")
    Call<List<GetModelFood>> getUserAllergens(@Body LoginContainer flex);
}
