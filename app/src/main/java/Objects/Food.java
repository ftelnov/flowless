package Objects;

public class Food {
    private String name;
    private String id;
    public Food(String name, String id){
        this.name = name;
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }
}
