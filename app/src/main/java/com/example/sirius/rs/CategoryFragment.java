package com.example.sirius.rs;


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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;


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
        DataAdapter adapter = new DataAdapter(getContext(), buttons, getFragmentManager());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void setInitialData(View view, String tag){
        buttons.clear();
        for(int i = 0; i < 5; ++i){
            clickitem click = new clickitem(tag);
            buttons.add(click);
        }
    }
}

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<clickitem> buttons;
    private Context context;
    private FragmentManager manager;

    DataAdapter(Context context, List<clickitem> buttons, FragmentManager manager) {
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
        clickitem button = buttons.get(position);
        holder.bind(button.string);
    }

    @Override
    public int getItemCount() {
        return buttons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;

        ViewHolder(View view, final Context context, final FragmentManager manager){
            super(view);
            button = (Button) view.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnClickFragment fragment = OnClickFragment.newInstance(button.getText().toString(), "http://gg.gg/cw79w");
                    manager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
                }
            });

        }
        public void bind(String text){
            button.setText(text);

        }
    }


}
