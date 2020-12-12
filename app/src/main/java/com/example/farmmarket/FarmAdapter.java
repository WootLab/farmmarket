package com.example.farmmarket;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.FarmHolder> {
    private List<Farm> allfarms = new ArrayList<>() ;
    Context context;
    FarmAdapterListener farmAdapterListener;
    public FarmAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public FarmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.farmview,parent,false);
        return new FarmHolder(view);
    }

    public void farmDetail(FarmAdapterListener farmAdapterListener){
        this.farmAdapterListener = farmAdapterListener;
    }

    @Override
    public void onBindViewHolder(@NonNull FarmHolder holder, int position) {

        Farm specFarm = allfarms.get(position);
        holder.title.setText(specFarm.getTitle());
        holder.desc.setText(specFarm.getDescription());
        holder.location.setText(specFarm.getLocation());
        if(specFarm.getImage() != null){
            Glide.with(context)
                    .asBitmap()
                    .placeholder(R.drawable.apple)
                    .centerCrop()
                    .load(Uri.parse(specFarm.getImage()))
                    .into(holder.image);
            return;
        }

        Glide.with(context)
                .asBitmap()
                .placeholder(R.drawable.apple)
                .centerCrop()
                .load(Uri.parse("https://cdn.pixabay.com/photo/2013/11/23/13/57/barn-216372_960_720.jpg"))
                .into(holder.image);

    }

    public void setFarms(List<Farm> allfarms){
        this.allfarms = allfarms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allfarms.size();
    }

    public class FarmHolder extends RecyclerView.ViewHolder {
        TextView location,title,desc;
        Button btnShow;
        ImageView image;
        CardView cardView;
        public FarmHolder(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.locationTV);
            title = itemView.findViewById(R.id.farmTitle);
            desc = itemView.findViewById(R.id.farmDesc);
            btnShow = itemView.findViewById(R.id.buttonMap);
            image = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.card);

            cardView.setOnClickListener(v -> {
                if(farmAdapterListener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        farmAdapterListener.gotoNext(position);
                    }

                }
            });


        }
    }

    public interface FarmAdapterListener{
        void gotoNext(int position);
    }
}
