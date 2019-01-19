package Objects;

import android.widget.Button;

public class ClickItem {
    String string;
    String id;
    String timeofcooking;
    String imageRoot;


    public ClickItem(String text, String des, String timeofcooking, String imageRoot) {
        this.string = text;
        this.id = des;
        this.timeofcooking = timeofcooking;
        this.imageRoot = imageRoot;
    }

    public String getText() {
        return this.string;
    }

    public void setText(String text) {
        this.string = text;
    }

    public String getIden() {
        return this.id;
    }

    public void setIden(String id) { this.id = id;
    }

    public void setTime(String time){
        this.timeofcooking = time;
    }

    public String getTime(){
        return this.timeofcooking;
    }

    public String getImageRoot(){
        return this.imageRoot;
    }
}
