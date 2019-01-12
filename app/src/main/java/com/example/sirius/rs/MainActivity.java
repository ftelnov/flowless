package com.example.sirius.rs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.widget.Toast;
import android.content.pm.ActivityInfo;

public class MainActivity extends AppCompatActivity {
    boolean flag = false;
    public MediaPlayer mediaPlayer;
    public MediaPlayer mediaPlayer_1;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initw();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button but = (Button) findViewById(R.id.button);
        but.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TextView tx = (TextView) findViewById(R.id.towrite);
                if (flag) {
                    tx.setText("Oh");
                    try {
                        mediaPlayer.start();
                    } catch (IllegalStateException e) {
                        mediaPlayer.pause();
                    }
                    mediaPlayer.start();
                } else {
                    try {
                        mediaPlayer_1.start();
                    } catch (IllegalStateException e) {
                        mediaPlayer_1.pause();
                    }
                    mediaPlayer_1.start();
                    tx.setText("Yeah");
                }
                flag = !flag;
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Oh, senpaaaaaai:3", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void initw() {
        mediaPlayer = (MediaPlayer) MediaPlayer.create(this, R.raw.oh);
        mediaPlayer_1 = (MediaPlayer) MediaPlayer.create(this, R.raw.yeah);
    }
}
