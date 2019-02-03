package fragments;

//Recipe - структура, созданная для хранения данных о рецепте, заполняется в Food
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

import Objects.Category;
import Objects.Recipe;

import com.example.sirius.rs.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CategoryFragment extends Fragment {
    List<Recipe> buttons = new ArrayList<>();
    public static CategoryFragment newInstance(Category tag) {
        CategoryFragment catFragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putSerializable("category", tag);
        args.putString("name", "none");
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
        DataAdapter adapter = new DataAdapter(getContext(), buttons, getFragmentManager());
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
        List<Recipe> recipeList =  cat.getReceipts();
        for(Recipe recipe: recipeList){
            buttons.add(recipe);
        }
    }
}

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Recipe> buttons;
    private Context context;
    private FragmentManager manager;

    DataAdapter(Context context, List<Recipe> buttons, FragmentManager manager) {
        this.buttons = buttons;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.manager = manager;
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.but, parent, false);
        return new ViewHolder(view, context, manager);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Recipe button = buttons.get(position);
        holder.bind(button);
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
        Recipe recipe;

        ViewHolder(View view, final Context context, final FragmentManager manager){
            super(view);
            lay = (ConstraintLayout) view.findViewById(R.id.constr);
            textView = lay.findViewById(R.id.button);
            timeView = lay.findViewById(R.id.time);
            imageView = lay.findViewById(R.id.imageView);
            lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnClickFragment fragment = OnClickFragment.newInstance(recipe);
                    manager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
                }
            });

        }
        public void bind(Recipe recipe){
            textView.setText(recipe.getTitle());
            textView.setTag(recipe.getId());
            timeView.setText(recipe.getTime_of_cooking());
            this.recipe = recipe;
            String imageRoot = recipe.getRecipe_image();
            if (!imageRoot.isEmpty())   Picasso.get().load(imageRoot.substring(imageRoot.indexOf('(') + 1, imageRoot.indexOf(')'))).into(imageView);
        }
    }
}

class SpacesItemDecoration extends RecyclerView.ItemDecoration
{
    private int space;

    public SpacesItemDecoration(int space)
    {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        outRect.top = space;
        outRect.bottom = space;
    }
}
