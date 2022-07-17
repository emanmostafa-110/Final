package com.example.afinal.Signal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

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


public class EEGResult extends AppCompatActivity {
    EditText classificationEEG ,probabilityNonEEG,probabilitySeizureEEG;
    Button saveEEG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eegresult);
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
                Intent intent1 =new Intent(EEGResult.this , MyProfile.class);
                startActivity(intent1);
                return true;
            case R.id.seizureHistory:
                Intent intent2 =new Intent(EEGResult.this ,SeizureHistory.class);
                startActivity(intent2);
                return true;

            case R.id.medicalRecord:
                Intent intent3 =new Intent(EEGResult.this , MedicalRecord.class);
                startActivity(intent3);
                return true;

            case R.id.symptoms:
                Intent intent4 =new Intent(EEGResult.this , Symptoms.class);
                startActivity(intent4);
                return true;

            case R.id.connectionRequest:
                Intent intent5 =new Intent(EEGResult.this , ConnectionRequest.class);
                startActivity(intent5);
                return true;
            case R.id.connectionHistory:
                Intent intent8 =new Intent(EEGResult.this , HistoryConnection.class);
                startActivity(intent8);
                return true;

            case R.id.diet:
                Intent intent6 =new Intent(EEGResult.this , Diet.class);
                startActivity(intent6);
                return true;


            case R.id.question:
                Intent intent10 =new Intent(EEGResult.this , FrequentQuestion.class);
                startActivity(intent10);
                return true;
            case R.id.logout:
                Intent intent9 =new Intent(EEGResult.this , Login.class);
                startActivity(intent9);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }
