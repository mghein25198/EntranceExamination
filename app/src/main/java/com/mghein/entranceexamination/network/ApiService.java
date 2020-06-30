package com.mghein.entranceexamination.network;

import com.mghein.entranceexamination.model.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("latest")
    Call<DataResponse> getLatest();
}
