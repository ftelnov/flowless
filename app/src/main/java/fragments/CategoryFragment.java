package fragments;

//ArrayList первым значением берет имя рецепта, вторым - описание, третьим - рут картинки
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sirius.rs.Category;
import com.example.sirius.rs.ClickItem;
import com.example.sirius.rs.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CategoryFragment extends Fragment {
    List<ClickItem> buttons = new ArrayList<>();
    public static CategoryFragment newInstance(Category tag) {
        CategoryFragment catFragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putSerializable("category", tag);
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
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void setInitialData(View view, Category cat){
        buttons.clear();
        Map<Integer, ArrayList<String>> map =  cat.getReceipts();
        for(ArrayList<String> arr: map.values()){
            ClickItem click = new ClickItem(arr.get(0), arr.get(1), arr.get(2));
            buttons.add(click);
        }
    }
}

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<ClickItem> buttons;
    private Context context;
    private FragmentManager manager;

    DataAdapter(Context context, List<ClickItem> buttons, FragmentManager manager) {
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
        ClickItem button = buttons.get(position);
        holder.bind(button.getText(), button.getDest(), button.getRoot());
    }

    @Override
    public int getItemCount() {
        return buttons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;
        String root;
        String dest;

        ViewHolder(View view, final Context context, final FragmentManager manager){
            super(view);
            button = (Button) view.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnClickFragment fragment = OnClickFragment.newInstance(button.getText().toString(), root, dest);
                    manager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
                }
            });

        }
        public void bind(String text, String dest, String root){
            button.setText(text);
            this.root = root;
            this.dest = dest;
        }
    }


}
