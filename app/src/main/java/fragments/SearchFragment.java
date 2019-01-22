package fragments;


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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.appyvet.materialrangebar.RangeBar;
import com.example.sirius.rs.GetModelCategory;
import com.example.sirius.rs.R;
import com.example.sirius.rs.RetrofitRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Objects.ClickItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {
    private boolean flag = true;
    List<ClickItem> buttons = new ArrayList<ClickItem>();
    public Map<ImageButton, Integer> map = new HashMap<ImageButton, Integer>();
    public Map<ImageButton, Boolean> visited = new HashMap<ImageButton, Boolean>();
    public ImageButton imageButton;
    private RecyclerView recyclerView;

    public void onResume() {
        super.onResume();
        for (ImageButton imageButton : visited.keySet()) {
            visited.put(imageButton, false);
        }
        visited.put(imageButton, true);
        for (ImageButton imageButton : map.keySet()) {
            imageButton.setImageResource(map.get(imageButton));
        }
        imageButton.setImageResource(R.drawable.search_light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        final ConstraintLayout searchButtonLayout = (ConstraintLayout) view.findViewById(R.id.searchButtonLayout);
        final ImageButton button = (ImageButton) searchButtonLayout.findViewById(R.id.mondaybutton);
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearSearch);
        final TextView textView = (TextView) searchButtonLayout.findViewById(R.id.textView15);
        searchButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFlag()) {
                    setFlag(false);
                    button.setImageResource(R.drawable.arrowdown_light);
                    textView.setText("Показать подробности поиска");
                    linearLayout.setVisibility(View.GONE);
                } else {
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
        //
        final RangeBar rangeBarCalori = (RangeBar) view.findViewById(R.id.rangebarCalori);
        final RangeBar rangeBarFats = (RangeBar) view.findViewById(R.id.rangebarFats);
        final RangeBar rangeBarCarbo = (RangeBar) view.findViewById(R.id.rangebarCarbo);
        final RangeBar rangeBarProteins = (RangeBar) view.findViewById(R.id.rangebarProteins);
        final CheckBox alergen = (CheckBox) view.findViewById(R.id.allergen);
        //
        SearchView searchView = (SearchView) view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                RetrofitRequest.getRecipeByParamApi().getData(s, Integer.parseInt(rangeBarCalori.getLeftPinValue()), Integer.parseInt(rangeBarCalori.getRightPinValue()),
                        Integer.parseInt(rangeBarFats.getLeftPinValue()), Integer.parseInt(rangeBarFats.getRightPinValue()),
                        Integer.parseInt(rangeBarProteins.getLeftPinValue()), Integer.parseInt(rangeBarProteins.getRightPinValue()),
                        Integer.parseInt(rangeBarCarbo.getLeftPinValue()), Integer.parseInt(rangeBarCalori.getRightPinValue()),
                        alergen.isChecked()).enqueue(new Callback<List<GetModelCategory>>() {
                    @Override
                    public void onResponse(Call<List<GetModelCategory>> call, Response<List<GetModelCategory>> response) {

                        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
                        setFlag(false);
                        button.setImageResource(R.drawable.arrowdown_light);
                        textView.setText("Показать подробности поиска");
                        linearLayout.setVisibility(View.GONE);
                        if (response.body() == null) {
                            buttons.clear();
                            Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_LONG).show();
                            return;
                        }
                        List<GetModelCategory> list = response.body();
                        for (GetModelCategory adb : list) {
                            ArrayList<String> arrayList = new ArrayList<String>();
                            arrayList.add(adb.recipeTitle);
                            arrayList.add(adb.recipeId.toString());
                            arrayList.add(adb.timeOfCooking.toString());
                            arrayList.add(adb.recipeImage);
                            map.put(adb.recipeId, arrayList);
                        }
                        setInitialData(view, map);
                    }

                    @Override
                    public void onFailure(Call<List<GetModelCategory>> call, Throwable t) {
                        Toast.makeText(getActivity(), "В данный момент сервер недоступен. Проверьте подключение к сети и попробуйте снова!", Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        fragments.DataAdapter adapter = new fragments.DataAdapter(getContext(), buttons, getFragmentManager());
        recyclerView.setAdapter(adapter);
        return view;
    }

    public Boolean getFlag() {
        return this.flag;
    }

    private void setInitialData(View view, HashMap<Integer, ArrayList<String>> params) {
        buttons.clear();
        HashMap<Integer, ArrayList<String>> map = params;
        for (ArrayList<String> arr : map.values()) {
            ClickItem click = new ClickItem(arr.get(0), arr.get(1), arr.get(2), arr.get(3));
            buttons.add(click);
        }
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

            ViewHolder(View view, final Context context, final FragmentManager manager) {
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

            public void bind(String text, String id, String time, String imageRoot) {
                textView.setText(text);
                textView.setTag(id);
                timeView.setText(time + " мин.");
                if (!imageRoot.isEmpty())
                    Picasso.get().load(imageRoot.substring(imageRoot.indexOf('(') + 1, imageRoot.indexOf(')'))).into(imageView);
            }
        }
    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.top = space;
            outRect.bottom = space;
        }
    }

}
