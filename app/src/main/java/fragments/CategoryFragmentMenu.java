package fragments;

//ArrayList первым значением берет имя рецепта, вторым - описание, третьим - рут картинки

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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sirius.rs.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Objects.Category;
import Objects.ClickItem;


public class CategoryFragmentMenu extends Fragment {
    List<ClickItem> buttons = new ArrayList<>();

    public static CategoryFragmentMenu newInstance(Category tag, String day, String meal) {
        CategoryFragmentMenu catFragment = new CategoryFragmentMenu();
        Bundle args = new Bundle();
        args.putSerializable("category", tag);
        args.putString("day", day);
        args.putString("meal", meal);
        catFragment.setArguments(args);
        return catFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category,
                container, false);
        Category category = (Category) getArguments().getSerializable("category");
        setInitialData(view, category);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        DataAdapterMenu adapter = new DataAdapterMenu(getContext(), buttons, getFragmentManager(), getArguments().getString("day"), getArguments().getString("meal"));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        float dip = 4f;
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        recyclerView.addItemDecoration(new SpacesItemDecoration((int) px));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void setInitialData(View view, Category cat){
        buttons.clear();
        Map<Integer, ArrayList<String>> map =  cat.getReceipts();
        for(ArrayList<String> arr: map.values()){
            ClickItem click = new ClickItem(arr.get(0), arr.get(1), arr.get(2), arr.get(3));
            buttons.add(click);
        }
    }
}

class DataAdapterMenu extends RecyclerView.Adapter<DataAdapterMenu.ViewHolder> {

    private LayoutInflater inflater;
    private List<ClickItem> buttons;
    private Context context;
    private FragmentManager manager;
    private String day;
    private String meal;

    DataAdapterMenu(Context context, List<ClickItem> buttons, FragmentManager manager, String day, String meal) {
        this.buttons = buttons;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.manager = manager;
        this.day = day;
        this.meal = meal;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.but, parent, false);
        return new ViewHolder(view, context, manager, this.day, this.meal);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClickItem button = buttons.get(position);
        holder.bind(button.getText(), button.getIden(), button.getTime(), button.getImageRoot());
    }

    @Override
    public int getItemCount() {
        return buttons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout lay;
        TextView textView;
        TextView timeView;
        ImageView imageView;

        ViewHolder(View view, final Context context, final FragmentManager manager, final String day, final String meal){
            super(view);
            lay = (ConstraintLayout) view.findViewById(R.id.constr);
            textView = lay.findViewById(R.id.button);
            timeView = lay.findViewById(R.id.time);
            imageView = lay.findViewById(R.id.imageView);
            lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnClickFragment fragment = OnClickFragment.newInstance(textView.getText().toString(), textView.getTag().toString(), "menu", day, meal);
                    manager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
                }
            });

        }
        public void bind(String text, String id, String time, String imageRoot){
            textView.setText(text);
            textView.setTag(id);
            timeView.setText(time + " мин.");
            if (!imageRoot.isEmpty())   Picasso.get().load(imageRoot.substring(imageRoot.indexOf('(') + 1, imageRoot.indexOf(')'))).into(imageView);
        }
    }
}

