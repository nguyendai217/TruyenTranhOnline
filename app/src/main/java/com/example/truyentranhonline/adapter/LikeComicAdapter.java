package com.example.truyentranhonline.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.truyentranhonline.R;
import com.example.truyentranhonline.model.Comic;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LikeComicAdapter extends RecyclerView.Adapter<LikeComicAdapter.ComicViewHolder> {
    List<Comic>comicList;
    Context context;

    public LikeComicAdapter(List<Comic> comicList, Context context) {
        this.comicList = comicList;
        this.context = context;
    }
    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.comic_item,viewGroup,false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicViewHolder comicViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    public class ComicViewHolder extends RecyclerView.ViewHolder {
        ImageView imvComic;
        TextView tvName,tvCategory;
        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            imvComic= itemView.findViewById(R.id.imv_comic);
            tvName=itemView.findViewById(R.id.tv_name);
            tvCategory=itemView.findViewById(R.id.tv_category1);
        }
    }
}
