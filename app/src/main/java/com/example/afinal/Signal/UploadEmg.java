package com.example.afinal.Signal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.afinal.Alarm.Alarm;
import com.example.afinal.Connection.ConnectionRequest;
import com.example.afinal.Connection.HistoryConnection;
import com.example.afinal.Information.Diet;
import com.example.afinal.Information.FrequentQuestion;
import com.example.afinal.MedicalRecord;
import com.example.afinal.R;
import com.example.afinal.Symptoms;
import com.example.afinal.UI.Login;
import com.example.afinal.UI.MyProfile;


public class UploadEmg extends AppCompatActivity {

    Button upload;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_emg);
        upload = findViewById(R.id.UploadBtnEMG);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
                Intent intent = new Intent(UploadEmg.this,EMGResult.class);
                startActivity(intent);
            }
        });
    }

    private void uploadImage() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent();
            intent.setType("file/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,10);
        } else{
            ActivityCompat.requestPermissions(UploadEmg.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        Intent intent1  =new Intent(UploadEmg.this,EMGResult.class);
        startActivity(intent1);
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
                Intent intent1 =new Intent(UploadEmg.this , MyProfile.class);
                startActivity(intent1);
                return true;
            case R.id.seizureHistory:
                Intent intent2 =new Intent(UploadEmg.this ,SeizureHistory.class);
                startActivity(intent2);
                return true;

            case R.id.medicalRecord:
                Intent intent3 =new Intent(UploadEmg.this , MedicalRecord.class);
                startActivity(intent3);
                return true;

            case R.id.symptoms:
                Intent intent4 =new Intent(UploadEmg.this , Symptoms.class);
                startActivity(intent4);
                return true;

            case R.id.connectionRequest:
                Intent intent5 =new Intent(UploadEmg.this , ConnectionRequest.class);
                startActivity(intent5);
                return true;
            case R.id.connectionHistory:
                Intent intent8 =new Intent(UploadEmg.this , HistoryConnection.class);
                startActivity(intent8);
                return true;

            case R.id.diet:
                Intent intent6 =new Intent(UploadEmg.this , Diet.class);
                startActivity(intent6);
                return true;

            case R.id.alarm:
                Intent intent7 =new Intent(UploadEmg.this , Alarm.class);
                startActivity(intent7);
                return true;
            case R.id.question:
                Intent intent10 =new Intent(UploadEmg.this , FrequentQuestion.class);
                startActivity(intent10);
                return true;
            case R.id.logout:
                Intent intent9 =new Intent(UploadEmg.this , Login.class);
                startActivity(intent9);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}