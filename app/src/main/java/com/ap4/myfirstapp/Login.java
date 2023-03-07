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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText user;
    EditText password;
    Button resultat;
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
        password = (EditText) findViewById (R.id.password);
        Button resultat = (Button) findViewById(R.id.connection);
        resultat.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        final ProgressBar progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        String chaine = user.getText().toString();
        String chaine2 = password.getText().toString();

        if (chaine.length() != 0 && chaine2.length() != 0){
            Testconnection(chaine, chaine2);
            Log.v("user",chaine);
            Log.v("pass",chaine2);
        }else{
            try {
                System.out.println("vide");
                Thread.sleep(3000);
                progressBar.setVisibility(View.INVISIBLE);

            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

    }

    private void Testconnection(String username , String pass ) {
        String url = "https://site.btsap3.tk/SiteFichier/ap3/api/api.php";

        final OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", pass)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    String responseBodyS = responseBody.string();
                    System.out.println("TEST REPONCE" + responseBodyS);

                    try {
                        JSONObject jsonO = new JSONObject(responseBodyS);
                        boolean success = (boolean) jsonO.get("success");
                        System.out.println("TEST BOOLEAN" + success);

                        if (success == true) {
                            Intent intent = new Intent(Login.this, Page_magasin.class);
                            startActivity(intent);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        });
    }
}