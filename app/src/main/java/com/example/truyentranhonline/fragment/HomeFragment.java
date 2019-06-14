package com.example.truyentranhonline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.truyentranhonline.activity.LikeComicActivity;
import com.example.truyentranhonline.R;
import com.example.truyentranhonline.activity.NoteActivity;
import com.example.truyentranhonline.activity.ReadComicActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private ImageView imvReadComic, imvLikeComic, imvNote, imvInformation;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        controls();
    }

    private void initViews() {
        imvReadComic = getActivity().findViewById(R.id.imv_reading);
        imvLikeComic = getActivity().findViewById(R.id.imv_booklike);
        imvInformation = getActivity().findViewById(R.id.imv_information);
        imvNote = getActivity().findViewById(R.id.imv_note);
    }

    private void controls() {
        imvReadComic.setOnClickListener(this);
        imvLikeComic.setOnClickListener(this);
        imvInformation.setOnClickListener(this);
        imvNote.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imv_reading:
                Intent intent = new Intent(getContext(), ReadComicActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.imv_booklike:
                Intent intent1 = new Intent(getContext(), LikeComicActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;
            case R.id.imv_information:
                InformationFragment informationFragment = new InformationFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, informationFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.imv_note:
                Intent intent2 = new Intent(getContext(), NoteActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
        }
    }

}
