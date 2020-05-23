package com.example.rohiniparseresponcedemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecyclerInterface {

   // String JSONURL = "https://demonuts.com/Demonuts/JsonTest/Tennis/";
 //   String JSONURL = "http://stam.attendanceportal.com/";
    String JSONURL = "http://stam.attendanceportal.com/stam/api/";

    //@GET("stam/api/getst_m_tedata")
    @GET("getSt_M_Tedata")
    Call<String> getString(@Query("APIKey") String api,@Query("MAC") String MAC);
}
