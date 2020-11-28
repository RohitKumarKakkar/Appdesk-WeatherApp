package com.trojan.opyweather.Model;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    double temp;

    @SerializedName("feels_like")
    double feels_like;

    @SerializedName("pressure")
    double pressure;

    @SerializedName("humidity")
    int humidity;

    public Main(double temp, double feels_like, double pressure, int humidity) {
        this.temp = temp;
        this.feels_like = feels_like;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
