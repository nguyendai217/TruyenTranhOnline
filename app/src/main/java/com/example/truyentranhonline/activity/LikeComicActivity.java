package com.example.truyentranhonline.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.truyentranhonline.Common;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.adapter.ComicAdapter;
import com.example.truyentranhonline.model.Comic;

import java.util.ArrayList;
import java.util.List;

public class LikeComicActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_comic);

        toolbar= findViewById(R.id.toolbar_likeComic);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Truyện đã thích");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
