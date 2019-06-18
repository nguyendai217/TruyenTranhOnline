package com.example.truyentranhonline.activity;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truyentranhonline.R;
import com.example.truyentranhonline.adapter.NoteAdapter;
import com.example.truyentranhonline.model.Note;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewNote;
    NoteAdapter noteAdapter;
    List<Note> noteList = new ArrayList<>();
    FloatingActionButton fabNote;
    DatabaseReference mreference;
    FirebaseAuth mAuth;

    EditText edtNote, edtUpdate;
    Button btnSave, btnUpdate, btnDelete;
    String noteUp, post_key;
    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mAuth = FirebaseAuth.getInstance();
        mreference = FirebaseDatabase.getInstance().getReference().child("Note");
        initViews();
        controls();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar_note);
        recyclerViewNote = findViewById(R.id.recyclerView_note);
        fabNote = findViewById(R.id.fab_add);

        // thiet lap toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ghi chú");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // thiet lap recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewNote.setLayoutManager(layoutManager);

    }

    private void controls() {
        fabNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(NoteActivity.this).create();
                LayoutInflater layoutInflater = LayoutInflater.from(NoteActivity.this);
                View view = layoutInflater.inflate(R.layout.add_note, null, false);
                edtNote = view.findViewById(R.id.edt_add_note);
                btnSave = view.findViewById(R.id.btn_add);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String edt_note = edtNote.getText().toString();

                        if (edt_note == null) {
                            Toast.makeText(NoteActivity.this, "Thêm ghi chú thất bại !", Toast.LENGTH_SHORT).show();
                        } else {
                            String id = mreference.push().getKey();
                            Note note1 = new Note(id, edt_note);
                            mreference.child(id).setValue(note1);
                            Toast.makeText(NoteActivity.this, "Thêm ghi chú thành công !", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }
                });
                alertDialog.setView(view);
                alertDialog.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<Note, MyViewHolder> adapter = new FirebaseRecyclerAdapter<Note, MyViewHolder>(
                Note.class, R.layout.item_note, MyViewHolder.class, mreference
        ) {
            @Override
            protected void populateViewHolder(MyViewHolder viewHolder, final Note model, final int position) {
                viewHolder.setNote(model.getNote());

                viewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        post_key= getRef(position).getKey();
                        noteUp=model.getNote();
                        updateData();
                    }
                });
            }
        };
        recyclerViewNote.setAdapter(adapter);
    }

    private void updateData() {
        final AlertDialog alertDialog = new AlertDialog.Builder(NoteActivity.this).create();
        final LayoutInflater layoutInflater = LayoutInflater.from(NoteActivity.this);
        final View view = layoutInflater.inflate(R.layout.update_note, null, false);

        edtUpdate = view.findViewById(R.id.edt_update);
        btnUpdate = view.findViewById(R.id.btn_update);
        btnDelete = view.findViewById(R.id.btn_dele);

        edtUpdate.setText(noteUp);
        edtUpdate.setSelection(noteUp.length());// gan title moi vao text

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteUp = edtUpdate.getText().toString();

                Note note = new Note(post_key,noteUp);
                mreference.child(post_key).setValue(note);
                Toast.makeText(NoteActivity.this, "Update thành công!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAlertDialog();
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }

    private void DeleteAlertDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Thông báo !");
        builder.setMessage("Bạn có muốn xóa ghi chú ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mreference.child(post_key).removeValue();
                Toast.makeText(NoteActivity.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        View myview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myview = itemView;
        }
        public void setNote(String note) {
            TextView tvNote = myview.findViewById(R.id.tv_note);
            tvNote.setText(note);
        }
    }

}
