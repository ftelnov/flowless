package fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sirius.rs.FRBody;
import com.example.sirius.rs.GetModelCategory;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
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
    public Map<ImageButton, Integer> map = new HashMap<ImageButton, Integer>();
    public Map<ImageButton, Boolean> visited = new HashMap<ImageButton, Boolean>();
    public ImageButton imageButton;


    public static ProfileShowFragment newInstance(String login) {
        ProfileShowFragment profileShowFragment = new ProfileShowFragment();
        Bundle bundle = new Bundle();
        bundle.putString("login", login);
        profileShowFragment.setArguments(bundle);
        return profileShowFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (imageButton == null) return;
        for (ImageButton imageButton : visited.keySet()) {
            visited.put(imageButton, false);
        }
        visited.put(imageButton, true);
        for (ImageButton imageButton : map.keySet()) {
            imageButton.setImageResource(map.get(imageButton));
        }
        imageButton.setImageResource(R.drawable.profile_light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_show, container, false);
        Bundle bundle = getArguments();
        this.login = bundle.getString("login");
        final TextView log = (TextView) view.findViewById(R.id.userLogin);
        log.setText(this.login);
        final Button Fav = view.findViewById(R.id.FavouriteBut);
        Fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavouriteFragment favouriteFragment = FavouriteFragment.newInstance(login);
                getFragmentManager().beginTransaction().replace(R.id.container, favouriteFragment).addToBackStack(null).commit();
            }
        });
        final Button LogOut = view.findViewById(R.id.logout);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences mySharedPreferences = getActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                editor.putBoolean("auth", false);
                editor.apply();
                ProfileFragment profileFragment = new ProfileFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, profileFragment).addToBackStack(null).commit();
            }
        });
        final Button Allergen = view.findViewById(R.id.allergenButton);
        Allergen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlergenFragment alergenFragment = AlergenFragment.newInstance(login);
                getFragmentManager().beginTransaction().replace(R.id.container, alergenFragment).addToBackStack(null).commit();
            }
        });
        final Button myAllergen = view.findViewById(R.id.myAllergens);
        myAllergen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAllergens alergenFragment = MyAllergens.newInstance(login);
                getFragmentManager().beginTransaction().replace(R.id.container, alergenFragment).addToBackStack(null).commit();
            }
        });
        return view;
    }

}


