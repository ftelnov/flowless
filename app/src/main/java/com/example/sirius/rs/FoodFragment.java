package com.example.sirius.rs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


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
                    CategoryFragment catFragment = CategoryFragment.newInstance(tag);
                    fragmentManager.beginTransaction().replace(R.id.container, catFragment).addToBackStack(null).commit();
                }
            });
        }
        return view;
    }
}
