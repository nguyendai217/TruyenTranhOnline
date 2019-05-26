package com.example.truyentranhonline.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.truyentranhonline.Common;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.adapter.ComicAdapter;
import com.example.truyentranhonline.model.Comic;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerViewSearch;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = findViewById(R.id.toolbar_search);
        recyclerViewSearch = findViewById(R.id.recycler_search);
        recyclerViewSearch.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewSearch.setLayoutManager(layoutManager);
        showSearchDialog();
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showSearchDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SearchActivity.this);
        alertDialog.setTitle("Search");
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_search, null);
        final EditText edtSearch = view.findViewById(R.id.edt_search);
        alertDialog.setView(view);
        alertDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("Tìm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fetchComic(edtSearch.getText().toString());
            }
        });
        alertDialog.show();
        edtSearch.setFocusable(true);
    }

    private void fetchComic(String query) {
        List<Comic> comicSearch = new ArrayList<>();
        for (Comic comic : Common.listComic) {
            if (comic.Name.contains(query)) {
                comicSearch.add(comic);
            }
        }
        if (comicSearch.size() > 0) {
            recyclerViewSearch.setAdapter(new ComicAdapter(getBaseContext(), comicSearch));
        } else {
            Toast.makeText(this, "Không có truyện !", Toast.LENGTH_SHORT).show();
        }

    }
}
