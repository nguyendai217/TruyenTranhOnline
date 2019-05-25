package com.example.truyentranhonline;

import com.example.truyentranhonline.model.Chapter;
import com.example.truyentranhonline.model.Comic;

import java.util.ArrayList;
import java.util.List;

public class Common {

    public static List<Comic> listComic= new ArrayList<>();
    public static Comic commicSelected;
    public static List<Chapter> chapterList= new ArrayList<>();
    public static Chapter chapterSelected;
    public static int chapterIndex=-1;

    public static String formatString(String name) {
        StringBuilder stringBuilder= new StringBuilder(name.length()>15?name.substring(0,15)+"...":name);
        return stringBuilder.toString();
    }
}
