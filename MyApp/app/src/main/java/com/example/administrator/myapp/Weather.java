package com.example.administrator.myapp;

public class Weather {
    String wdata,wcity,wwendu,wforcast;
    public Weather(){};

    public Weather(String wdata, String wcity, String wwendu, String wforcast) {
        this.wdata = wdata;
        this.wcity = wcity;
        this.wwendu = wwendu;
        this.wforcast = wforcast;
    }

    public String getWdata() {
        return wdata;
    }

    public void setWdata(String wdata) {
        this.wdata = wdata;
    }

    public String getWcity() {
        return wcity;
    }

    public void setWcity(String wcity) {
        this.wcity = wcity;
    }

    public String getWwendu() {
        return wwendu;
    }

    public void setWwendu(String wwendu) {
        this.wwendu = wwendu;
    }

    public String getWforcast() {
        return wforcast;
    }

    public void setWforcast(String wforcast) {
        this.wforcast = wforcast;
    }
}
