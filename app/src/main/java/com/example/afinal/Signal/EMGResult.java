package com.example.afinal.Signal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.Connection.ConnectionRequest;
import com.example.afinal.Connection.HistoryConnection;
import com.example.afinal.Information.Diet;
import com.example.afinal.Information.FrequentQuestion;
import com.example.afinal.MedicalRecord;
import com.example.afinal.R;
import com.example.afinal.Symptoms;
import com.example.afinal.UI.Login;
import com.example.afinal.UI.MyProfile;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class EMGResult extends AppCompatActivity {

    EditText classification,seizure,nonSeizure;
    Button save;
    String class_name,seizureProbability,nonSeizureProbability;
    File file;
    final String type="EMG";
    String url = "http://epilepsy.novel-eg.com/public/api/storeSignal";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emgresult);

        classification = findViewById(R.id.classificationEMG);
        seizure = findViewById(R.id.probabilitySeizureEMG);
        nonSeizure = findViewById(R.id.probabilityNonEMG);
        save = findViewById(R.id.saveEMG);

        class_name = getIntent().getStringExtra("classification");
        seizureProbability = getIntent().getStringExtra("seizureProbability");
        nonSeizureProbability = getIntent().getStringExtra("nonSeizureProbability");
        file = (File) getIntent().getExtras().get("file");

        classification.setText(class_name);
        seizure.setText(seizureProbability);
        nonSeizure.setText(nonSeizureProbability);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadInformation(url, file, type, class_name, seizureProbability, nonSeizureProbability);
            }
        });
    }

    private void uploadInformation(String url,File file,String type,String class_name,String seizureProbability, String nonSeizureProbability) {

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file",file.getName(),RequestBody.create(MediaType.parse("file/*"),file))
                .addFormDataPart("type",type)
                .addFormDataPart("classification",class_name)
                .addFormDataPart("prop_of_seiz", seizureProbability)
                .addFormDataPart("prop_of_non_seiz", nonSeizureProbability)
                .build();

        Request request=new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(EMGResult.this, "Successful", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menue, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.profile:
                Intent intent1 =new Intent(EMGResult.this , MyProfile.class);
                startActivity(intent1);
                return true;
            case R.id.seizureHistory:
                Intent intent2 =new Intent(EMGResult.this ,SeizureHistory.class);
                startActivity(intent2);
                return true;

            case R.id.medicalRecord:
                Intent intent3 =new Intent(EMGResult.this , MedicalRecord.class);
                startActivity(intent3);
                return true;

            case R.id.symptoms:
                Intent intent4 =new Intent(EMGResult.this , Symptoms.class);
                startActivity(intent4);
                return true;

            case R.id.connectionRequest:
                Intent intent5 =new Intent(EMGResult.this , ConnectionRequest.class);
                startActivity(intent5);
                return true;
            case R.id.connectionHistory:
                Intent intent8 =new Intent(EMGResult.this , HistoryConnection.class);
                startActivity(intent8);
                return true;

            case R.id.diet:
                Intent intent6 =new Intent(EMGResult.this , Diet.class);
                startActivity(intent6);
                return true;


            case R.id.question:
                Intent intent10 =new Intent(EMGResult.this , FrequentQuestion.class);
                startActivity(intent10);
                return true;
            case R.id.logout:
                Intent intent9 =new Intent(EMGResult.this , Login.class);
                startActivity(intent9);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
