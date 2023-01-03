package com.ap4.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import static android.content.Intent.ACTION_VIEW;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn = (Button) findViewById(R.id.menuhome);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent( Login.this, MainActivity.class );
            startActivity(intent);
        });

        user = (EditText) findViewById (R.id.user);
        //password = (EditText) findViewById (R.id.password);

    }
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String chaine = user.getText().toString();
            Log.v("Essai",chaine);
        }


}