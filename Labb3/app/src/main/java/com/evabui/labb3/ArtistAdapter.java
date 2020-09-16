package com.evabui.labb3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Inspiration ifrån: https://medium.com/@relferreira/goodbye-listview-recyclerview-f83dc1133850
public class ArtistAdapter extends RecyclerView.Adapter {

    private List<String> artists;

    public ArtistAdapter(List<String> artists) {
        this.artists = artists;
    }
    
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        return new ArtistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String item = this.artists.get(position);
        ((ArtistViewHolder)holder).tv.setText(item);
    }

    @Override
    public int getItemCount() {
        return this.artists.size();
    }

    protected static class ArtistViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv;
        
        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView);
            
        }
    }
}
