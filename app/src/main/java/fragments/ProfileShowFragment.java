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
import android.widget.Button;
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


    public static ProfileShowFragment newInstance(String login) {
        ProfileShowFragment profileShowFragment = new ProfileShowFragment();
        Bundle bundle = new Bundle();
        bundle.putString("login", login);
        profileShowFragment.setArguments(bundle);
        return profileShowFragment;
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
                getFragmentManager().beginTransaction().replace(R.id.container, favouriteFragment).commit();
            }
        });
        return view;
    }

}


