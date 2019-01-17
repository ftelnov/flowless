package fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sirius.rs.Category;
import com.example.sirius.rs.GetModelCategory;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FoodFragment extends Fragment {
    final Context context = getContext();

    public static FoodFragment newInstance(FragmentManager manager) {
        FoodFragment catFragment = new FoodFragment();
        return catFragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food,
                container, false);
        ImageButton firstTop = (ImageButton) view.findViewById(R.id.firstTop);
        ImageButton secondTop = (ImageButton) view.findViewById(R.id.secondTop);
        ImageButton firstCenter = (ImageButton) view.findViewById(R.id.firstCenter);
        ImageButton secondCenter = (ImageButton) view.findViewById(R.id.secondCenter);
        ImageButton firstBottom = (ImageButton) view.findViewById(R.id.firstBottom);
        ImageButton secondBottom = (ImageButton) view.findViewById(R.id.secondBottom);
        final FragmentManager fragmentManager = getFragmentManager();
        Picasso.get().load("https://images.wallpaperscraft.ru/image/zavtrak_kruassany_kofe_yajtsa_frukty_95625_1920x1080.jpg").into(firstTop);
        Picasso.get().load("https://images.wallpaperscraft.ru/image/salat_ovoschi_vkusno_dieticheskoe_71637_1920x1080.jpg").into(secondTop);
        Picasso.get().load("https://images.wallpaperscraft.ru/image/ustricy_sup_posuda_ovoschi_79003_1920x1080.jpg").into(firstCenter);
        Picasso.get().load("http://gg.gg/cw79w").into(secondCenter);
        Picasso.get().load("http://gg.gg/cw7a8").into(firstBottom);
        Picasso.get().load("http://gg.gg/cw7ad").into(secondBottom);
        List<ImageButton> Array = new ArrayList<ImageButton>();
        Array.add(firstTop);
        Array.add(secondBottom);
        Array.add(secondTop);
        Array.add(secondCenter);
        Array.add(firstCenter);
        Array.add(firstBottom);
        for (ImageButton but : Array) {
            final String tag = (String) but.getTag();
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RetrofitRequest.getApi().getData(tag).enqueue(new Callback<List<GetModelCategory>>() {
                        @Override
                        public void onResponse(Call<List<GetModelCategory>> call, Response<List<GetModelCategory>> response) {
                            String result = new String();
                            Map<Integer, ArrayList<String>> map = new HashMap<>();
                            List<GetModelCategory> list = response.body();
                            for (GetModelCategory adb : list) {
                                ArrayList<String> arrayList = new ArrayList<String>();
                                arrayList.add(adb.recipeTitle);
                                arrayList.add(adb.recipeId.toString());
                                map.put(adb.recipeId, arrayList);
                            }
                            Category cat = new Category(tag, -15, "http://gg.gg/cw7ad", map);
                            CategoryFragment catFragment = CategoryFragment.newInstance(cat);
                            fragmentManager.beginTransaction().replace(R.id.container, catFragment).addToBackStack(null).commit();
                        }

                        @Override
                        public void onFailure(Call<List<GetModelCategory>> call, Throwable t) {
                            Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        return view;
    }
}
