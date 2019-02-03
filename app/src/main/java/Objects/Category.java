package Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Category implements Serializable {
    private List<Recipe> recipeList;
    private String name;
    private Integer id;
    private String imageRoot;

    public Category(String name, Integer id, String imageRoot, List<Recipe> map){
        this.recipeList = map;
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

    public List<Recipe> getReceipts(){
        return this.recipeList;
    }

    public String getImageRoot(){
        return this.imageRoot;
    }
}

