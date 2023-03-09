package com.example.sqldemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    ArrayList<ListDataModel> listDataModelArrayList;
    CustomAdapter(Context context, ArrayList<ListDataModel>listDataModelArrayList){
        this.context=context;
        this.listDataModelArrayList=listDataModelArrayList;
        //Toast.makeText(context, "reached1", Toast.LENGTH_SHORT).show();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView age;
        public ViewHolder(View itemview){

            super(itemview);
            //Toast.makeText(context, "reached2", Toast.LENGTH_SHORT).show();
            name=itemview.findViewById(R.id.nameView);
            age=itemview.findViewById(R.id.ageView);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.itemlayoutview,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Toast.makeText(context, "reached 3", Toast.LENGTH_SHORT).show();
        holder.name.setText(listDataModelArrayList.get(position).getName());
        holder.age.setText(String.valueOf(listDataModelArrayList.get(position).getAge()));
    }

    @Override
    public int getItemCount() {
        return listDataModelArrayList.size();
    }




}
