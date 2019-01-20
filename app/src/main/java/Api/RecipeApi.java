package Api;

import com.example.sirius.rs.GetModelRecipe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecipeApi {
    @GET("/recipe/{name}")
    Call<GetModelRecipe> getData(@Path("name") String resourceName);
}