package Api;

import com.example.sirius.rs.FRBody;
import com.example.sirius.rs.GetModelRecipe;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AddAllergenApi {
    @POST("/food/{id}/allergen")
    Call<ResponseBody> getData(@Path("id") String resourceName, @Body FRBody flex);
}