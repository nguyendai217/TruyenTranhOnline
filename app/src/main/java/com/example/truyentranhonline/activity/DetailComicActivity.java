package com.example.truyentranhonline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truyentranhonline.R;
import com.example.truyentranhonline.model.Like;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DetailComicActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imvComic;
    private TextView tvName, tvCategory, tvDescription;
    private Button btnReadComic;
    private ImageView imvLike;
    DatabaseReference comics, likes;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_comic);
        mAuth= FirebaseAuth.getInstance();
        comics = FirebaseDatabase.getInstance().getReference("Comic");
        likes= FirebaseDatabase.getInstance().getReference("Likes");
        initViews();
        controls();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar_detail);
        imvComic = findViewById(R.id.imv_detail_comic);
        tvName = findViewById(R.id.tv_nameComic);
        tvCategory = findViewById(R.id.tv_category);
        tvDescription = findViewById(R.id.tv_description);
        btnReadComic = findViewById(R.id.btn_read);
        imvLike = findViewById(R.id.imv_like);
    }

    private void controls() {
        final Bundle bundle = getIntent().getExtras();
        final String tv_name = bundle.getString("name");
        final String tv_category = bundle.getString("category");
        String tv_des = bundle.getString("description");
        final String url_image = bundle.getString("image");
        Picasso.get().load(url_image).into(imvComic);
        tvName.setText(tv_name);
        tvCategory.setText(tv_category);
        tvDescription.setText(tv_des);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tv_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReadComic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailComicActivity.this, ChapterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        imvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imvLike.setImageResource(R.drawable.ic_favorite_red_24dp);
                String id= likes.push().getKey();
                Like like= new Like(id,tv_name,url_image,tv_category);
                likes.child(id).setValue(like);
                Toast.makeText(DetailComicActivity.this, "Thêm vào truyên đã thich", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(DetailComicActivity.this,LikeComicActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }

}
