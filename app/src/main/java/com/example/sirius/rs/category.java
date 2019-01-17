package com.example.sirius.rs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class category implements Serializable {
    private Map<Integer, ArrayList<String>> map;
    private String name;
    private Integer id;
    private String imageRoot;

    category(String name, Integer id, String imageRoot, Map<Integer, ArrayList<String>> map){
        this.map = map;
        this.name = name;
        this.id = id;
        this.imageRoot = imageRoot;
    }

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Map<Integer, ArrayList<String>> getReceipts(){
        return this.map;
    }

    public String getImageRoot(){
        return this.imageRoot;
    }
}

