package Api;

import com.example.sirius.rs.AddToMenuBody;
import com.example.sirius.rs.FRBody;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AddToMenuApi {
    @POST("/user/menuadd/{time}")
    Call<ResponseBody> getATruth(@Path("time") String timef, @Body AddToMenuBody registerBody);
}