package com.myself.supercart;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ThingsInfoModel {
    public String name;
    public String price;
    public String weight;

    //주의!
    //firebaseDB는 객체 단위로 값을 넣을 때 반드시 매개변수가 비어있는 생성자를 요구한다.
    public ThingsInfoModel(){}

    public ThingsInfoModel(String name, String price, String weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) { this.price = price; }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) { this.weight = weight;}

    @Override
    public String toString(){
        return "Things{" +
                "name='" + name + '\'' +
                ", price=' " + price + '\'' +
                ", weight=" + weight +
                '}';
    }

}