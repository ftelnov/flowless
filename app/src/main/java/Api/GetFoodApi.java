package Api;
import com.example.sirius.rs.GetModelFood;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface GetFoodApi {
    @POST("/food")
    Call<List<GetModelFood>> getAllFood();
}