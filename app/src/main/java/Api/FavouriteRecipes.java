package Api;

import com.example.sirius.rs.AuthBody;
import com.example.sirius.rs.FRBody;
import com.example.sirius.rs.GetModelCategory;
import com.example.sirius.rs.RegisterBody;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FavouriteRecipes {
    @POST("/user/getfavs")
    Call<List<GetModelCategory>> getATruth(@Body FRBody registerBody);
}