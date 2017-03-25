package com.example.android.quakereport.Adapters;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
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

    private int getMagnitudeColor(double magnitude, ViewGroup viewGroup)
    {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(viewGroup.getContext(), magnitudeColorResourceId);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.list_layout,viewGroup,false);
        TextView mag = (TextView) view.findViewById(R.id.mag);
        TextView loc = (TextView) view.findViewById(R.id.loc);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView time = (TextView) view.findViewById(R.id.time);
        LinearLayout back = (LinearLayout)view.findViewById(R.id.back);
        mag.setText(data.get(i).getMagnitude());
        loc.setText(data.get(i).getLocation());
        date.setText(data.get(i).getDate());
        time.setText(data.get(i).getTime());
        GradientDrawable magnitudeCircle = (GradientDrawable) back.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        try {
            int magnitudeColor = getMagnitudeColor(Double.parseDouble(data.get(i).getMagnitude()),viewGroup);
            magnitudeCircle.setColor(magnitudeColor);
        }
        catch (Exception e)
        {

        }

        return view;
    }
}
