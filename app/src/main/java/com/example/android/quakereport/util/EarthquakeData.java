package com.example.android.quakereport.util;

/**
 * Created by Danish on 3/24/17.
 */

public class EarthquakeData
{

    private String location, date, magnitude, time;

    public EarthquakeData(String mag, String loc, String date, String time)
    {
        this.date=date;
        this.location=loc;
        this.magnitude=mag;
        this.time = time;

    }

    public String getMagnitude()
    {
        return this.magnitude;
    }
    public String getLocation()
    {
        return this.location;
    }
    public String getDate()
    {
        return this.date;
    }
    public String getTime()
    {
        return this.time;
    }
}
