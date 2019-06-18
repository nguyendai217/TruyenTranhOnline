package com.example.truyentranhonline.activity;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.truyentranhonline.R;
import com.example.truyentranhonline.adapter.LikeComicAdapter;
import com.example.truyentranhonline.model.Like;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LikeComicActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewLikeComic;
    DatabaseReference likes;
    List<Like> comicLike;
    LikeComicAdapter likeComicAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_comic);

        recyclerViewLikeComic = findViewById(R.id.recyclerview_likeComic);
        likes = FirebaseDatabase.getInstance().getReference("Likes");
        toolbar = findViewById(R.id.toolbar_likeComic);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Truyện đã thích");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // load du lieu hien thi len recyclerview
        recyclerViewLikeComic.setHasFixedSize(true);
        recyclerViewLikeComic.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        comicLike = new ArrayList<>();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        likes.addListenerForSingleValueEvent(new ValueEventListener() { // lay dư lieu tu firebase ve
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Like like = snapshot.getValue(Like.class);
                        comicLike.add(like);
                    }
                    likeComicAdapter = new LikeComicAdapter(comicLike, getBaseContext());
                    recyclerViewLikeComic.setAdapter(likeComicAdapter);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LikeComicActivity.this, "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}
