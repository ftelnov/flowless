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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category,
                container, false);
        setInitialData(view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        DataAdapter adapter = new DataAdapter(getContext(), buttons);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void setInitialData(View view){
        buttons.add(new clickitem("Superior"));
        buttons.add(new clickitem("Flex"));
        buttons.add(new clickitem("Flex"));
        buttons.add(new clickitem("Flex"));

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
