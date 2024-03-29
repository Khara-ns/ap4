package com.ap4.myfirstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

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

public class QrCode extends AppCompatActivity {
    Button btn_scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);
        btn_scan =findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v->
        {
            scanCode();
        });
    }
    private void scanCode()
    {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Bouton Volume+ pour activer le flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(QrCode.this);
            builder.setTitle("Resultat");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    grabProduit(result.getContents());
                }
            }).show();
        }
    });
    private void grabProduit(String uuid) {
        String url = "https://site.btsap3.tk/SiteFichier/ap3/api/apiProduitUnique.php";

        final OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("uuid", uuid)
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

                    assert responseBody != null;
                    System.out.println("super " + responseBody);
                    String responseBodyS = responseBody.string();
                    System.out.println("TEST REPONSE" + responseBodyS);

                    try {
                        JSONArray jsonPITIE = new JSONArray(responseBodyS);

                      //  JSONArray jsonNom = jsonO.getJSONArray("");
                        String name = jsonPITIE.getString(0);
                        System.out.println("ALLELUIA + "+name);
                        JSONObject jsonO = new JSONObject(name);
                        String nom = (String) jsonO.get("produit_nom");
                        String ref = (String) jsonO.get("produit_ref");
                        int prix = (int) jsonO.get("produit_coutHT");
                        String description = (String) jsonO.get("produit_description");
                        String rayon = (String) jsonO.get("rayon_libelle");
                        String lieuStockage = (String) jsonO.get("entrepot_adresse");
                        ProduitUniqueObjet MonProduitUnique = new ProduitUniqueObjet(nom,ref,prix,description,rayon,lieuStockage);

                        System.out.println("TEST : " + MonProduitUnique);

                       // if (success == true) {
                        //    Intent intent = new Intent(QrCode.this, Page_magasin.class);
                        //    startActivity(intent);
                       // }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        });
    }
}
