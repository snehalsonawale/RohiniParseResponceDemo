package com.example.rohiniparseresponcedemo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecyclerInterface {

 //   String JSONURL = "https://demonuts.com/Demonuts/JsonTest/Tennis/";
    String JSONURL = "http://stam.attendanceportal.com/";

    @GET("stam/api/getst_m_tedata")
    Call<String> getString();
}
