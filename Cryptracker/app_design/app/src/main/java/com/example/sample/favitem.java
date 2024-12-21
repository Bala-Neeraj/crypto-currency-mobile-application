package com.example.sample;

public class favitem {
    private String name;
    private String value;
    private String rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public favitem(String name, String value, String rate) {


    this.name=name;
        this.value=value;
        this.rate=rate;
}
}
