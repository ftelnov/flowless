package com.example.sirius.rs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextClassificationSessionFactory;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class OnClickFragment extends Fragment {
    public static OnClickFragment newInstance(String name, String root) {
        OnClickFragment fragment = new OnClickFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("root", root);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_click, container, false);
        String name = getArguments().getString("name");
        String root = getArguments().getString("root");
        TextView textView = (TextView) view.findViewById(R.id.nameView);
        textView.setText(name);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
        Picasso.get().load(root).into(imageView);
        return view;
    }

}
