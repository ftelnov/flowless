package fragments;



import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.Image;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sirius.rs.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Objects.ClickItem;


public class SearchFragment extends Fragment {
    private boolean flag = true;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        final ConstraintLayout searchButtonLayout = (ConstraintLayout) view.findViewById(R.id.searchButtonLayout);
        final ImageButton button = (ImageButton) searchButtonLayout.findViewById(R.id.searchlistbutton);
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearSearch);
        final TextView textView = (TextView) searchButtonLayout.findViewById(R.id.textView15);
        searchButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFlag()){
                    setFlag(false);
                    button.setImageResource(R.drawable.arrowdown_light);
                    textView.setText("Показать подробности поиска");
                    linearLayout.setVisibility(View.GONE);
                }else{
                    setFlag(true);
                    button.setImageResource(R.drawable.arrow_light);
                    textView.setText("Скрыть подробности поиска");
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.searchRecycler);
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
    public Boolean getFlag(){
        return this.flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }


    //Recycler Man
    class DataAdapter extends RecyclerView.Adapter<fragments.DataAdapter.ViewHolder> {

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
        public fragments.DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.but, parent, false);
            return new fragments.DataAdapter.ViewHolder(view, context, manager);
        }

        @Override
        public void onBindViewHolder(fragments.DataAdapter.ViewHolder holder, int position) {
            ClickItem button = buttons.get(position);
            holder.bind(button.getText(), button.getIden(), button.getTime(), button.getImageRoot());
        }

        @Override
        public int getItemCount() {
            return buttons.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ConstraintLayout lay;
            TextView textView;
            TextView timeView;
            ImageView imageView;

            ViewHolder(View view, final Context context, final FragmentManager manager){
                super(view);
                lay = (ConstraintLayout) view.findViewById(R.id.constr);
                textView = lay.findViewById(R.id.button);
                timeView = lay.findViewById(R.id.time);
                imageView = lay.findViewById(R.id.imageView);
                lay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OnClickFragment fragment = OnClickFragment.newInstance(textView.getText().toString(), textView.getTag().toString());
                        manager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
                    }
                });

            }
            public void bind(String text, String id, String time, String imageRoot){
                textView.setText(text);
                textView.setTag(id);
                timeView.setText(time + " мин.");
                if (!imageRoot.isEmpty())   Picasso.get().load(imageRoot).into(imageView);
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

}
