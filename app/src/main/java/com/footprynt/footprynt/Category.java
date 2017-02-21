package com.footprynt.footprynt;

public class Category {
    private String category,color;
    private int image;

    public Category() {
    }

    public Category(String category) {
        this.category = category;
    }

    public Category(String category,int image, String color){
        this.category = category;
        this.image = image;
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String name) {
        this.category = name;
    }

    public int getImage() {  return image; }

    public String getColor() {  return color; }


}
