package com.myself.supercart;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserInfoModel {
    public String username;
    public String userpw;
    public String userage;
    public String userheight;
    public String userweight;
    public String usernumber;

    //주의!
    //firebaseDB는 객체 단위로 값을 넣을 때 반드시 매개변수가 비어있는 생성자를 요구한다.
    public UserInfoModel(){}

    public UserInfoModel(String username, String userpw ,String userage, String userheight, String userweight, String usernumber){
        this.username = username;
        this.userpw = userpw;
        this.userage = userage;
        this.userheight = userheight;
        this.userweight = userweight;
        this.usernumber = usernumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpw() {
        return userpw;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }

    public String getUserage() {
        return userage;
    }

    public void setUserage(String userage) {
        this.userage = userage;
    }

    public String getUserheight() {
        return userheight;
    }

    public void setUserheight(String userheight) {
        this.userheight = userheight;
    }

    public String getUserweight() {
        return userweight;
    }

    public void setUserweight(String userweight) {
        this.userweight = userweight;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }

    @Override
    public String toString(){
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", userpw='" + userpw + '\'' +
                ", userage=' " + userage + '\'' +
                ", userheight=" + userheight + '\'' +
                ", userweight=" + userweight + '\'' +
                ", usernumber=" + usernumber +
                '}';
    }
}
