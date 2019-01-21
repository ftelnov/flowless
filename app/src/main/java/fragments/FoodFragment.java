package fragments;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import Objects.Category;

import com.example.sirius.rs.GetModelCategory;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FoodFragment extends Fragment {
    final Context context = getContext();
    public Map<ImageButton, Integer> map = new HashMap<ImageButton, Integer>();
    public Map<ImageButton, Boolean> visited = new HashMap<ImageButton, Boolean>();
    public  ImageButton imageButton;

    public static FoodFragment newInstance(FragmentManager manager) {
        FoodFragment catFragment = new FoodFragment();
        return catFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(imageButton == null) return;
        for(ImageButton imageButton: visited.keySet()){
            visited.put(imageButton, false);
        }
        visited.put(imageButton, true);
        for(ImageButton imageButton: map.keySet()){
            imageButton.setImageResource(map.get(imageButton));
        }
        imageButton.setImageResource(R.drawable.food_light);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food,
                container, false);
        final ImageButton firstTop = (ImageButton) view.findViewById(R.id.firstTop);
        ImageButton secondTop = (ImageButton) view.findViewById(R.id.secondTop);
        ImageButton firstCenter = (ImageButton) view.findViewById(R.id.firstCenter);
        ImageButton secondCenter = (ImageButton) view.findViewById(R.id.secondCenter);
        ImageButton firstBottom = (ImageButton) view.findViewById(R.id.firstBottom);
        ImageButton secondBottom = (ImageButton) view.findViewById(R.id.secondBottom);
        final FragmentManager fragmentManager = getFragmentManager();
        final BlurTransformation blurTransformation = new BlurTransformation(getActivity());
        Picasso.get().load(R.drawable.zavtrak).transform(blurTransformation).fit().centerCrop().into(firstTop);
        Picasso.get().load(R.drawable.salad).transform(blurTransformation).fit().centerCrop().into(secondTop);
        Picasso.get().load(R.drawable.soup).transform(blurTransformation).fit().centerCrop().into(firstCenter);
        Picasso.get().load(R.drawable.fish).transform(blurTransformation).fit().centerCrop().into(secondCenter);
        Picasso.get().load(R.drawable.chicken).transform(blurTransformation).fit().centerCrop().into(firstBottom);
        Picasso.get().load(R.drawable.meat).transform(blurTransformation).fit().centerCrop().into(secondBottom);
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
                    RetrofitRequest.getCategoryApi().getData(tag).enqueue(new Callback<List<GetModelCategory>>() {
                        @Override
                        public void onResponse(Call<List<GetModelCategory>> call, Response<List<GetModelCategory>> response) {

                            Map<Integer, ArrayList<String>> map = new HashMap<>();
                            if (response.body() == null) {
                                Toast.makeText(getActivity(), "Сервер в данный моменты недоступен, повторите запрос позже!", Toast.LENGTH_LONG).show();
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
                            Category cat = new Category(tag, -15, "http://gg.gg/cw7ad", map);
                            CategoryFragment catFragment = CategoryFragment.newInstance(cat);
                            fragmentManager.beginTransaction().replace(R.id.container, catFragment).addToBackStack(null).commit();
                        }

                        @Override
                        public void onFailure(Call<List<GetModelCategory>> call, Throwable t) {
                            Toast.makeText(getActivity(), "Сервер недоступен или у Вас отсутствует подключение к сети!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        return view;
    }
}
