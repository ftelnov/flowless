package fragments;


import android.content.Context;
import android.content.res.Resources;
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
import android.widget.Button;
import android.widget.Toast;

import ResponseBodies.LoginContainer;
import ResponseBodies.GetModelFood;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;

import java.util.ArrayList;
import java.util.List;

import Objects.Food;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyAllergensFragment extends Fragment {
    public RecyclerView recyclerView;
    public DataAdapter dataAdapter;
    List<Food> buttons = new ArrayList<>();
    static MyAllergensFragment newInstance(String login){
        MyAllergensFragment myAllergens = new MyAllergensFragment();
        Bundle bundle = new Bundle();
        bundle.putString("login", login);
        myAllergens.setArguments(bundle);
        return myAllergens;
    }

    public MyAllergensFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_my_allergens, container, false);
        LoginContainer loginContainer = new LoginContainer();
        loginContainer.login = getArguments().getString("login");
        dataAdapter = new DataAdapter(getContext(), buttons, getFragmentManager(), loginContainer.login);

        RetrofitRequest.getApi().getUserAllergens(loginContainer).enqueue(new Callback<List<GetModelFood>>() {
            @Override
            public void onResponse(Call<List<GetModelFood>> call, Response<List<GetModelFood>> response) {
                if(response.body() == null){
                    return;
                }
                List<GetModelFood> foodList = response.body();
                for(GetModelFood getModelFood: foodList){
                    Food flex = new Food(getModelFood.name, getModelFood.id.toString());
                    buttons.add(flex);
                }
                recyclerView = view.findViewById(R.id.recyclerAllergens);
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
                recyclerView.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<GetModelFood>> call, Throwable t) {
                Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    //Adapter
    class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

        private LayoutInflater inflater;
        private List<Food> buttons;
        private Context context;
        private FragmentManager manager;
        private String login;

        DataAdapter(Context context, List<Food> buttons, FragmentManager manager, String login) {
            this.buttons = buttons;
            this.inflater = LayoutInflater.from(context);
            this.context = context;
            this.manager = manager;
            this.login = login;
        }
        @Override
        public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.fooditem, parent, false);
            return new DataAdapter.ViewHolder(view, context, manager, login);
        }

        @Override
        public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
            Food button = buttons.get(position);
            holder.bind(button.getName(), button.getId());
        }

        @Override
        public int getItemCount() {
            return buttons.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            Button button;
            LoginContainer loginContainer = new LoginContainer();
            ConstraintLayout constraintLayout;
            ViewHolder(View view, final Context context, final FragmentManager manager, String login){
                super(view);
                loginContainer.login = login;
                constraintLayout = view.findViewById(R.id.allergenLayout);
                button = constraintLayout.findViewById(R.id.buttonFood);
                button.setOnClickListener(new View.OnClickListener() {
                    private Boolean flag = true;
                    @Override
                    public void onClick(View v) {
                        if(flag){
                            RetrofitRequest.getApi().delAllergen(button.getTag().toString(), loginContainer).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if(response.code() != 200){
                                        Toast.makeText(getActivity(), "Что-то пошло не так, попробуйте снова!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_LONG).show();
                                }
                            });
                            button.setBackground(getResources().getDrawable(R.drawable.button_recolor, getActivity().getTheme()));
                        }else {
                            button.setBackground(getResources().getDrawable(R.drawable.button, getActivity().getTheme()));}
                        flag = !flag;
                        button.setAllCaps(false);
                    }
                });
            }
            public void bind(String text, String id){
                button.setText(text);
                button.setTag(id);
            }
        }
    }
}
