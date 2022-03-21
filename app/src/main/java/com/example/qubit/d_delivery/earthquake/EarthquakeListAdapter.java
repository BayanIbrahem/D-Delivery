package com.example.qubit.d_delivery.earthquake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.qubit.d_delivery.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class EarthquakeListAdapter extends ArrayAdapter<EarthquakeEntry> {
    public EarthquakeListAdapter(Context context, int resource, List<EarthquakeEntry> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listViewItem = convertView;
        if(listViewItem == null){
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.listview_item, null);
        }

        EarthquakeEntry earthquake = getItem(position);

        TextView mag = listViewItem.findViewById(R.id.listviewItem_mag);
        TextView place = listViewItem.findViewById(R.id.listview_item_place);
        TextView date = listViewItem.findViewById(R.id.listview_item_date);

        double magnitude = earthquake.getMagnitude();
        DecimalFormat format = new DecimalFormat("0.0");
        format.setRoundingMode(RoundingMode.CEILING);
        //string formatter.

        mag.setText(format.format(magnitude));
        place.setText(earthquake.getPlace());
        date.setText(earthquake.getFormattedDate());

        switch ((int)earthquake.getMagnitude()){
            case 0: mag.setBackground(getContext().getDrawable(R.drawable.mag_bg1));
                break;
            case 1: mag.setBackground(getContext().getDrawable(R.drawable.mag_bg2));
                break;
            case 2: mag.setBackground(getContext().getDrawable(R.drawable.mag_bg3));
                break;
            case 3: mag.setBackground(getContext().getDrawable(R.drawable.mag_bg4));
                break;
            case 4: mag.setBackground(getContext().getDrawable(R.drawable.mag_bg5));
                break;
            case 5: mag.setBackground(getContext().getDrawable(R.drawable.mag_bg6));
                break;
            case 6: mag.setBackground(getContext().getDrawable(R.drawable.mag_bg7));
                break;
            case 7: mag.setBackground(getContext().getDrawable(R.drawable.mag_bg8));
                break;
            case 8: mag.setBackground(getContext().getDrawable(R.drawable.mag_bg9));
                break;
            default: mag.setBackground(getContext().getDrawable(R.drawable.mag_bg0));
        }

        return listViewItem;
    }
}
