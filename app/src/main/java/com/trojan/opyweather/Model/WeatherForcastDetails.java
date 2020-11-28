package com.trojan.opyweather.Model;

import com.trojan.opyweather.Model.City;
import com.trojan.opyweather.Model.MyList;

import java.util.List;

public class WeatherForcastDetails {
    public String cod;
    public double message;
    public int cnt;
    public List<MyList> list;
    public City city;

    public WeatherForcastDetails(String cod, double message, int cnt, List<MyList> list, City city) {
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<MyList> getList() {
        return list;
    }

    public void setList(List<MyList> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
