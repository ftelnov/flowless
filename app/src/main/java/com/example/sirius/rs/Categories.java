package com.example.sirius.rs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Categories {
    private ArrayList<Category> arrayList;
    public Categories newList(ArrayList<Category> arr){
        this.arrayList = arr;
        return this;
    }
    public ArrayList<Category> getList(){
        return arrayList;
    }
    public Category returnByPosition(Integer pos){
        return arrayList.get(pos);
    }
    public Category returnByName(String name){
        for(Category cat: arrayList){
            if(cat.getName().equals(name)) return cat;
        }
        return new Category("Null", -1, "-1" , new HashMap<Integer, ArrayList<String>>());
    }
    public Category returnById(Integer id){
        for(Category cat: arrayList){
            if(cat.getId().equals(id)) return cat;
        }
        return new Category("Null", -1, "-1", new HashMap<Integer, ArrayList<String>>());
    }
}
