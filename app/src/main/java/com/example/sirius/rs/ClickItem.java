package com.example.sirius.rs;

import android.widget.Button;

public class ClickItem {
    String string;
    String dest;
    String root;


    public ClickItem(String text, String dest, String root) {
        this.string = text;
        this.dest = dest;
        this.root = root;
    }

    public String getText() {
        return this.string;
    }

    public void setText(String text) {
        this.string = text;
    }

    public void setRoot(String text) {
        this.root = text;
    }

    public void setDest(String text) {
        this.dest = dest;
    }

    public String getRoot() {
        return this.root;
    }

    public String getDest() {
        return this.dest;
    }
}
