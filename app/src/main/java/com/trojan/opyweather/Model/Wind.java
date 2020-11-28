package com.trojan.opyweather.Model;

import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    double speed;

    @SerializedName("deg")
    double deg;

    public Wind(double speed, double deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }
}
