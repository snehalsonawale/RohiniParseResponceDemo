package com.example.rohiniparseresponcedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {




  //  private RetrofitAdapter retrofitAdapter;
    private RecyclerView recyclerView;
    Spinner spinner,subsubject;
    String selectedSubjectName,selectedSubSubjectName,username;

    ArrayList   arrayList1 ;
    ArrayList arrayList2;
    TextView tvusername;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchJSON();
       // recyclerView = findViewById(R.id.recycler);


        spinner= (Spinner) findViewById(R.id.subjectName);
        subsubject= (Spinner) findViewById(R.id.Sub_subjectName);
        tvusername= (TextView) findViewById(R.id.username);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSubjectName = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"selected subject"+selectedSubjectName,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        subsubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSubSubjectName = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"selected sub subject"+selectedSubSubjectName,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    private void fetchJSON() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RecyclerInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RecyclerInterface api = retrofit.create(RecyclerInterface.class);

        Call<String> call = api.getString();

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

           JSONArray jsonArray = jsonObject.getJSONArray("TeData");// from object get json array
             arrayList1 = new ArrayList();// created simple arraylist for name
                     arrayList2 = new ArrayList();// aray list for sub subject name
            for(int i=0;i<jsonArray.length();i++)/// lop for multiple values
            {
                try {
                    JSONObject jobj=jsonArray.getJSONObject(i);// arraylist contains json objects so one by one it take

                    int id=jobj.getInt("Tid"); // from object get id to match



                    String subject,subsubject;
                    if(id== 101200002)  // compare your id here if id is match get that objecs name and sub subject name
                    {
                        username=jobj.getString("fname");
                        tvusername.setText(username);
                        subject=jobj.getString("Subject_Name");
                        subsubject=jobj.getString("SubSubject_Name");
                        arrayList1.add(subject);
                        arrayList2.add(subsubject);

                      //  Log.e("RESSSSSoponce",""+arrayList);
                    }



                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }




            }







        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("subject responce",""+arrayList1);
        Log.e("sub subject responce",""+arrayList2);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,////////// subject spinnar
                android.R.layout.simple_spinner_item, arrayList1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity.this,// sub subject spiner
                android.R.layout.simple_spinner_item, arrayList2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subsubject.setAdapter(adapter2);



    }
}
