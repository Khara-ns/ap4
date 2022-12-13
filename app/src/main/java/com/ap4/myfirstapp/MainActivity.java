package com.ap4.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.siuu);
        this.btn = (Button) findViewById(R.id.btn);
        this.titre = (TextView) findViewById(R.id.titre);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                titre.setText("Mais voilà mais c'était sur enfait !");
                mediaPlayer.start();
            }
        });
    }
}