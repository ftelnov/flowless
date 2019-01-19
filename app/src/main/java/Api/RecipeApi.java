package Api;

import com.example.sirius.rs.GetModelCategory;
import com.example.sirius.rs.GetModelRecipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeApi {
    @GET("/recipe/{name}")
    Call<GetModelRecipe> getData(@Path("name") String resourceName);
}