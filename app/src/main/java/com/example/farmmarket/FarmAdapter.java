package com.example.farmmarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.FarmHolder> {
    private List<Farm> allfarms = new ArrayList<>() ;
    @NonNull
    @Override
    public FarmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.farmview,parent,false);
        return new FarmHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmHolder holder, int position) {

        Farm specFarm = allfarms.get(position);
        holder.title.setText(specFarm.getTitle());
        holder.desc.setText(specFarm.getDescription());
        holder.location.setText(specFarm.getLocation());

    }

    public void setFarms(List<Farm> allfarms){
        this.allfarms = allfarms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allfarms.size();
    }

    public static class FarmHolder extends RecyclerView.ViewHolder {
        TextView location,title,desc;
        Button btnShow;
        ImageView image;
        public FarmHolder(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.locationTV);
            title = itemView.findViewById(R.id.farmTitle);
            desc = itemView.findViewById(R.id.farmDesc);
            btnShow = itemView.findViewById(R.id.buttonMap);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
