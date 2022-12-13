package com.ap4.myfirstapp;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView titre;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.siuu);
        Button btn = (Button) findViewById(R.id.btn);
        this.titre = (TextView) findViewById(R.id.titre);

        btn.setOnClickListener(view -> {
            titre.setText("Mais voilà mais c'était sur enfait !");
            mediaPlayer.start();
        });
    }
}