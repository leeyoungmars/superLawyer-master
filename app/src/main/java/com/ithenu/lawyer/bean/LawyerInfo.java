package com.ithenu.lawyer.bean;

import java.io.Serializable;

public class LawyerInfo implements Serializable {
    private int id;
    private String number;
    private String passWord;
    private String phone;
    private int level;
    private String photo;
    private String name;
    private String time;
    private String province;
    private String city;
    private String office;
    private String expertise;
    private String helpPeople;
    private String answer;
    private String info;
    private String map;
    private String token;

    public LawyerInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public LawyerInfo(String number, String passWord, String phone, int level, String photo, String name,
                      String time, String province, String city, String office, String expertise, String helpPeople,
                      String answer, String info, String map, String token) {
        super();
        this.number = number;
        this.passWord = passWord;
        this.phone = phone;
        this.level = level;
        this.photo = photo;
        this.name = name;
        this.time = time;
        this.province = province;
        this.city = city;
        this.office = office;
        this.expertise = expertise;
        this.helpPeople = helpPeople;
        this.answer = answer;
        this.info = info;
        this.map = map;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getHelpPeople() {
        return helpPeople;
    }

    public void setHelpPeople(String helpPeople) {
        this.helpPeople = helpPeople;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LawyerInfo [id=" + id + ", number=" + number + ", passWord=" + passWord + ", phone=" + phone
                + ", level=" + level + ", photo=" + photo + ", name=" + name + ", time=" + time + ", province="
                + province + ", city=" + city + ", office=" + office + ", expertise=" + expertise + ", helpPeople="
                + helpPeople + ", answer=" + answer + ", info=" + info + ", map=" + map + ", token=" + token + "]";
    }

}
