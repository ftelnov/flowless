package fragments;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.sirius.rs.R;

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

        //Monday
        ConstraintLayout Monday = view.findViewById(R.id.mondayLayout);
        final ImageButton MondayButton = view.findViewById(R.id.mondaybutton);
        final RecyclerView MondayRecycler = view.findViewById(R.id.MondayRecylcer);
        Monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagMonday) {
                    flagMonday = false;
                    MondayButton.setImageResource(R.drawable.arrowdown_light);
                    MondayRecycler.setVisibility(View.GONE);
                } else {
                    flagMonday = true;
                    MondayButton.setImageResource(R.drawable.arrow_light);
                    MondayRecycler.setVisibility(View.VISIBLE);
                }
            }
        });
        //

        //Tuesday
        ConstraintLayout Tuesday = view.findViewById(R.id.tuesdayLayout);
        final ImageButton TuesdayButton = view.findViewById(R.id.tuesdaybutton);
        final RecyclerView TuesdayRecycler = view.findViewById(R.id.TuesdayRecylcer);
        Tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagTuesday) {
                    flagTuesday = false;
                    TuesdayButton.setImageResource(R.drawable.arrowdown_light);
                    TuesdayRecycler.setVisibility(View.GONE);
                } else {
                    flagTuesday = true;
                    TuesdayButton.setImageResource(R.drawable.arrow_light);
                    TuesdayRecycler.setVisibility(View.VISIBLE);
                }
            }
        });
        //

        //Tuesday
        ConstraintLayout Wednesday = view.findViewById(R.id.wednesdayLayout);
        final ImageButton WednesdayButton = view.findViewById(R.id.wednesdaybutton);
        final RecyclerView WednesdayRecycler = view.findViewById(R.id.wednesdayRecylcer);
        Wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagWednesday) {
                    flagWednesday = false;
                    WednesdayButton.setImageResource(R.drawable.arrowdown_light);
                    WednesdayRecycler.setVisibility(View.GONE);
                } else {
                    flagWednesday = true;
                    WednesdayButton.setImageResource(R.drawable.arrow_light);
                    WednesdayRecycler.setVisibility(View.VISIBLE);
                }
            }
        });
        //

        //ThursDay
        ConstraintLayout Thursday = view.findViewById(R.id.thursdayLayout);
        final ImageButton ThursdayButton = view.findViewById(R.id.thursdaybutton);
        final RecyclerView ThursdayRecycler = view.findViewById(R.id.thursdayRecylcer);
        Thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagThursday) {
                    flagThursday = false;
                    ThursdayButton.setImageResource(R.drawable.arrowdown_light);
                    ThursdayRecycler.setVisibility(View.GONE);
                } else {
                    flagThursday = true;
                    ThursdayButton.setImageResource(R.drawable.arrow_light);
                    ThursdayRecycler.setVisibility(View.VISIBLE);
                }
            }
        });
        //
        //FriDay
        ConstraintLayout Friday = view.findViewById(R.id.fridayLayout);
        final ImageButton FridayButton = view.findViewById(R.id.fridaybutton);
        final RecyclerView FridayRecycler = view.findViewById(R.id.fridayRecylcer);
        Friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagFriday) {
                    flagFriday = false;
                    FridayButton.setImageResource(R.drawable.arrowdown_light);
                    FridayRecycler.setVisibility(View.GONE);
                } else {
                    flagFriday = true;
                    FridayButton.setImageResource(R.drawable.arrow_light);
                    FridayRecycler.setVisibility(View.VISIBLE);
                }
            }
        });
        //
        //SaturDay
        ConstraintLayout Saturday = view.findViewById(R.id.saturLayout);
        final ImageButton SaturdayButton = view.findViewById(R.id.saturdaybutton);
        final RecyclerView SaturdayRecycler = view.findViewById(R.id.saturdayRecylcer);
        Saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagSaturday) {
                    flagSaturday = false;
                    SaturdayButton.setImageResource(R.drawable.arrowdown_light);
                    SaturdayRecycler.setVisibility(View.GONE);
                } else {
                    flagSaturday = true;
                    SaturdayButton.setImageResource(R.drawable.arrow_light);
                    SaturdayRecycler.setVisibility(View.VISIBLE);
                }
            }
        });
        //

        //SaturDay
        ConstraintLayout Sunday = view.findViewById(R.id.sundayLayout);
        final ImageButton SundayButton = view.findViewById(R.id.sundaybutton);
        final RecyclerView SundayRecycler = view.findViewById(R.id.sundayRecylcer);
        Sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagSunday) {
                    flagSunday = false;
                    SundayButton.setImageResource(R.drawable.arrowdown_light);
                    SundayRecycler.setVisibility(View.GONE);
                } else {
                    flagSunday = true;
                    SundayButton.setImageResource(R.drawable.arrow_light);
                    SundayRecycler.setVisibility(View.VISIBLE);
                }
            }
        });
        //
        return view;
    }
}
