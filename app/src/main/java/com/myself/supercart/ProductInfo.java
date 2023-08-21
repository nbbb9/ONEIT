package com.myself.supercart;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductInfo {

    FirebaseDatabase database;
    DatabaseReference mRef;

    private int imageResource;
    String product;
    int quantity, price;

    public ProductInfo(){ }

    public ProductInfo(int imageResource, String product, int price ,int quantity) {
        this.imageResource = imageResource;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getProduct() {
        return product;
    }
    public void setProduct(String product){
        this.product = product;
    }
    public int getPrice(){ return price; }
    public void setPrice(int price) { this.price = price; }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

}
