package fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import Objects.Recipe;
import ResponseBodies.AddToMenuBody;
import ResponseBodies.LoginContainer;
import ResponseBodies.GetModelRecipe;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;
import com.squareup.picasso.Picasso;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OnClickFragment extends Fragment {
    private View view;
    private Boolean flag = true;
    private LinearLayout linearLayout;
    private Boolean flag_add = true;
    private Boolean flag_auth = false;
    private Recipe recipe;
    private Map<Integer, String[]> map;

    public static OnClickFragment newInstance(Serializable map) {
        OnClickFragment fragment = new OnClickFragment();
        Bundle args = new Bundle();
        args.putSerializable("map", map);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_scrolling, menu);
        map = new HashMap<Integer, String[]>();
        map.put(R.id.mondaybreakfast, new String[]{"monday", "breakfast"});
        map.put(R.id.mondaydinner, new String[]{"monday", "dinner"});
        map.put(R.id.mondaylunch, new String[]{"monday", "lunch"});

        map.put(R.id.tuesdaybreakfast, new String[]{"tuesday", "breakfast"});
        map.put(R.id.tuesdaydinner, new String[]{"tuesday", "dinner"});
        map.put(R.id.tuesdaylunch, new String[]{"tuesday", "lunch"});

        map.put(R.id.wednesdaybreakfast, new String[]{"wednesday", "breakfast"});
        map.put(R.id.wednesdaydinner, new String[]{"wednesday", "dinner"});
        map.put(R.id.wednesdaylunch, new String[]{"wednesday", "lunch"});

        map.put(R.id.thursdaybreakfast, new String[]{"thursday", "breakfast"});
        map.put(R.id.thursdaydinner, new String[]{"thursday", "dinner"});
        map.put(R.id.thursdaylunch, new String[]{"thursday", "lunch"});

        map.put(R.id.fridaybreakfast, new String[]{"friday", "breakfast"});
        map.put(R.id.fridaydinner, new String[]{"friday", "dinner"});
        map.put(R.id.fridaylunch, new String[]{"friday", "lunch"});

        map.put(R.id.saturdaybreakfast, new String[]{"saturday", "breakfast"});
        map.put(R.id.saturdaydinner, new String[]{"saturday", "dinner"});
        map.put(R.id.saturdaylunch, new String[]{"saturday", "lunch"});

        map.put(R.id.sundaybreakfast, new String[]{"sunday", "breakfast"});
        map.put(R.id.sundaydinner, new String[]{"sunday", "dinner"});
        map.put(R.id.sundaylunch, new String[]{"sunday", "lunch"});
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addtofav) {
            final SharedPreferences mySharedPreferences = this.getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
            flag_auth = mySharedPreferences.getBoolean("auth", false);
            if (!flag_auth) {
                Toast.makeText(getActivity(), "Вы не авторизированы! Перейдите в раздел авторизации!", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!flag_add) {
                Toast.makeText(getActivity(), "Данный рецепт уже добавлен в избранное!", Toast.LENGTH_SHORT).show();
                return false;
            }
            flag_add = false;
            LoginContainer addAsFavouriteBody = new LoginContainer();
            addAsFavouriteBody.login = mySharedPreferences.getString("login", "flow");
            RetrofitRequest.getApi().addToFav(addAsFavouriteBody, String.valueOf(recipe.getId())).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        Toast.makeText(getActivity(), "Рецепт успешно добавлен в избранное!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Данный рецепт уже добавлен в избранное!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }
        else if(id == R.id.delfromfav){
            final SharedPreferences mySharedPreferences = this.getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
            flag_auth = mySharedPreferences.getBoolean("auth", false);
            if (!flag_auth) {
                Toast.makeText(getActivity(), "Вы не авторизированы! Перейдите в раздел авторизации!", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!flag_add) {
                Toast.makeText(getActivity(), "Данный рецепт уже удален из избранного!", Toast.LENGTH_SHORT).show();
                return false;
            }
            flag_add = false;
            LoginContainer addAsFavouriteBody = new LoginContainer();
            addAsFavouriteBody.login = mySharedPreferences.getString("login", "flow");
            RetrofitRequest.getApi().deleteFromFav(addAsFavouriteBody, String.valueOf(recipe.getId())).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        Toast.makeText(getActivity(), "Рецепт успешно удален из избранного!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Рецепта нет в избранном!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }
        else if (map.get(id) != null) {
            final SharedPreferences mySharedPreferences = this.getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
            flag_auth = mySharedPreferences.getBoolean("auth", false);
            if (!flag_auth) {
                Toast.makeText(getActivity(), "Вы не авторизированы! Перейдите в раздел авторизации!", Toast.LENGTH_SHORT).show();
                return false;
            }
            String[] result_day = map.get(id);
            AddToMenuBody addAsFavouriteBody = new AddToMenuBody();
            addAsFavouriteBody.login = mySharedPreferences.getString("login", "flow");
            addAsFavouriteBody.recipe_id = String.valueOf(recipe.getId());
            addAsFavouriteBody.day = result_day[0];
            RetrofitRequest.getApi().addToMenu(result_day[1], addAsFavouriteBody).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        Toast.makeText(getActivity(), "Рецепт успешно добавлен в меню!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_click, container, false);
        this.view = view;

        recipe = (Recipe) getArguments().getSerializable("map");
        linearLayout = (LinearLayout) view.findViewById(R.id.listExp);
        View tempView = getView();
        TextView name = (TextView) tempView.findViewById(R.id.nameView);
        name.setText(recipe.getTitle());
        final ConstraintLayout constraintLayout = (ConstraintLayout) tempView.findViewById(R.id.showLayout);
        final ImageButton button = (ImageButton) constraintLayout.findViewById(R.id.listButton);
        final TextView textView = (TextView) constraintLayout.findViewById(R.id.textView16);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFlag()) {
                    setFlag(false);
                    button.setImageResource(R.drawable.arrowdown_light);
                    textView.setText("Показать подробности рецепта");
                    linearLayout.setVisibility(View.GONE);
                } else {
                    setFlag(true);
                    button.setImageResource(R.drawable.arrow_light);
                    textView.setText("Скрыть подробности рецепта");
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        //
        TextView callori = (TextView) tempView.findViewById(R.id.callori);
        callori.setText(callori.getText() + recipe.getCalories().toString() + '\n');

        TextView proteins = (TextView) tempView.findViewById(R.id.proteins);
        proteins.setText(proteins.getText() + recipe.getProteins().toString() + '\n');

        TextView fats = (TextView) tempView.findViewById(R.id.fats);
        fats.setText(fats.getText() + recipe.getFats().toString() + '\n');

        TextView carbon = (TextView) tempView.findViewById(R.id.carbohydrates);
        carbon.setText(carbon.getText() + recipe.getCarbohydrates().toString() + '\n');

        TextView timecook = (TextView) tempView.findViewById(R.id.time);
        timecook.setText(timecook.getText() + recipe.getTime_of_cooking().toString() + '\n');

        TextView ingrid = (TextView) tempView.findViewById(R.id.ingrid);
        ingrid.setText(ingrid.getText() + "\n" + recipe.getRecipe_ingredients() + '\n');

        TextView portions = (TextView) tempView.findViewById(R.id.portions);
        portions.setText(portions.getText() + recipe.getPortions().toString() + '\n');

        TextView receipt = (TextView) tempView.findViewById(R.id.textView5);
        receipt.setText(receipt.getText() + "\n " + recipe.getRecipe());

        ImageView imageView = (ImageView) tempView.findViewById(R.id.imageView2);
        String image = recipe.getRecipe_image();
        if (!image.isEmpty()) {
            Picasso.get().load(image.substring(image.indexOf('(') + 1, image.indexOf(')'))).into(imageView);
        }
        return view;
    }

    public View getView() {
        return this.view;
    }

    public Boolean getFlag() {
        return this.flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public LinearLayout getLinearLayout() {
        return this.linearLayout;
    }
}
