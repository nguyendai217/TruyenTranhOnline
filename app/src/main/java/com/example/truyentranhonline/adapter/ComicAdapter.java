package com.example.truyentranhonline.adapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.truyentranhonline.Common;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.activity.DetailComicActivity;
import com.example.truyentranhonline.interf.OnClickListenerComic;
import com.example.truyentranhonline.model.Comic;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewHolder> {
    Context context;
    List<Comic> comicList;

    public ComicAdapter(Context context, List<Comic> comicList) {
        this.context = context;
        this.comicList = comicList;
    }
    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.comic_item, viewGroup, false);
        return new ComicViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicViewHolder comicViewHolder, int i) {

        final String tvName= comicList.get(i).Name;
        final String tvCategory="Thể Loại: "+comicList.get(i).Category ;
        final String url_image= comicList.get(i).Image;
        final String description= comicList.get(i).Description;

        Picasso.get().load(url_image).into(comicViewHolder.imvComic);
        comicViewHolder.tvNameComic.setText(tvName);
        comicViewHolder.tvCategory.setText(tvCategory);


        comicViewHolder.setItemClickListener(new OnClickListenerComic() {
            @Override
            public void OnClick(View view, int position) {
                Common.commicSelected = comicList.get(position);
                //Toast.makeText(context, "Click item comic", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailComicActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Bundle bundle= new Bundle();
                bundle.putString("image",url_image);
                bundle.putString("name",tvName);
                bundle.putString("category",tvCategory);
                bundle.putString("description",description);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    public class ComicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imvComic;
        TextView tvNameComic, tvCategory;

        OnClickListenerComic itemClickListener;

        public void setItemClickListener(OnClickListenerComic itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameComic = itemView.findViewById(R.id.tv_name);
            imvComic = itemView.findViewById(R.id.imv_comic);
            tvCategory=itemView.findViewById(R.id.tv_category1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.OnClick(v, getAdapterPosition());
        }
    }
}
