package fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.sirius.rs.R;

import java.util.HashMap;
import java.util.Map;


public class ProfileFragment extends Fragment {

    public Map<ImageButton, Integer> map = new HashMap<ImageButton, Integer>();
    public Map<ImageButton, Boolean> visited = new HashMap<ImageButton, Boolean>();
    public  ImageButton imageButton;
    private String token;
    private String user_name;
    private String user_sername;


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
        imageButton.setImageResource(R.drawable.profile_light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
