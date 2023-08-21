package com.myself.supercart;

public class CustomItem {

    private int imageResource;
    private String text, text2;

    public CustomItem(int imageResource, String text, String text2) {
        this.imageResource = imageResource;
        this.text = text;
        this.text2 = text2;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }
    public String getText2(){
        return text2;
    }

}
