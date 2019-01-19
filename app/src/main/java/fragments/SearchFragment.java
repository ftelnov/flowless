package fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.sirius.rs.R;


public class SearchFragment extends Fragment {
    private boolean flag = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        final ImageButton button = (ImageButton) view.findViewById(R.id.searchlistbutton);
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFlag()){
                    setFlag(false);
                    button.setImageResource(R.drawable.arrow_light);
                    linearLayout.setVisibility(View.GONE);
                }else{
                    setFlag(true);
                    button.setImageResource(R.drawable.arrowdown_light);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        return view;
    }
    public Boolean getFlag(){
        return this.flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
