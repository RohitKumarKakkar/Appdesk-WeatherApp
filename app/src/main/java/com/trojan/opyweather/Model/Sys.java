package com.trojan.opyweather.Model;

import com.google.gson.annotations.SerializedName;

public class Sys {
    @SerializedName("country")
    String country;

    @SerializedName("sunrise")
    int sunrise;

    public Sys(String country, int sunrise) {
        this.country = country;
        this.sunrise = sunrise;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }
}
