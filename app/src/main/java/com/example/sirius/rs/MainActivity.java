package com.example.sirius.rs;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.content.pm.ActivityInfo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fragments.FoodFragment;
import fragments.FoodListFragment;
import fragments.NotificationFragment;
import fragments.ProfileFragment;
import fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {
    final Map<ImageButton, Integer> map = new HashMap<ImageButton, Integer>();
    final Map<ImageButton, Boolean> visited = new HashMap<ImageButton, Boolean>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("");
        super.onCreate(savedInstanceState);
        //Fragments
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FoodFragment foodFragment = new FoodFragment();
        final SearchFragment searchFragment = new SearchFragment();
        final ProfileFragment profileFragment = new ProfileFragment();
        final FoodListFragment foodlistFragment = new FoodListFragment();
        final NotificationFragment notificationFragment = new NotificationFragment();

        setContentView(R.layout.activity_main);

        //Menu
        final ImageButton food_but = (ImageButton) findViewById(R.id.foodbutton);
        final ImageButton search_but = (ImageButton) findViewById(R.id.searchbutton);
        final ImageButton profile_but = (ImageButton) findViewById(R.id.profilebutton);
        final ImageButton notif_but = (ImageButton) findViewById(R.id.notificationbutton);
        final ImageButton listfood_but = (ImageButton) findViewById(R.id.foodlistbutton);
        map.put(food_but, R.drawable.food_dark);
        map.put(search_but, R.drawable.search_dark);
        map.put(profile_but, R.drawable.profile_dark);
        map.put(notif_but, R.drawable.notif_dark);
        map.put(listfood_but, R.drawable.listfood_dark);
        visited.put(food_but, true);
        visited.put(search_but, false);
        visited.put(profile_but, false);
        visited.put(notif_but, false);
        visited.put(listfood_but, false);


        final List<ImageButton> list = new LinkedList<ImageButton>();
        list.add(food_but);
        list.add(search_but);
        list.add(profile_but);
        list.add(notif_but);
        list.add(listfood_but);

        //transaction
        fragmentManager.beginTransaction().replace(R.id.container, foodFragment).commit();
        food_but.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visited.get(food_but)) return;

                for (ImageButton imageButton : list) {
                    todark(imageButton);
                }
                food_but.setImageResource(R.drawable.food_light);
                visited.put(food_but, true);
                fragmentManager.beginTransaction().replace(R.id.container, foodFragment).addToBackStack(null).commit();
            }
        });
        search_but.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visited.get(search_but)) return;

                for (ImageButton imageButton : list) {
                    todark(imageButton);
                }
                search_but.setImageResource(R.drawable.search_light);
                visited.put(search_but, true);
                fragmentManager.beginTransaction().replace(R.id.container, searchFragment).addToBackStack(null).commit();
            }
        });

        profile_but.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visited.get(profile_but)) return;
                for (ImageButton imageButton : list) {
                    todark(imageButton);
                }
                profile_but.setImageResource(R.drawable.profile_light);
                visited.put(profile_but, true);
                fragmentManager.beginTransaction().replace(R.id.container, profileFragment).addToBackStack(null).commit();
            }
        });

        notif_but.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visited.get(notif_but)) return;

                for (ImageButton imageButton : list) {
                    todark(imageButton);
                }
                notif_but.setImageResource(R.drawable.notif_light);
                visited.put(notif_but, true);
                fragmentManager.beginTransaction().replace(R.id.container, notificationFragment).addToBackStack(null).commit();
            }
        });
        listfood_but.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visited.get(listfood_but)) return;

                for (ImageButton imageButton : list) {
                    todark(imageButton);
                }
                listfood_but.setImageResource(R.drawable.listfood_light);
                visited.put(listfood_but, true);
                fragmentManager.beginTransaction().replace(R.id.container, foodlistFragment).addToBackStack(null).commit();
            }
        });
    }

    private void todark(ImageButton imageButton) {
        imageButton.setImageResource(map.get(imageButton));
        visited.put(imageButton, false);
    }

}

