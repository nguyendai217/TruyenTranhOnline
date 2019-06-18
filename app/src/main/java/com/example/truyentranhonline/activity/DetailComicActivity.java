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
        mAuth= FirebaseAuth.getInstance();   // ket noi toi csdl firebase
        comics = FirebaseDatabase.getInstance().getReference("Comic"); // lấy dữ liệu tại nhánh Comic
        likes= FirebaseDatabase.getInstance().getReference("Likes");  // lấy dữ liệu tại nhánh Likes
        initViews();
        controls();
    }

    // ánh xạ thuộc tính của các controler đã sử dung
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
        final Bundle bundle = getIntent().getExtras();  // dữ liệu từ bên màn hình đọc truyện
        final String tv_name = bundle.getString("name");
        final String tv_category = bundle.getString("category");
        String tv_des = bundle.getString("description");
        final String url_image = bundle.getString("image");
        Picasso.get().load(url_image).into(imvComic);   // sử dụng thư viện picasso để load ảnh
        tvName.setText(tv_name); // gán các dữ liệu đã lấy đc từ màn hình đọc truyện
        tvCategory.setText(tv_category);
        tvDescription.setText(tv_des);

        // thiết lập thông số thanh toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tv_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // setup hiển thị màn hình chapter khi click vào button
        btnReadComic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailComicActivity.this, ChapterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // khai báo cho hệ thống biết chuẩn bị có màn hình mới
                startActivity(intent); // thực hiện chuyển màn hình
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
