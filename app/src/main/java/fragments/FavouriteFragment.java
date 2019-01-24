package fragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
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

import com.example.sirius.rs.FRBody;
import com.example.sirius.rs.GetModelCategory;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Api.FavouriteRecipes;
import Objects.ClickItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavouriteFragment extends Fragment {
    public String login;
    public RecyclerView recyclerView;
    List<ClickItem> buttons = new ArrayList<>();
    DataAdapter adapter;

    static FavouriteFragment newInstance(String login){
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
        FRBody frBody = new FRBody();
        frBody.login = this.login;
        RetrofitRequest.getFavouriteRecipesApi().getATruth(frBody).enqueue(new Callback<List<GetModelCategory>>() {
            @Override
            public void onResponse(Call<List<GetModelCategory>> call, Response<List<GetModelCategory>> response) {

                HashMap<Integer, ArrayList<String>> map = new HashMap<>();

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
