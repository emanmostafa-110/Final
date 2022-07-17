package com.example.afinal.Signal;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface HttpServiceEEG {
    @Multipart
    @POST("/EEG")
    abstract Call<FileModel> callModelApi(@Part MultipartBody.Part file);
}
