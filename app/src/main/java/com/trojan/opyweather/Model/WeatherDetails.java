package com.trojan.opyweather.Model;

import java.util.List;

public class WeatherDetails {

    private Coord coord;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private int id;
    private int cod;
    private String name;
    private Sys sys;
    private int dt;
    private int timezone;

    public WeatherDetails(){
    }

    public WeatherDetails(Coord coord, List<Weather> weather, Main main, Wind wind, int id, int cod, String name, Sys sys, int dt, int timezone) {
        this.coord = coord;
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.id = id;
        this.cod = cod;
        this.name = name;
        this.sys = sys;
        this.dt = dt;
        this.timezone = timezone;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public WeatherDetails(Sys sys) {
        this.sys = sys;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
