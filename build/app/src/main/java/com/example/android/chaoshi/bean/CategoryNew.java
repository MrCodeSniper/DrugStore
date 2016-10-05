package com.example.android.chaoshi.bean;



public class CategoryNew  {

    protected   long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public CategoryNew() {
    }

    public CategoryNew(String name) {

        this.name = name;
    }

    public CategoryNew(long id , String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
