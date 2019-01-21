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


public class NotificationFragment extends Fragment {
    public Map<ImageButton, Integer> map = new HashMap<ImageButton, Integer>();
    public Map<ImageButton, Boolean> visited = new HashMap<ImageButton, Boolean>();
    public  ImageButton imageButton;

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
        imageButton.setImageResource(R.drawable.notif_light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

}
