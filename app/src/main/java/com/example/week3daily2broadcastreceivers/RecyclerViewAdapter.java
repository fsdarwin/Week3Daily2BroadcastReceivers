package com.example.week3daily2broadcastreceivers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<Alblum> alblumArr;

    public RecyclerViewAdapter(ArrayList<Alblum> alblumArr) {
        this.alblumArr = alblumArr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Alblum alblum = alblumArr.get(position);
        if(alblum != null){

            Glide.with(viewHolder.itemView.getContext())
                    .load(alblum.getImage())
                    .into(viewHolder.imgImage);
            viewHolder.tvSong.setText(alblum.getSong());
            viewHolder.tvTitle.setText(alblum.getTitle());
            viewHolder.tvYear.setText(alblum.getYear());
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Declare Views
        ImageView imgImage;
        TextView tvTitle;
        TextView tvSong;
        TextView tvYear;

        //Instanciate Views
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //final Context context = itemView.getContext();
            imgImage = itemView.findViewById(R.id.imgImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvSong = itemView.findViewById(R.id.tvSong);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public int getItemCount() {
        return alblumArr != null ? alblumArr.size() : 0;
    }
}
