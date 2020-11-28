package com.trojan.opyweather.Model;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("main")
    String main;

    @SerializedName("description")
    String description;

    @SerializedName("id")
    int id;

    @SerializedName("icon")
    String icon;

    public Weather(String main, String description, int id, String icon) {
        this.main = main;
        this.description = description;
        this.id = id;
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
