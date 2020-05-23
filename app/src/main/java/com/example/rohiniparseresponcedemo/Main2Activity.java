package com.example.rohiniparseresponcedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Main2Activity extends AppCompatActivity {


     private RetrofitAdapter retrofitAdapter;
    private RecyclerView recyclerView;

    ArrayList   arrayList1 ;
    ArrayList arrayList2;
    Spinner spinner,subsubject;
    String selectedSubjectName,selectedSubSubjectName,username;
    ModelRecycler modelRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.recycler);

        fetchJSON();

    }


    private void fetchJSON() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RecyclerInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RecyclerInterface api = retrofit.create(RecyclerInterface.class);

        Call<String> call = api.getString("460ad6f3-8216-469f-9b1d-52cffa5d812d","00-03-64-00-09-96");

        /// tresponce is parsed here

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {   // imp get resonce in strin format
                //  Log.i("Responsestring", response.body());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        // Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();  /// responce send to methos
                        writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    private void writeRecycler(String jsonresponse) {  /// responce is in method as string

        try {
            JSONObject jsonObject = new JSONObject(jsonresponse);/// responce get into json object
            ArrayList<ModelRecycler> modelRecyclerArrayList = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("TeData");// from object get json array

            for (int i = 0; i < jsonArray.length(); i++) {
                modelRecycler = new ModelRecycler();
                JSONObject dataobj = jsonArray.getJSONObject(i);

                modelRecycler.setFname(dataobj.getString("fname"));
                modelRecycler.setLname(dataobj.getString("lname"));
                modelRecycler.setSubSubject_Name(dataobj.getString("Subject_Name"));
                modelRecycler.setId(dataobj.getInt("Tid"));

                modelRecyclerArrayList.add(modelRecycler);
              //  Log.e("sub subject responce",""+modelRecycler);



            }

            retrofitAdapter = new RetrofitAdapter(this,modelRecyclerArrayList);
            recyclerView.setAdapter(retrofitAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

            //Log.e("sub subject responce",""+modelRecyclerArrayList);










        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("subject responce",""+arrayList1);
        Log.e("sub subject responce",""+arrayList2);







    }
}
