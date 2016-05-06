package com.example.karthikmam.ex01;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etName, etDOB, etCollege, etYear;
    Spinner spDepartment;
    RadioButton rbMale, rbFemale;
    RadioGroup rgGender;

    DatePickerDialog datePickerDialog;

    final public static String NAME = "com.example.karthikmam.ex01.Name";
    final public static String DOB = "com.example.karthikmam.ex01.DOB";
    final public static String COLLEGE = "com.example.karthikmam.ex01.College";
    final public static String YEAR = "com.example.karthikmam.ex01.Year";
    final public static String DEPARTMENT = "com.example.karthikmam.ex01.Department";
    final public static String GENDER = "com.example.karthikmam.ex01.Gender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etDOB = (EditText) findViewById(R.id.etDOB);
        etCollege = (EditText) findViewById(R.id.etCollege);
        etYear = (EditText) findViewById(R.id.etYear);
        spDepartment = (Spinner) findViewById(R.id.spDepartment);
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
    }

    public void btClick(View view) {
        switch (view.getId()) {
            case R.id.btReset:
                etName.setText("");
                etDOB.setText("");
                etCollege.setText("");
                etYear.setText("");
                spDepartment.setSelection(0);
                rbMale.setChecked(false);
                rbFemale.setChecked(false);
                break;
            case R.id.etDOB:
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etDOB.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                    }
                }, 2016, 1, 5);
                datePickerDialog.setTitle("Select DoB");
                datePickerDialog.show();
                break;
            case R.id.btOk:
                Intent newActivity = new Intent(getApplicationContext(), DetailsActivity.class);
                newActivity.putExtra(NAME, etName.getText().toString());
                newActivity.putExtra(DOB, etDOB.getText().toString());
                newActivity.putExtra(YEAR, etYear.getText().toString());
                newActivity.putExtra(COLLEGE, etCollege.getText().toString());
                newActivity.putExtra(DEPARTMENT, spDepartment.getSelectedItem().toString());
                newActivity.putExtra(GENDER, ((RadioButton) findViewById(rgGender.getCheckedRadioButtonId())).getText());
                startActivity(newActivity);
                break;
        }
    }
}
