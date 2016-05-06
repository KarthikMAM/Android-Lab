package com.example.karthikmam.dbactivity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Karthik M A M on 20-04-2016.
 */
public class ListItemAdapter extends ArrayAdapter<ListItem> {
    Context appContext;
    ListItem[] objects;

    public ListItemAdapter(Context context, int resource, ListItem[] objects) {
        super(context, resource, objects);

        this.appContext = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(appContext, R.layout.items_list, null);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);

        tvName.setText(objects[position].contactName);
        tvPhone.setText(objects[position].contactNumber);

        return convertView;
    }
}
