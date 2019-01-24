package Api;

import com.example.sirius.rs.FRBody;
import com.example.sirius.rs.GetModelFood;
import com.example.sirius.rs.GetModelRecipe;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserAllergensApi {
    @POST("/user/allergens")
    Call<List<GetModelFood>> getData(@Body FRBody flex);
}