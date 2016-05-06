package com.example.karthikmam.ex06;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by Karthik M A M on 25-04-2016.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    Activity startActivity;
    Item[] items;

    public ItemAdapter(Context context, int resource, Item[] objects) {
        super(context, resource, objects);

        startActivity = (Activity) context;
        items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = startActivity
                    .getLayoutInflater()
                    .inflate(R.layout.item_list, null);
        }

        ((TextView) convertView.findViewById(R.id.tvFrom)).setText(items[position].from);
        ((TextView) convertView.findViewById(R.id.tvBody)).setText(items[position].body);

        return convertView;
    }
}
