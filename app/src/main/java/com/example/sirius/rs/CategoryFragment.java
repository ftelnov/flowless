package com.example.sirius.rs;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    List<clickitem> buttons = new ArrayList<>();
    public static CategoryFragment newInstance(String tag) {
        CategoryFragment catFragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("tag", tag);
        catFragment.setArguments(args);
        return catFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category,
                container, false);
        String tag = getArguments().getString("tag");
        setInitialData(view, tag);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        DataAdapter adapter = new DataAdapter(getContext(), buttons);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void setInitialData(View view, String tag){
        switch (tag){
            case "breakfast":
                buttons.add(new clickitem("breakfast1"));
                buttons.add(new clickitem("breakfast2"));
                buttons.add(new clickitem("breakfast3"));
                buttons.add(new clickitem("breakfast4"));
                break;
            case "fish":
                buttons.add(new clickitem("fish1"));
                buttons.add(new clickitem("fish2"));
                buttons.add(new clickitem("fish3"));
                buttons.add(new clickitem("fish4"));
                break;
            case "soup":
                buttons.add(new clickitem("soup1"));
                buttons.add(new clickitem("soup2"));
                buttons.add(new clickitem("soup3"));
                buttons.add(new clickitem("soup4"));
                break;
            case "salad":
                buttons.add(new clickitem("salad1"));
                buttons.add(new clickitem("salad2"));
                buttons.add(new clickitem("salad3"));
                buttons.add(new clickitem("salad4"));
                break;
            case "chicken":
                buttons.add(new clickitem("chicken1"));
                buttons.add(new clickitem("chicken2"));
                buttons.add(new clickitem("chicken3"));
                buttons.add(new clickitem("chicken4"));
                break;
            case "meat":
                buttons.add(new clickitem("meat1"));
                buttons.add(new clickitem("meat2"));
                buttons.add(new clickitem("meat3"));
                buttons.add(new clickitem("meat4"));
                break;
        }


    }
}

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<clickitem> buttons;

    DataAdapter(Context context, List<clickitem> buttons) {
        this.buttons = buttons;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.but, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        clickitem button = buttons.get(position);
        holder.bind(button.string);
    }

    @Override
    public int getItemCount() {
        return buttons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;

        ViewHolder(View view){
            super(view);
            button = (Button) view.findViewById(R.id.button);

        }
        public void bind(String text){
            button.setText(text);

        }
    }


}
