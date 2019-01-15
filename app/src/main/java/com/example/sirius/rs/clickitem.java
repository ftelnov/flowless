package com.example.sirius.rs;

import android.widget.Button;

public class clickitem {
     String string;


    public clickitem(String text) {
        this.string = text;

    }

    public String getText() {
        return this.string;
    }

    public void setText(String text) {
        this.string = text;
    }


}
