package com.example.sirius.rs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class categories {
    private ArrayList<category> arrayList;
    public categories newList(ArrayList<category> arr){
        this.arrayList = arr;
        return this;
    }
    public ArrayList<category> getList(){
        return arrayList;
    }
    public category returnByPosition(Integer pos){
        return arrayList.get(pos);
    }
    public category returnByName(String name){
        for(category cat: arrayList){
            if(cat.getName().equals(name)) return cat;
        }
        return new category("Null", -1, "-1" , new HashMap<Integer, ArrayList<String>>());
    }
    public category returnById(Integer id){
        for(category cat: arrayList){
            if(cat.getId().equals(id)) return cat;
        }
        return new category("Null", -1, "-1", new HashMap<Integer, ArrayList<String>>());
    }
}
