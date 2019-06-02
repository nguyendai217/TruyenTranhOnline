package com.example.truyentranhonline.activity;
import android.app.ProgressDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truyentranhonline.Common;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.adapter.ViewPagerAdapter;
import com.example.truyentranhonline.model.Chapter;

public class ViewComicActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imvNext, imvPrev;
    private TextView tvChapterName;
    private ViewPager viewPager;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comic);

        initViews();
        controls();
    }

    private void initViews() {
        imvNext = findViewById(R.id.imv_next);
        imvPrev = findViewById(R.id.imv_prev);
        tvChapterName = findViewById(R.id.tv_ChapterName);
        viewPager = findViewById(R.id.view_Pager);
    }

    private void controls() {
        imvNext.setOnClickListener(this);
        imvPrev.setOnClickListener(this);
        fetchLinks(Common.chapterSelected);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imv_next:
                if (Common.chapterIndex == Common.chapterList.size() - 1) {
                    Toast.makeText(ViewComicActivity.this, "Bạn đã đọc đến phần cuối..", Toast.LENGTH_SHORT).show();
                } else {
                    Common.chapterIndex++;
                    fetchLinks(Common.chapterList.get(Common.chapterIndex));
                }
                break;
            case R.id.imv_prev:
                if (Common.chapterIndex == 0) {
                    Toast.makeText(ViewComicActivity.this, "Bạn đang đọc phần đầu tiên.", Toast.LENGTH_SHORT).show();
                } else {
                    Common.chapterIndex--;
                    fetchLinks(Common.chapterList.get(Common.chapterIndex));
                }
                break;
        }
    }

    private void fetchLinks(Chapter chapter) {

        if (chapter.Links != null) {
            progressDialog= new ProgressDialog(this);
            progressDialog.setTitle("Please wait..");
            progressDialog.show();
            if (chapter.Links.size() > 0) {
                ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getBaseContext(), chapter.Links);
                viewPager.setAdapter(pagerAdapter);
                progressDialog.dismiss();
                tvChapterName.setText(Common.formatString(Common.chapterSelected.Name));
            } else {
                Toast.makeText(this, "Chapter trống", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Chapter trống", Toast.LENGTH_SHORT).show();
        }
    }
}
