package com.footprynt.footprynt;

public class Category {
    private String category,color;
    private int image;

    public Category(String category) {
        this.category = category;
    }

    public Category(String category,int image,String color) {
        this.image = image;
        this.color = color;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public int getImage(){
        return image;
    }

    public String getColor() {
        return color;
    }


}
