package fragments;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.appyvet.materialrangebar.RangeBar;

import Objects.Recipe;
import ResponseBodies.GetModelRecipe;
import ResponseBodies.LoginContainer;

import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ResponseBodies.RecipeParametersBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {
    private boolean flag = true;
    List<Recipe> buttons = new ArrayList<Recipe>();
    public Map<ImageButton, Integer> map = new HashMap<ImageButton, Integer>();
    public Map<ImageButton, Boolean> visited = new HashMap<ImageButton, Boolean>();
    public ImageButton imageButton;
    private RecyclerView recyclerView;

    public void onResume() {
        super.onResume();
        for (ImageButton imageButton : visited.keySet()) {
            visited.put(imageButton, false);
        }
        visited.put(imageButton, true);
        for (ImageButton imageButton : map.keySet()) {
            imageButton.setImageResource(map.get(imageButton));
        }
        imageButton.setImageResource(R.drawable.search_light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        final ConstraintLayout searchButtonLayout = (ConstraintLayout) view.findViewById(R.id.searchButtonLayout);
        final ImageButton button = (ImageButton) searchButtonLayout.findViewById(R.id.mondaybutton);
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearSearch);
        final TextView textView = (TextView) searchButtonLayout.findViewById(R.id.textView15);
        searchButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFlag()) {
                    setFlag(false);
                    button.setImageResource(R.drawable.arrowdown_light);
                    textView.setText("Показать подробности поиска");
                    linearLayout.setVisibility(View.GONE);
                } else {
                    setFlag(true);
                    button.setImageResource(R.drawable.arrow_light);
                    textView.setText("Скрыть подробности поиска");
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.searchRecycler);
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
        //
        final RangeBar rangeBarCalori = (RangeBar) view.findViewById(R.id.rangebarCalori);
        final RangeBar rangeBarFats = (RangeBar) view.findViewById(R.id.rangebarFats);
        final RangeBar rangeBarCarbo = (RangeBar) view.findViewById(R.id.rangebarCarbo);
        final RangeBar rangeBarProteins = (RangeBar) view.findViewById(R.id.rangebarProteins);
        final CheckBox alergen = (CheckBox) view.findViewById(R.id.allergen);
        //
        SearchView searchView = (SearchView) view.findViewById(R.id.searchView);
        final RecipeParametersBody recipeParametersBody = new RecipeParametersBody();
        recipeParametersBody.login = this.getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE).getString("login", "$####root####$");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                recipeParametersBody.resourceName = s;
                recipeParametersBody.calories_min = Integer.parseInt(rangeBarCalori.getLeftPinValue());
                recipeParametersBody.calories_max = Integer.parseInt(rangeBarCalori.getRightPinValue());
                recipeParametersBody.fats_min = Integer.parseInt(rangeBarFats.getLeftPinValue());
                recipeParametersBody.fats_max = Integer.parseInt(rangeBarFats.getRightPinValue());
                recipeParametersBody.proteins_min = Integer.parseInt(rangeBarProteins.getLeftPinValue());
                recipeParametersBody.proteins_max = Integer.parseInt(rangeBarProteins.getRightPinValue());
                recipeParametersBody.carbohydrates_min = Integer.parseInt(rangeBarCarbo.getLeftPinValue());
                recipeParametersBody.carbohydrates_max = Integer.parseInt(rangeBarCarbo.getRightPinValue());
                recipeParametersBody.flag = alergen.isChecked();
                RetrofitRequest.getApi().getRecipeByParameters(recipeParametersBody).enqueue(new Callback<List<GetModelRecipe>>() {
                    @Override
                    public void onResponse(Call<List<GetModelRecipe>> call, Response<List<GetModelRecipe>> response) {

                        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
                        setFlag(false);
                        button.setImageResource(R.drawable.arrowdown_light);
                        textView.setText("Показать подробности поиска");
                        linearLayout.setVisibility(View.GONE);
                        if (response.body() == null) {
                            buttons.clear();
                            return;
                        }
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
                        setInitialData(view, recipeList);
                    }

                    @Override
                    public void onFailure(Call<List<GetModelRecipe>> call, Throwable t) {
                        Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        fragments.DataAdapter adapter = new fragments.DataAdapter(getContext(), buttons, getFragmentManager());
        recyclerView.setAdapter(adapter);
        return view;
    }

    public Boolean getFlag() {
        return this.flag;
    }

    private void setInitialData(View view, List<Recipe> recipeList) {
        buttons.clear();
        buttons.addAll(recipeList);
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }


}
