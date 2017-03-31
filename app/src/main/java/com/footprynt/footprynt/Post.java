package com.footprynt.footprynt;

class Post {
    String post;
    boolean isChecked;						//Parameter which says whether the post is checked or not
    Post(String post){
        this.post = post;
    }

    public void setPost(String post){
        this.post = post;
    }

    public String getPost(){
        return this.post;
    }

    public void setChecked(boolean x){
        this.isChecked = x;
    }

    public boolean getChecked(){
        return this.isChecked;
    }

    public void toggleChecked(){
        isChecked = !isChecked;
    }

}