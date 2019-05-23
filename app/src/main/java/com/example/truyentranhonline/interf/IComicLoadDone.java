package com.example.truyentranhonline.interf;

import com.example.truyentranhonline.model.Comic;

import java.util.List;

public interface IComicLoadDone {
    void onComicLoadDoneListener(List<Comic>comics);
}
