package com.example.wellxa;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewholder> {
    private String[] data;
    public AlarmAdapter(String[] data){
        this.data=data;
    }

    @NonNull
    @Override
    public AlarmViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class AlarmViewholder extends RecyclerView.ViewHolder{

        public AlarmViewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
