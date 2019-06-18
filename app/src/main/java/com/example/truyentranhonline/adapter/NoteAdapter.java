package com.example.truyentranhonline.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.truyentranhonline.R;
import com.example.truyentranhonline.model.Note;


import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    List<Note>listNote;
    Context context;

    public NoteAdapter(List<Note> listNote, Context context) {
        this.listNote = listNote;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_note,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvNote.setText(listNote.get(i).getNote());
    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNote=itemView.findViewById(R.id.tv_note);
        }
    }
}
