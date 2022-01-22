package com.aerium.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

public class Recyclerview extends RecyclerView.Adapter<Recyclerview.MyViewHolder> {
    private JSONArray jsonArray;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView formula,url;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            formula = (TextView) itemView.findViewById(R.id.listViewFormula);
            url = (TextView) itemView.findViewById(R.id.listViewUrl);
            img = (ImageView) itemView.findViewById(R.id.listViewImage);
        }
    }

    public Recyclerview(JSONArray jsonArray){
        this.jsonArray = jsonArray;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylist,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
