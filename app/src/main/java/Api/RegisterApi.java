package Api;

import com.example.sirius.rs.AuthBody;
import com.example.sirius.rs.RegisterBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterApi {
    @POST("/register")
    Call<ResponseBody> getATruth(@Body RegisterBody registerBody);
}