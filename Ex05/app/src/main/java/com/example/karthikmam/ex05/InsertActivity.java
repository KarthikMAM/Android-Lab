package com.example.karthikmam.ex05;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {

    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";

    EditText etName, etAddress, etPhone, etEmail;
    String name, address, phone, email;

    SQLiteDatabase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        etName = (EditText) findViewById(R.id.etName);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etEmail = (EditText) findViewById(R.id.etEmail);
    }

    public void btClick(View view) {
        name = etName.getText().toString();
        address = etAddress.getText().toString();
        phone = etPhone.getText().toString();
        email = etEmail.getText().toString();

        dataBase = openOrCreateDatabase("contactsDb", MODE_PRIVATE, null);

        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(ADDRESS, address);
        values.put(PHONE, phone);
        values.put(EMAIL, email);

        dataBase.insert("contacts", null, values);

        dataBase.close();
    }
}
