package fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sirius.rs.R;

public class ProfileShowFragment extends Fragment {
    public String login;


    public static ProfileShowFragment newInstance(String login){
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
        return view;
    }

}
