package com.example.android.quakereport.util;

/**
 * Created by Danish on 3/24/17.
 */

public class EarthquakeData
{

    private String location, date, magnitude;

    public EarthquakeData(String mag, String loc, String date)
    {
        this.date=date;
        this.location=loc;
        this.magnitude=mag;
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
}
