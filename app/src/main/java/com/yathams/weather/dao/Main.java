package com.yathams.weather.dao;

/**
 * Created by reddve5 on 7/14/17.
 */

public class Main {
    public String temp = "";
    public String temp_min = "";
    public String temp_max = "";
    public String humidity = "";
    public String pressure = "";

    /**
     * This method truncates the decimal points
     * @return
     */
    public String formatTemp(){
        return String.valueOf((int)Float.parseFloat(temp));
    }
}
