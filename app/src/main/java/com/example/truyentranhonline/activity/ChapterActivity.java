package com.example.truyentranhonline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.truyentranhonline.Common;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.adapter.ChapterAdapter;
import com.example.truyentranhonline.model.Comic;

public class ChapterActivity extends AppCompatActivity {
    RecyclerView recyclerChapter;
    Toolbar toolbar;
    ChapterAdapter chapterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        initViews();
        controls();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar_chapter);
        recyclerChapter = findViewById(R.id.recyclerview_chapter);
    }

    private void controls() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chapter");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerChapter.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerChapter.setLayoutManager(layoutManager);

        fetchChapter(Common.commicSelected);
    }

    private void fetchChapter(Comic commicSelected) {
        Common.chapterList = commicSelected.Chapters;
        if (commicSelected.Chapters != null) {
            if (commicSelected.Chapters.size() > 0) {
                chapterAdapter = new ChapterAdapter(getBaseContext(), commicSelected.Chapters);
                recyclerChapter.setAdapter(chapterAdapter);
            }
        } else {
            Toast.makeText(this, " Empty Chapter ", Toast.LENGTH_SHORT).show();
        }
    }
}
