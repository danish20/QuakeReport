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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.quakereport.Adapters.ListDataAdapter;
import com.example.android.quakereport.util.EarthquakeData;
import com.example.android.quakereport.util.QueryUtils;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<EarthquakeData> earthquakes = new ArrayList<>();
        /*earthquakes.add(new EarthquakeData("4.5","San Francisco","Feb, 20 2017"));
        earthquakes.add(new EarthquakeData("7.0","London","Feb, 20 2017"));
        earthquakes.add(new EarthquakeData("6.7","Tokyo","Feb, 20 2017"));
        earthquakes.add(new EarthquakeData("4.9","Mexico City","Feb, 20 2017"));
        earthquakes.add(new EarthquakeData("5.0","Moscow","Feb, 20 2017"));
        earthquakes.add(new EarthquakeData("2.5","Rio de Janeiro","Feb, 20 2017"));
        earthquakes.add(new EarthquakeData("3.7","Paris","Feb, 20 2017"));*/

        earthquakes = QueryUtils.extractEarthquakes();
        System.out.println("LOG::Dann"+earthquakes.size());

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(new ListDataAdapter(earthquakes));
    }
}
