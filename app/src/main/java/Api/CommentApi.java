package Api;

import com.example.sirius.rs.GetModelRecipe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CommentApi {
    @GET("/recipe/{id}/Comment")
    Call<GetModelRecipe> getData(@Path("id") String resourceName);

}