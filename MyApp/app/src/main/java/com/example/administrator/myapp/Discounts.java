package com.example.administrator.myapp;

public class Discounts {
    private int price,type;
    private String title,data;
    public static int GET_IMMEDIATELY=0;
    public static int GET_SATISFY=1;
    public Discounts(){}

    public Discounts(int price, int type, String title, String data) {
        this.price = price;
        this.type = type;
        this.title = title;
        this.data = data;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
