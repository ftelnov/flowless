package Api;

import com.example.sirius.rs.GetModelCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryApi {
    @GET("/category/{name}")
    Call<List<GetModelCategory>> getData(@Path("name") String resourceName);
}