package fragments;


import android.content.Context;
import android.content.res.Resources;
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

import com.example.sirius.rs.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Objects.ClickItem;
import Objects.Food;

public class AlergenFragment extends Fragment {
    public RecyclerView recyclerView;
    List<Food> buttons = new ArrayList<>();

    public AlergenFragment() {
    }

    static AlergenFragment newInstance(String login){
        AlergenFragment alergenFragment = new AlergenFragment();
        Bundle bundle = new Bundle();
        bundle.putString("login", login);
        alergenFragment.setArguments(bundle);
        return alergenFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alergen, container, false);
        recyclerView = view.findViewById(R.id.foodrecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        float dip = 4f;
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        recyclerView.addItemDecoration(new fragments.SpacesItemDecoration((int) px));
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
    //Adapter
    class DataAdapter extends RecyclerView.Adapter<fragments.DataAdapter.ViewHolder> {

        private LayoutInflater inflater;
        private List<Food> buttons;
        private Context context;
        private FragmentManager manager;

        DataAdapter(Context context, List<Food> buttons, FragmentManager manager) {
            this.buttons = buttons;
            this.inflater = LayoutInflater.from(context);
            this.context = context;
            this.manager = manager;
        }
        @Override
        public fragments.DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.fooditem, parent, false);
            return new fragments.DataAdapter.ViewHolder(view, context, manager);
        }

        @Override
        public void onBindViewHolder(fragments.DataAdapter.ViewHolder holder, int position) {
            Food button = buttons.get(position);
            holder.bind(button.getName());
        }

        @Override
        public int getItemCount() {
            return buttons.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            Button button;
            ViewHolder(View view, final Context context, final FragmentManager manager){
                super(view);
                button = view.findViewById(R.id.buttonFood);
            }
            public void bind(String text){
                button.setText(text);
            }
        }
    }

    //
}
