package com.example.karthikmam.ex05;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SQLiteDatabase dataBase = openOrCreateDatabase("contactsDb", MODE_PRIVATE, null);
        dataBase.execSQL("create table if not exists contacts(name VARCHAR, address VARCHAR, phone VARCHAR, email VARCHAR);");
        dataBase.close();
    }

    public void btClick(View view) {
        Class<?> targetActivity = null;
        switch (view.getId()) {
            case R.id.btDelete:
                targetActivity = DeleteActivity.class;
                break;
            case R.id.btDisplay:
                targetActivity = DisplayActivity.class;
                break;
            case R.id.btInsert:
                targetActivity = InsertActivity.class;
                break;
            case R.id.btModify:
                targetActivity = ModifyActivity.class;
                break;
        }

        Intent intent = new Intent(getApplicationContext(), targetActivity);
        startActivity(intent);
    }
}
