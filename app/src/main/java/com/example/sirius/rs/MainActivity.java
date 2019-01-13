package com.example.sirius.rs;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.widget.Toast;
import android.content.pm.ActivityInfo;

public class MainActivity extends AppCompatActivity {
    boolean flag = false;
    public MediaPlayer mediaPlayer;
    public MediaPlayer mediaPlayer_1;
    boolean flagSearch = false;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

        //transaction
        fragmentManager.beginTransaction().replace(R.id.container, foodFragment).commit();
        food_but.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                search_but.setImageResource(R.drawable.search_dark);
                profile_but.setImageResource(R.drawable.profile_dark);
                notif_but.setImageResource(R.drawable.notif_dark);
                listfood_but.setImageResource(R.drawable.listfood_dark);
                food_but.setImageResource(R.drawable.food_light);
                fragmentManager.beginTransaction().replace(R.id.container, foodFragment).addToBackStack(null).commit();
            }
        });
        search_but.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                search_but.setImageResource(R.drawable.search_light);
                profile_but.setImageResource(R.drawable.profile_dark);
                notif_but.setImageResource(R.drawable.notif_dark);
                listfood_but.setImageResource(R.drawable.listfood_dark);
                food_but.setImageResource(R.drawable.food_dark);
                fragmentManager.beginTransaction().replace(R.id.container, searchFragment).addToBackStack(null).commit();
            }
        });

        profile_but.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                search_but.setImageResource(R.drawable.search_dark);
                profile_but.setImageResource(R.drawable.profile_light);
                notif_but.setImageResource(R.drawable.notif_dark);
                listfood_but.setImageResource(R.drawable.listfood_dark);
                food_but.setImageResource(R.drawable.food_dark);
                fragmentManager.beginTransaction().replace(R.id.container, profileFragment).addToBackStack(null).commit();
            }
        });

        notif_but.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                search_but.setImageResource(R.drawable.search_dark);
                profile_but.setImageResource(R.drawable.profile_dark);
                notif_but.setImageResource(R.drawable.notif_light);
                listfood_but.setImageResource(R.drawable.listfood_dark);
                food_but.setImageResource(R.drawable.food_dark);
                fragmentManager.beginTransaction().replace(R.id.container, notificationFragment).addToBackStack(null).commit();
            }
        });
        listfood_but.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                search_but.setImageResource(R.drawable.search_dark);
                profile_but.setImageResource(R.drawable.profile_dark);
                notif_but.setImageResource(R.drawable.notif_dark);
                listfood_but.setImageResource(R.drawable.listfood_light);
                food_but.setImageResource(R.drawable.food_dark);
                fragmentManager.beginTransaction().replace(R.id.container, foodlistFragment).addToBackStack(null).commit();
            }
        });
    }

}

