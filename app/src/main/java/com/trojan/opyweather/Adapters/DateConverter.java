package com.trojan.opyweather.Adapters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {


    public static String CovertDate(int dt){
        Date date =new Date(dt*1000L);
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm EEE MM yyyy");
        String formated=sdf.format(date);
        return formated;
    }
}
