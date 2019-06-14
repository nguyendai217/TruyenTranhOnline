package com.example.truyentranhonline.model;

public class Like {
    public String id;
    public String name;
    public String imvcomic;
    public String category;

    public Like() {
    }

    public Like(String id, String name, String imvcomic, String category) {
        this.id = id;
        this.name = name;
        this.imvcomic = imvcomic;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImvcomic() {
        return imvcomic;
    }

    public void setImvcomic(String imvcomic) {
        this.imvcomic = imvcomic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
