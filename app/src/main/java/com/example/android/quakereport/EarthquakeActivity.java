/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.quakereport.Adapters.ListDataAdapter;
import com.example.android.quakereport.util.EarthquakeData;
import com.example.android.quakereport.util.QueryUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    public static ListView earthquakeListView;
    public static final String urlString = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.

        /*earthquakes.add(new EarthquakeData("4.5","San Francisco","Feb, 20 2017"));
        earthquakes.add(new EarthquakeData("7.0","London","Feb, 20 2017"));
        earthquakes.add(new EarthquakeData("6.7","Tokyo","Feb, 20 2017"));
        earthquakes.add(new EarthquakeData("4.9","Mexico City","Feb, 20 2017"));
        earthquakes.add(new EarthquakeData("5.0","Moscow","Feb, 20 2017"));
        earthquakes.add(new EarthquakeData("2.5","Rio de Janeiro","Feb, 20 2017"));
        earthquakes.add(new EarthquakeData("3.7","Paris","Feb, 20 2017"));*/



        // Find a reference to the {@link ListView} in the layout
         earthquakeListView= (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        EarthquakeAsyncTask getData = new EarthquakeAsyncTask();
        getData.execute(urlString);

    }

    public static void updateUI(ArrayList<EarthquakeData> data)
    {
        Log.d(LOG_TAG,"Update UI");
        earthquakeListView.setAdapter(new ListDataAdapter(data));
    }

    private class EarthquakeAsyncTask extends AsyncTask<String,Void,List<EarthquakeData>>
    {

        @Override
        protected List<EarthquakeData> doInBackground(String... strings) {
            ArrayList<EarthquakeData> earthquakes=null;
            try {
                if(strings.length<1 || strings[0]==null)
                    return null;
                String response = QueryUtils.makeHttpRequest(strings[0]);
                earthquakes = QueryUtils.extractEarthquakes(response);
            }
            catch (IOException e)
            {
             e.printStackTrace();
            }
            return earthquakes;
        }

        @Override
        protected void onPostExecute(List<EarthquakeData> earthquakes) {
         if(earthquakes==null)
             return;
            updateUI((ArrayList<EarthquakeData>) earthquakes);
        }
    }
}
