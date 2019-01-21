package Api;

import com.example.sirius.rs.AuthBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("/auth")
    Call<ResponseBody> getATruth(@Body AuthBody registrationBody);
}