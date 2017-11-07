package com.yathams.weather.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by reddve5 on 7/14/17.
 */

public class WeatherResponse {
    public Coord coord;
    public List<Weather> weather = new ArrayList<>();
    public  Main main;
    public Wind wind;
    public Sys sys;
    public int id;
    public int cod = 0;
    public String name = "";
    public int cnt = 0;
    public long dt;
    public String message = "";


    /**
     * This method format the date milliseconds into Monday 31 Jan 2017
     * @return
     */
    public String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd MMM yyyy");
        return simpleDateFormat.format(new Date(dt*1000));
    }
}