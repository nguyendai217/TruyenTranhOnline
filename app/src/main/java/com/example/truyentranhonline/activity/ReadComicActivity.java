package com.example.truyentranhonline.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.truyentranhonline.Common;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.adapter.ComicAdapter;
import com.example.truyentranhonline.interf.IComicLoadDone;
import com.example.truyentranhonline.model.Comic;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadComicActivity extends AppCompatActivity implements IComicLoadDone {
    ArrayList<Comic> comicList;
    RecyclerView recyclerViewComic;
    ImageView imvSearch;
    DatabaseReference comics;
    IComicLoadDone comicListener;
    ProgressDialog progressDialog;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_comic);
        comics = FirebaseDatabase.getInstance().getReference("Comic");
        comicListener = this;
        initViews();
        controls();
    }

    private void initViews() {
        recyclerViewComic = findViewById(R.id.recycler_comic);
        toolbar = findViewById(R.id.toolbar_readcomic);
        imvSearch=findViewById(R.id.imv_search);
    }

    private void controls() {
        loadComic();
        recyclerViewComic.setHasFixedSize(true);
        recyclerViewComic.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setTitle("Danh sách truyện");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ReadComicActivity.this, SearchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onComicLoadDoneListener(List<Comic> listComic) {
        Common.listComic = listComic;
        recyclerViewComic.setAdapter(new ComicAdapter(getBaseContext(), listComic));
        progressDialog.dismiss();
    }

    private void loadComic() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        comics.addListenerForSingleValueEvent(new ValueEventListener() {
            List<Comic> comicLoad = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot comicSnapshot : dataSnapshot.getChildren()) {
                    Comic comic = comicSnapshot.getValue(Comic.class);
                    comicLoad.add(comic);
                }
                comicListener.onComicLoadDoneListener(comicLoad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ReadComicActivity.this, " " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
