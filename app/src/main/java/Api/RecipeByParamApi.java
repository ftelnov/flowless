package Api;

import com.example.sirius.rs.FRBody;
import com.example.sirius.rs.GetModelCategory;
import com.example.sirius.rs.GetModelRecipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeByParamApi {
    @POST("/filter")
    Call<List<GetModelCategory>> getData(@Body FRBody body, @Query("name") String resourceName,
                                         @Query("calories_min") int calories_min,
                                         @Query("calories_max") int calories_max,
                                         @Query("fats_min") int fats_min,
                                         @Query("fats_max") int fats_max,
                                         @Query("proteins_min") int proteins_min,
                                         @Query("proteins_max") int proteins_max,
                                         @Query("carbohyd_min") int calrbonydrates_min,
                                         @Query("carbohyd_max") int calrbonydrates_max,
                                         @Query("alergen") boolean flag);

}