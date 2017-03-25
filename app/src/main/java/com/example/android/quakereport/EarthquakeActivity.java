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

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;



import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.quakereport.Adapters.ListDataAdapter;
import com.example.android.quakereport.util.EarthquakeData;
import com.example.android.quakereport.util.QueryUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthquakeData>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    public static ListView earthquakeListView;
    public TextView empty;
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
        empty = (TextView)findViewById(R.id.empty);
        earthquakeListView.setEmptyView(empty);

        getLoaderManager().initLoader(0,null,this);

        // Create a new {@link ArrayAdapter} of earthquakes
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface

    }


    @Override
    public Loader<List<EarthquakeData>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(this,urlString);
    }

    @Override
    public void onLoadFinished(Loader<List<EarthquakeData>> loader, List<EarthquakeData> earthquakeDatas) {
        empty.setText("No Earthquake Data to Display");
        earthquakeListView.setAdapter(new ListDataAdapter((ArrayList<EarthquakeData>)earthquakeDatas));
    }

    @Override
    public void onLoaderReset(Loader<List<EarthquakeData>> loader) {

        earthquakeListView.setAdapter(new ListDataAdapter(new ArrayList<EarthquakeData>()));

    }


    private static class EarthquakeLoader extends AsyncTaskLoader<List<EarthquakeData>>
    {
        String url;
       public EarthquakeLoader(Context context, String url)
       {
           super(context);
           this.url=url;
       }
        @Override
        public List<EarthquakeData> loadInBackground() {
            ArrayList<EarthquakeData> earthquakes=null;
            try
            {

                String response = QueryUtils.makeHttpRequest(url);
                earthquakes = QueryUtils.extractEarthquakes(response);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return earthquakes;
        }

        @Override
        protected void onStartLoading() {
            forceLoad();
        }
    }
}
