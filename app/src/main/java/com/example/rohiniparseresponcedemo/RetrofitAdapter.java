package com.example.rohiniparseresponcedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    ArrayList<ModelRecycler> dataModelArrayList;

    ModelRecycler modelRecycler;


    public RetrofitAdapter(Context ctx, ArrayList<ModelRecycler> dataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }


    @NonNull
    @Override
    public RetrofitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.retro_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RetrofitAdapter.MyViewHolder holder, int position) {
        modelRecycler= dataModelArrayList.get(position);


        holder.name.setText(dataModelArrayList.get(position).getFname());
        holder.country.setText(dataModelArrayList.get(position).getLname());
        holder.city.setText(dataModelArrayList.get(position).getSubject_Name());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectid= String.valueOf(modelRecycler.getId());

                Toast.makeText(v.getContext(),"click on item: "+subjectid, Toast.LENGTH_LONG).show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView country, name, city;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            country = (TextView) itemView.findViewById(R.id.country);
            name = (TextView) itemView.findViewById(R.id.name);
            city = (TextView) itemView.findViewById(R.id.city);



        }
    }
}
