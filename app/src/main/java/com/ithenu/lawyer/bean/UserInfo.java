package com.ithenu.lawyer.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private int id;
    private String photo;
    private String userName;
    private String passWord;
    private String phone;
    private String name;
    private String token;

    public UserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserInfo(String photo, String userName, String passWord, String phone, String name, String token) {
        super();
        this.photo = photo;
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.name = name;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserInfo [id=" + id + ", photo=" + photo + ", userName=" + userName + ", passWord=" + passWord
                + ", phone=" + phone + ", name=" + name + ", token=" + token + "]";
    }

}
