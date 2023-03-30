package com.ap4.myfirstapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class Liste_produit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_produit);
        RecoitProduit();
    }


    private void RecoitProduit() {
        String url = "https://site.btsap3.tk/SiteFichier/ap3/api/apiEnvoitousProduit.php";
        String test = "sqdfdsjlkdfhqshdfjkhdqkjhdsfjhfdgojesfgnmnsfhfdhjkshfhjfsiops";

        final OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("test", test)
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
                ProduitObjet[] produit = new ProduitObjet[0];
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }


                    String responseBodyS = responseBody.string();
                    System.out.println("TEST REPONCE" + responseBodyS);
                    JSONObject reponceL = new JSONObject(responseBodyS);
                    JSONArray reponce = reponceL.getJSONArray("reponce");
                    int nombreProduit = reponce.length();


                    String nomProduit;
                    String photoProduit;
                    produit = new ProduitObjet[nombreProduit];

                    //  JSONArray jsonNom = jsonO.getJSONArray("");
                    for (int i = 0; i < nombreProduit; i++) {

                        nomProduit = (String) reponce.getJSONObject(i).getString("produit_nom");
                        photoProduit = (String) reponce.getJSONObject(i).getString("photo_fichier");

                        produit[i] = new ProduitObjet(nomProduit, photoProduit);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //test
                for (int i = 0; i < produit.length; i++){
                System.out.println(produit[i].getProduitNom());
                System.out.println(produit[i].getProduitPhoto());
                }
            }
        });
    }
}