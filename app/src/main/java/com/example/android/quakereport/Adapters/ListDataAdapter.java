package com.example.android.quakereport.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.util.EarthquakeData;

import java.util.ArrayList;

/**
 * Created by Danish on 3/24/17.
 */

public class ListDataAdapter extends BaseAdapter
{
    ArrayList<EarthquakeData> data = new ArrayList<>();

    public ListDataAdapter(ArrayList<EarthquakeData> data)
    {
        this.data=data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i*8;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.list_layout,viewGroup,false);
        TextView mag = (TextView) view.findViewById(R.id.mag);
        TextView loc = (TextView) view.findViewById(R.id.loc);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView time = (TextView) view.findViewById(R.id.time);
        mag.setText(data.get(i).getMagnitude());
        loc.setText(data.get(i).getLocation());
        date.setText(data.get(i).getDate());
        time.setText(data.get(i).getTime());
        return view;
    }
}
