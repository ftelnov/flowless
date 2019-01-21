package Api;

import com.example.sirius.rs.GetModelCategory;
import com.example.sirius.rs.GetModelRecipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeByParamApi {
    @GET("/filter")
    Call<List<GetModelCategory>> getData(@Query("name") String resourceName,
                                         @Query("calories_min") int calories_min,
                                         @Query("calories_max") int calories_max,
                                         @Query("fats_min") int fats_min,
                                         @Query("fats_max") int fats_max,
                                         @Query("proteins_min") int proteins_min,
                                         @Query("proteins_max") int proteins_max,
                                         @Query("calrbonydrates_min") int calrbonydrates_min,
                                         @Query("calrbonydrates_max") int calrbonydrates_max,
                                         @Query("alergen") boolean flag);

}