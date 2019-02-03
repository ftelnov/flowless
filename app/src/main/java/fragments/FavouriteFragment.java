package fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import Objects.Category;
import Objects.Recipe;
import ResponseBodies.GetModelRecipe;
import ResponseBodies.LoginContainer;

import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavouriteFragment extends Fragment {
    public String login;
    public RecyclerView recyclerView;
    List<Recipe> buttons = new ArrayList<>();
    DataAdapter adapter;

    static FavouriteFragment newInstance(String login) {
        FavouriteFragment favouriteFragment = new FavouriteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("login", login);
        favouriteFragment.setArguments(bundle);
        return favouriteFragment;
    }

    public FavouriteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        this.login = getArguments().getString("login");
        recyclerView = view.findViewById(R.id.favouriteRecipesRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        float dip = 4f;
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        recyclerView.addItemDecoration(new fragments.SpacesItemDecoration((int) px));
        recyclerView.setLayoutManager(layoutManager);
        initData();
        return view;
    }

    private void initData() {
        LoginContainer loginContainer = new LoginContainer();
        loginContainer.login = this.login;
        RetrofitRequest.getApi().getFavouriteRecipes(loginContainer).enqueue(new Callback<List<GetModelRecipe>>() {
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
                setInitialData(recipeList);
                adapter = new DataAdapter(getContext(), buttons, getFragmentManager());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GetModelRecipe>> call, Throwable t) {
                Toast.makeText(getActivity(), "Сервер недоступен или у Вас отсутствует подключение к сети!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setInitialData(List<Recipe> recipeList) {
        buttons.clear();
        buttons.addAll(recipeList);
    }
}
