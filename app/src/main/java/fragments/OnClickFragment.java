package fragments;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sirius.rs.GetModelRecipe;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OnClickFragment extends Fragment {
    private View view;
    private Boolean flag = true;
    private LinearLayout linearLayout;

    public static OnClickFragment newInstance(String name, String id) {
        OnClickFragment fragment = new OnClickFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("id", id);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_click, container, false);
        this.view = view;
        String name = (String) getArguments().getString("name");
        String id = (String) getArguments().getString("id");
        linearLayout = (LinearLayout) view.findViewById(R.id.listExp);
        RetrofitRequest.getRecipeApi().getData(id).enqueue(new Callback<GetModelRecipe>() {
            @Override
            public void onResponse(Call<GetModelRecipe> call, Response<GetModelRecipe> response) {

                if (response.body() == null) {
                    Toast.makeText(getActivity(), "Сервер в данный моменты недоступен, повторите запрос позже!", Toast.LENGTH_LONG).show();
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack();
                    return;
                }
                final GetModelRecipe list = response.body();
                View tempView = getView();
                TextView name = (TextView) tempView.findViewById(R.id.nameView);
                name.setText(list.recipeTitle);
                //button
                final ConstraintLayout constraintLayout = (ConstraintLayout) tempView.findViewById(R.id.showLayout);
                final ImageButton button = (ImageButton) constraintLayout.findViewById(R.id.listButton);
                final TextView textView = (TextView) constraintLayout.findViewById(R.id.textView16);
                constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(getFlag()){
                            setFlag(false);
                            button.setImageResource(R.drawable.arrowdown_light);
                            textView.setText("Показать подробности поиска");
                            linearLayout.setVisibility(View.GONE);
                        }else{
                            setFlag(true);
                            button.setImageResource(R.drawable.arrow_light);
                            textView.setText("Скрыть подробности поиска");
                            linearLayout.setVisibility(View.VISIBLE);
                        }
                    }
                });

                //
                TextView callori = (TextView) tempView.findViewById(R.id.callori);
                callori.setText(callori.getText() + list.calories.toString() + '\n');

                TextView proteins = (TextView) tempView.findViewById(R.id.proteins);
                proteins.setText(proteins.getText() + list.proteins.toString() + '\n');

                TextView fats = (TextView) tempView.findViewById(R.id.fats);
                fats.setText(fats.getText() + list.fats.toString() + '\n');

                TextView carbon = (TextView) tempView.findViewById(R.id.carbohydrates);
                carbon.setText(carbon.getText() + list.carbohydrates.toString() + '\n');

                TextView timecook = (TextView) tempView.findViewById(R.id.time);
                timecook.setText(timecook.getText() + list.timeOfCooking.toString() + '\n');

                TextView ingrid = (TextView) tempView.findViewById(R.id.ingrid);
                ingrid.setText(ingrid.getText() + "\n" + list.recipeIngredients.toString() + '\n');

                TextView portions = (TextView) tempView.findViewById(R.id.portions);
                portions.setText(portions.getText() + list.portions.toString() + '\n');

                TextView receipt = (TextView) tempView.findViewById(R.id.textView5);
                receipt.setText(receipt.getText() + "\n " + list.recipe.toString());

                ImageView imageView = (ImageView) tempView.findViewById(R.id.imageView2);
                if (!list.recipeImage.isEmpty()) {
                    Picasso.get().load(list.recipeImage.substring(list.recipeImage.indexOf('(') + 1, list.recipeImage.indexOf(')'))).into(imageView);
                }

            }

            @Override
            public void onFailure(Call<GetModelRecipe> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });
        return view;
    }

    public View getView() {
        return this.view;
    }

    public Boolean getFlag(){
        return this.flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public LinearLayout getLinearLayout(){
        return this.linearLayout;
    }
}
