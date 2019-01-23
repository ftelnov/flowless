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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sirius.rs.GetModelFood;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Objects.ClickItem;
import Objects.Food;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        dataAdapter = new DataAdapter(getContext(), buttons, getFragmentManager());
        RetrofitRequest.getFoodApi().getAllFood().enqueue(new Callback<List<GetModelFood>>() {
            @Override
            public void onResponse(Call<List<GetModelFood>> call, Response<List<GetModelFood>> response) {
                if(response.body() == null){
                    Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_SHORT).show();
                    return;
                }
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
        Toast.makeText(getActivity(), String.valueOf(buttons.size()), Toast.LENGTH_SHORT).show();
        return view;
    }


    //Adapter
    class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

        private LayoutInflater inflater;
        private List<Food> buttons;
        private Context context;
        private FragmentManager manager;

        DataAdapter(Context context, List<Food> buttons, FragmentManager manager) {
            this.buttons = buttons;
            this.inflater = LayoutInflater.from(context);
            this.context = context;
            this.manager = manager;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.fooditem, parent, false);
            return new ViewHolder(view, context, manager);
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
            ConstraintLayout constraintLayout;
            ViewHolder(View view, final Context context, final FragmentManager manager){
                super(view);
                constraintLayout = view.findViewById(R.id.allergenLayout);
                button = constraintLayout.findViewById(R.id.buttonFood);
            }
            public void bind(String text, String id){
                button.setText(text);
                button.setTag(id);
            }
        }
    }

    //
}
