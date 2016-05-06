package com.example.karthikmam.ex01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView tvName, tvDOB, tvGender, tvYear, tvDepartment, tvCollege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvName = (TextView) findViewById(R.id.tvName);
        tvDOB = (TextView) findViewById(R.id.tvDOB);
        tvGender = (TextView) findViewById(R.id.tvGender);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvDepartment = (TextView) findViewById(R.id.tvDepartment);
        tvCollege = (TextView) findViewById(R.id.tvCollege);

        Intent startIntent = getIntent();
        tvName.setText(startIntent.getStringExtra(MainActivity.NAME));
        tvDOB.setText(startIntent.getStringExtra(MainActivity.DOB));
        tvGender.setText(startIntent.getStringExtra(MainActivity.GENDER));
        tvYear.setText(startIntent.getStringExtra(MainActivity.YEAR));
        tvDepartment.setText(startIntent.getStringExtra(MainActivity.DEPARTMENT));
        tvCollege.setText(startIntent.getStringExtra(MainActivity.COLLEGE));
    }
}
