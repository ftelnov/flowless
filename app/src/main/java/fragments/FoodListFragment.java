package fragments;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.sirius.rs.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodListFragment extends Fragment {
    public Map<ImageButton, Integer> map = new HashMap<ImageButton, Integer>();
    public Map<ImageButton, Boolean> visited = new HashMap<ImageButton, Boolean>();
    public ImageButton imageButton;
    private Boolean flagMonday = false;
    private Boolean flagTuesday = false;
    private Boolean flagWednesday = false;
    private Boolean flagThursday = false;
    private Boolean flagFriday = false;
    private Boolean flagSaturday = false;
    private Boolean flagSunday = false;

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
        imageButton.setImageResource(R.drawable.listfood_light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.id.monday);
        arrayList.add(R.id.tuesday);
        arrayList.add(R.id.wednesday);
        arrayList.add(R.id.thursday);
        arrayList.add(R.id.friday);
        arrayList.add(R.id.saturday);
        arrayList.add(R.id.sunday);
        for(Integer id: arrayList){
            final Button button = (Button) view.findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TimeFragment timeFragment = TimeFragment.newInstance(button.getTag().toString());
                    getFragmentManager().beginTransaction().replace(R.id.container, timeFragment).addToBackStack(null).commit();
                }
            });
        }


        return view;
    }
}
