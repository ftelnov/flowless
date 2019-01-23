package Api;

import com.example.sirius.rs.AddToMenuBody;
import com.example.sirius.rs.GetMenuBody;
import com.example.sirius.rs.GetModelCategory;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetMenuApi {
    @POST("/user/menu/{time}")
    Call<List<GetModelCategory>> getATruth(@Path("time") String time, @Body GetMenuBody addToMenuBody);
}