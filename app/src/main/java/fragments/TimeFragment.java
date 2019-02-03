package fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import Objects.Recipe;
import ResponseBodies.GetMenuBody;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Objects.Category;
import ResponseBodies.GetModelRecipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TimeFragment extends Fragment {
    Boolean flag_auth;
    static TimeFragment newInstance(String day){
        TimeFragment timeFragment = new TimeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("day", day);
        timeFragment.setArguments(bundle);
        return timeFragment;
    }

    public TimeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_fragment, container, false);
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.id.breakfast);
        arrayList.add(R.id.lunch);
        arrayList.add(R.id.dinner);
        final SharedPreferences mySharedPreferences = this.getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        flag_auth = mySharedPreferences.getBoolean("auth", false);
        final String tag = getArguments().getString("day");
        for(Integer id: arrayList){
            final Button button = (Button) view.findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!flag_auth) {
                        Toast.makeText(getActivity(), "Вы не авторизированы! Перейдите в раздел авторизации!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    GetMenuBody getMenuBody = new GetMenuBody();
                    getMenuBody.day = tag.toLowerCase();
                    getMenuBody.login = mySharedPreferences.getString("login", "flow");
                    RetrofitRequest.getApi().getMenu(button.getTag().toString(), getMenuBody).enqueue(new Callback<List<GetModelRecipe>>() {
                        @Override
                        public void onResponse(Call<List<GetModelRecipe>> call, Response<List<GetModelRecipe>> response) {

                            List<Recipe> recipeList = new ArrayList<Recipe>();
                            List<GetModelRecipe> list = response.body();
                            for (GetModelRecipe adb : list) {
                                Recipe recipe = new Recipe(adb.recipeId, adb.recipeTitle,
                                        adb.timeOfCooking, adb.recipeType, adb.calories,
                                        adb.proteins, adb.fats, adb.recipeIngredients,
                                        adb.portions, adb.recipeImage, adb.userId,
                                        adb.carbohydrates, adb.recipe);
                                recipeList.add(recipe);

                            }
                            Category cat = new Category(tag, -15, "http://gg.gg/cw7ad", recipeList);
                            CategoryFragment catFragment = CategoryFragment.newInstance(cat);
                            getFragmentManager().beginTransaction().replace(R.id.container, catFragment).addToBackStack(null).commit();
                        }

                        @Override
                        public void onFailure(Call<List<GetModelRecipe>> call, Throwable t) {
                            Toast.makeText(getActivity(), "Сервер недоступен или у Вас отсутствует подключение к сети!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        return view;
    }

}
