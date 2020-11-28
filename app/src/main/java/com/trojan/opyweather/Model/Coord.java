package com.trojan.opyweather.Model;

import com.google.gson.annotations.SerializedName;

public class Coord {
    @SerializedName("lon")
    double lon;

    @SerializedName("lat")
    double lat;

    public Coord(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
