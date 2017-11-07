package com.yathams.weather.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by reddve5 on 7/27/17.
 */

public class Sys {
    public int type = 0;
    public int id = 0;
    public long sunrise = 0;
    public long sunset = 0;
    public String country = "";

    public String getSunriseTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        return simpleDateFormat.format(new Date(sunrise*1000));
    }

    public String getSunsetTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        return simpleDateFormat.format(new Date(sunset*1000));
    }
}
