package Objects;

import android.widget.Button;

public class ClickItem {
    String string;
    String id;


    public ClickItem(String text, String des) {
        this.string = text;
        this.id = des;
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

    public String setIden(String id) {
        return this.id;
    }
}
