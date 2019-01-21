package Api;

import com.example.sirius.rs.GetModelRecipe;
import com.example.sirius.rs.GetModelRegister;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RegisterApi {
    @GET("/register")
    Call<GetModelRegister> getATruth(@Query("login") String resourceName,
                                     @Query("password") String password,
                                     @Query("mail") String email
                                   );
}