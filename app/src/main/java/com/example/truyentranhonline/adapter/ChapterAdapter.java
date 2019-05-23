package com.example.truyentranhonline.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.truyentranhonline.R;
import com.example.truyentranhonline.model.Chapter;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>  {
     Context context;
     List<Chapter> chapterList;

    public ChapterAdapter(Context context, List<Chapter> chapterList) {
        this.context = context;
        this.chapterList = chapterList;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_chapter,viewGroup,false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder chapterViewHolder, int i) {
        chapterViewHolder.tvChapter.setText(chapterList.get(i).Name);
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvChapter;
        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChapter=itemView.findViewById(R.id.tv_chapter_item);
        }
    }
}
