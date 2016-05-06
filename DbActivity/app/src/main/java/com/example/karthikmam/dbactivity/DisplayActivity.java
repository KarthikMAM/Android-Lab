package com.example.karthikmam.dbactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;

public class DisplayActivity extends AppCompatActivity {

    ListItem[] list = new ListItem[40];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        try {
            DbHelper dbHelper = new DbHelper(getApplicationContext());

            dbHelper.insertData(new ListItem("Karthik", "9487907066"));
            dbHelper.insertData(new ListItem("Shanmuga", "9487907066"));

            dbHelper.updateData(new ListItem("Kar", "9487907066"));

            ListItem[] listItems = dbHelper.getData();
            Toast.makeText(this, listItems.length + "", Toast.LENGTH_LONG).show();
            for(int i=0; i<listItems.length; i++) {
                listItems[i] = new ListItem(listItems[i].contactName, listItems[i].contactNumber);
            }


            ListItemAdapter arrayAdapter = new ListItemAdapter(getApplicationContext(), R.layout.items_list, listItems);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(
                            getApplicationContext(),
                            ((TextView) findViewById(R.id.tvName)).getText(),
                            Toast.LENGTH_LONG
                    ).show();
                }
            });

        } catch (Exception e) {
            Log.d("Error from " + e.getCause(), e.getMessage());
            e.printStackTrace();
        }
    }
}
