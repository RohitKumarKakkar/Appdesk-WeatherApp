package com.trojan.opyweather.Model;

import com.trojan.opyweather.Model.Main;
import com.trojan.opyweather.Model.Sys;
import com.trojan.opyweather.Model.Weather;
import com.trojan.opyweather.Model.Wind;

import java.util.List;

public class MyList {
    public int dt;
    public Main main;
    public List<Weather> weather;
    public Wind wind;
    public Sys sys;
    public String dt_txt;


    public MyList(int dt, Main main, List<Weather> weather, Wind wind, Sys sys, String dt_txt) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
        this.wind = wind;
        this.sys = sys;
        this.dt_txt = dt_txt;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
