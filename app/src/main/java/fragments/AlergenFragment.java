package fragments;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sirius.rs.FRBody;
import com.example.sirius.rs.GetModelFood;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;
import com.example.sirius.rs.TempClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Objects.ClickItem;
import Objects.Food;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class AlergenFragment extends Fragment {
    public RecyclerView recyclerView;
    public DataAdapter dataAdapter;
    List<Food> buttons = new ArrayList<>();

    public AlergenFragment() {
    }

    static AlergenFragment newInstance(String login){
        AlergenFragment alergenFragment = new AlergenFragment();
        Bundle bundle = new Bundle();
        bundle.putString("login", login);
        alergenFragment.setArguments(bundle);
        return alergenFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_alergen, container, false);
        FRBody frBody = new FRBody();
        final ArrayList<String> ids = new ArrayList<>();
        frBody.login = getArguments().getString("login");
        RetrofitRequest.getUserAllergensApi().getData(frBody).enqueue(new Callback<List<GetModelFood>>() {
            @Override
            public void onResponse(Call<List<GetModelFood>> call, Response<List<GetModelFood>> response) {
                if (response.body() == null) return;
                for(GetModelFood food: response.body()){
                    ids.add(String.valueOf(food.id));
                }
            }

            @Override
            public void onFailure(Call<List<GetModelFood>> call, Throwable t) {
                Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_SHORT).show();
            }
        });
        dataAdapter = new DataAdapter(getContext(), buttons, getFragmentManager(), frBody.login, ids);
        RetrofitRequest.getFoodApi().getAllFood().enqueue(new Callback<List<GetModelFood>>() {
            @Override
            public void onResponse(Call<List<GetModelFood>> call, Response<List<GetModelFood>> response) {
                List<GetModelFood> foodList = response.body();
                for(GetModelFood getModelFood: foodList){
                    Food flex = new Food(getModelFood.name, getModelFood.id.toString());
                    buttons.add(flex);
                }
                recyclerView = view.findViewById(R.id.foodrecycler);
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
        private ArrayList<String> arrayList;

        DataAdapter(Context context, List<Food> buttons, FragmentManager manager, String login, ArrayList<String> ids) {
            this.buttons = buttons;
            this.inflater = LayoutInflater.from(context);
            this.context = context;
            this.manager = manager;
            this.login = login;
            this.arrayList = ids;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.fooditem, parent, false);
            return new ViewHolder(view, context, manager, login, arrayList);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Food button = buttons.get(position);
            holder.bind(button.getName(), button.getId());
        }

        @Override
        public int getItemCount() {
            return buttons.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            Button button;
            ArrayList<String> list;
            FRBody frBody = new FRBody();
            ConstraintLayout constraintLayout;
            ViewHolder(View view, final Context context, final FragmentManager manager, String login, ArrayList<String> flex){
                super(view);
                frBody.login = login;
                constraintLayout = view.findViewById(R.id.allergenLayout);
                button = constraintLayout.findViewById(R.id.buttonFood);
                list = flex;

            }
            public void bind(String text, String id){
                button.setText(text);
                button.setTag(id);
                final TempClass tempClass = new TempClass();
                button.setOnClickListener(new View.OnClickListener() {
                    private Boolean flag = true;
                    @Override
                    public void onClick(View v) {
                        if(flag){
                            RetrofitRequest.addAllergenApi().getData(button.getTag().toString(), frBody).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if(response.code() != 201){
                                        Toast.makeText(getActivity(), "Аллерген уже добавлен!", Toast.LENGTH_SHORT).show();
                                    }else{
                                        flag = !flag;
                                        button.setBackground(getResources().getDrawable(R.drawable.button_recolor, getActivity().getTheme()));
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_LONG).show();
                                }
                            });
                        }else {
                            RetrofitRequest.dellAllergenApi().getData(button.getTag().toString(), frBody).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if(response.code() != 200){
                                        Toast.makeText(getActivity(), "Продукт уже удален из аллергенов!", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        flag = !flag;
                                        button.setBackground(getResources().getDrawable(R.drawable.button, getActivity().getTheme()));
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_LONG).show();
                                }
                            }); }

                        button.setAllCaps(false);
                    }
                });
            }
        }
    }

    //
}