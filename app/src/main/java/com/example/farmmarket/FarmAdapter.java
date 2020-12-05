package com.example.farmmarket;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.FarmHolder> {
    @NonNull
    @Override
    public FarmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FarmHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FarmHolder extends RecyclerView.ViewHolder {
        public FarmHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
