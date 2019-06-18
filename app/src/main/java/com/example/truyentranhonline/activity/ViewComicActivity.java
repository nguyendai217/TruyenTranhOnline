package com.example.truyentranhonline.activity;

import android.app.ProgressDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.truyentranhonline.Common;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.adapter.ViewPagerAdapter;
import com.example.truyentranhonline.interf.IComicLike;
import com.example.truyentranhonline.model.Chapter;
import com.example.truyentranhonline.model.Like;

import java.util.List;

public class ViewComicActivity extends AppCompatActivity implements IComicLike {
    private ViewPager viewPager;
    ProgressDialog progressDialog;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comic);

        initViews();
        fetchLinks(Common.chapterSelected);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Common.chapterSelected.Name);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar_view);
        viewPager = findViewById(R.id.view_Pager);
    }

    private void fetchLinks(Chapter chapter) {

        if (chapter.Links != null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Please wait..");
            progressDialog.show();
            if (chapter.Links.size() > 0) {
                ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getBaseContext(), chapter.Links);
                viewPager.setAdapter(pagerAdapter);
                progressDialog.dismiss();

            } else {
                Toast.makeText(this, "Chưa có truyện !", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Chưa có truyện !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComicLike(List<Like> ComicLike, int position) {

    }
}
