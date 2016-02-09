package com.benjaminpiouffle.podfler;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by piouffb on 09/01/16.
 */
public class PlantWatcherAdapter extends ArrayAdapter<PlantWatcher> {
    private final Context context;
    private final PlantWatcher[] watchers;
    private int selected = 0;

    public PlantWatcherAdapter(Context context, PlantWatcher[] watchers) {
        super(context, -1, watchers);
        this.context = context;
        this.watchers = watchers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
        textView.setText(this.watchers[position].getName());
        if (position == selected)
            rowView.setBackgroundColor(Color.LTGRAY);
        return rowView;
    }

    public void setSelected(int position) {
        this.selected = position;
    }
}