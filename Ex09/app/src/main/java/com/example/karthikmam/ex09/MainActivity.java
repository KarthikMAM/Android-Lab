package com.example.karthikmam.ex09;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private String FILE_PATH = "";
    private final String FILE_NAME = "temp.txt";

    private TextView tvContents;
    private EditText etPayload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new File(Environment.getExternalStorageDirectory() + "/ReadWrite/").mkdir();
        FILE_PATH = Environment.getExternalStorageDirectory() + "/ReadWrite/" + FILE_NAME;

        tvContents = (TextView) findViewById(R.id.tvContents);
        etPayload = (EditText) findViewById(R.id.etPayload);
    }

    public void btnClick(View view) {

        switch (view.getId()) {
            case R.id.btAppend:
                try {
                    FileOutputStream fileOutput = new FileOutputStream(FILE_PATH, true);
                    fileOutput.write(etPayload.getText().toString().getBytes());
                    fileOutput.close();
                } catch (Exception e) {
                    Log.d("Error", "Append: " + e.getMessage());
                    e.printStackTrace();
                }
                break;
            case R.id.btClear:
                try {
                    FileOutputStream fileOutput = new FileOutputStream(FILE_PATH, false);
                    fileOutput.write("".getBytes());
                    fileOutput.close();
                } catch (Exception e) {
                    Log.d("Error", "Clear: " + e.getMessage());
                    e.printStackTrace();
                }
                break;
            case R.id.btRead:
                try {
                    File file = new File(FILE_PATH);
                    FileInputStream fileInput = new FileInputStream(file);

                    byte[] fileContents = new byte[(int) file.length()];
                    fileInput.read(fileContents);
                    tvContents.setText(new String(fileContents, "UTF-8"));

                    fileInput.close();
                } catch (Exception e) {
                    Log.d("Error", "Read: " + e.getMessage());
                    e.printStackTrace();
                }
                break;
            case R.id.btWrite:
                try {
                    FileOutputStream fileOutput = new FileOutputStream(FILE_PATH, false);
                    fileOutput.write(etPayload.getText().toString().getBytes());
                    fileOutput.close();
                } catch (Exception e) {
                    Log.d("Error", "Write: " + e.getMessage());
                    e.printStackTrace();
                }
                break;
        }
    }
}
