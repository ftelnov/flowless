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

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;


public class OnClickFragment extends Fragment {
    public static OnClickFragment newInstance(String name, String root, String dest) {
        OnClickFragment fragment = new OnClickFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("root", root);
        args.putString("dest", dest);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_click, container, false);
        String name = (String) getArguments().getString("name");
        TextView textView = (TextView) view.findViewById(R.id.nameView);
        textView.setText(name);
        String root = (String) getArguments().getString("root");
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
        Picasso.get().load(root).into(imageView);
        String dest = (String) getArguments().getString("dest");
        TextView textView2 = (TextView) view.findViewById(R.id.textView7);
        textView2.setText(dest);
        return view;
    }

}
