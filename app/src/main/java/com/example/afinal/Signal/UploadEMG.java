package com.example.afinal.Signal;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UploadEMG extends AppCompatActivity {

    Button select,apply,show;
    String path,file_extension,file_name,class_name,seizureProbability,nonSeizureProbability;
    File file;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_emg);
        select = findViewById(R.id.UploadBtnEMG);
        progressBar = findViewById(R.id.progress);
        apply = findViewById(R.id.classify);
        show = findViewById(R.id.ShowResult);

        select.setVisibility(View.VISIBLE);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFile();

            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (path != null){
                    progressBar.setVisibility(View.VISIBLE);
                    applyModel(file);
                    progressBar.setVisibility(View.GONE);
                }else {
                    Toast.makeText(UploadEMG.this,"Please Select File",Toast.LENGTH_SHORT).show();
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadEMG.this,EMGResult.class);
                intent.putExtra("classification",class_name);
                intent.putExtra("seizureProbability",seizureProbability);
                intent.putExtra("nonSeizureProbability",nonSeizureProbability);
                intent.putExtra("file",file);
                startActivity(intent);
            }
        });
    }

    private void selectFile() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent();
            intent.setType("file/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,1);
        } else{
            ActivityCompat.requestPermissions(UploadEMG.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1&&resultCode== Activity.RESULT_OK){
            Uri uri = data.getData();
            Context context = UploadEMG.this;
            path = RealPathUtil.getRealPath(context,uri);
            file_extension = path.substring(path.lastIndexOf(".")+1);
            if (file_extension.equals("edf")||file_extension.equals("csv")){
                file = new File(path);
                file_name = file.getName();
                select.setVisibility(View.GONE);
                apply.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(context, "Pleas Select File With 'edf' or 'csv' Extension", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void applyModel(File file) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        HttpServiceEMG service = RetrofitBuilder.getClient().create(HttpServiceEMG.class);
        Call<FileModel> call = service.callModelApi(filePart);
        call.enqueue(new Callback<FileModel>() {
            @Override
            public void onResponse(Call<FileModel> call, Response<FileModel> response) {
                FileModel fileModel = response.body();
                class_name = fileModel.getClassName();
                nonSeizureProbability = fileModel.getProbabilityOfNoSeizure();
                seizureProbability = fileModel.getProbabilityOfSeizure();
                apply.setVisibility(View.GONE);
                show.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<FileModel> call, Throwable t) {
                Toast.makeText(UploadEMG.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
                Intent intent1 =new Intent(UploadEMG.this , MyProfile.class);
                startActivity(intent1);
                return true;
            case R.id.seizureHistory:
                Intent intent2 =new Intent(UploadEMG.this ,SeizureHistory.class);
                startActivity(intent2);
                return true;

            case R.id.medicalRecord:
                Intent intent3 =new Intent(UploadEMG.this , MedicalRecord.class);
                startActivity(intent3);
                return true;

            case R.id.symptoms:
                Intent intent4 =new Intent(UploadEMG.this , Symptoms.class);
                startActivity(intent4);
                return true;

            case R.id.connectionRequest:
                Intent intent5 =new Intent(UploadEMG.this , ConnectionRequest.class);
                startActivity(intent5);
                return true;
            case R.id.connectionHistory:
                Intent intent8 =new Intent(UploadEMG.this , HistoryConnection.class);
                startActivity(intent8);
                return true;

            case R.id.diet:
                Intent intent6 =new Intent(UploadEMG.this , Diet.class);
                startActivity(intent6);
                return true;


            case R.id.question:
                Intent intent10 =new Intent(UploadEMG.this , FrequentQuestion.class);
                startActivity(intent10);
                return true;
            case R.id.logout:
                Intent intent9 =new Intent(UploadEMG.this , Login.class);
                startActivity(intent9);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}