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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sirius.rs.FRBody;
import com.example.sirius.rs.GetModelCategory;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Objects.Category;
import Objects.ClickItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileShowFragment extends Fragment {
    public String login;
    public RecyclerView recyclerView;
    List<ClickItem> buttons = new ArrayList<>();
    DataAdapter adapter;


    public static ProfileShowFragment newInstance(String login) {
        ProfileShowFragment profileShowFragment = new ProfileShowFragment();
        Bundle bundle = new Bundle();
        bundle.putString("login", login);
        profileShowFragment.setArguments(bundle);
        return profileShowFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_show, container, false);
        Bundle bundle = getArguments();
        this.login = bundle.getString("login");
        TextView login = (TextView) view.findViewById(R.id.userLogin);
        login.setText(this.login);
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
        FRBody frBody = new FRBody();
        frBody.login = this.login;
        RetrofitRequest.getFavouriteRecipesApi().getATruth(frBody).enqueue(new Callback<List<GetModelCategory>>() {
            @Override
            public void onResponse(Call<List<GetModelCategory>> call, Response<List<GetModelCategory>> response) {

                HashMap<Integer, ArrayList<String>> map = new HashMap<>();
                if (response.body() == null) {
                    Toast.makeText(getActivity(), "Любимые рецепты еще не добавлены!", Toast.LENGTH_LONG).show();
                    return;
                }
                List<GetModelCategory> list = response.body();
                for (GetModelCategory adb : list) {
                    ArrayList<String> arrayList = new ArrayList<String>();
                    arrayList.add(adb.recipeTitle);
                    arrayList.add(adb.recipeId.toString());
                    arrayList.add(adb.timeOfCooking.toString());
                    arrayList.add(adb.recipeImage);
                    map.put(adb.recipeId, arrayList);
                }
                setInitialData(map);
                adapter = new DataAdapter(getContext(), buttons, getFragmentManager());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GetModelCategory>> call, Throwable t) {
                Toast.makeText(getActivity(), "Сервер недоступен или у Вас отсутствует подключение к сети!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setInitialData(HashMap<Integer, ArrayList<String>> map){
        buttons.clear();
        for(ArrayList<String> arr: map.values()){
            ClickItem click = new ClickItem(arr.get(0), arr.get(1), arr.get(2), arr.get(3));
            buttons.add(click);
        }
    }
}


