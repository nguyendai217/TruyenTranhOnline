package com.example.truyentranhonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.truyentranhonline.Common;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.activity.ViewComicActivity;
import com.example.truyentranhonline.interf.OnClickListenerChapter;
import com.example.truyentranhonline.model.Chapter;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {
    Context context;
    List<Chapter> chapterList;

    public ChapterAdapter(Context context, List<Chapter> chapterList) {
        this.context = context;
        this.chapterList = chapterList;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chapter, viewGroup, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder chapterViewHolder, int i) {
        chapterViewHolder.tvChapter.setText(chapterList.get(i).Name);
        chapterViewHolder.setItemClickListener(new OnClickListenerChapter() {
            @Override
            public void OnClick(View view, int position) {
                Common.chapterSelected = chapterList.get(position);
                //Toast.makeText(context, "Click item chapter", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ViewComicActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvChapter;
        OnClickListenerChapter onClickListenerChapter;

        public void setItemClickListener(OnClickListenerChapter onClickListenerChapter) {
            this.onClickListenerChapter = onClickListenerChapter;
        }

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChapter = itemView.findViewById(R.id.tv_chapter_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListenerChapter.OnClick(v, getAdapterPosition());
        }
    }
}
