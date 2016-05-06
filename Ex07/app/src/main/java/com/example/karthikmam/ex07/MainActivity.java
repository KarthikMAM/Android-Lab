package com.example.karthikmam.ex07;

import android.graphics.Color;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    int redValue = 0, blueValue = 50, greenValue = 30;
    TextView tvBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBanner = (TextView) findViewById(R.id.tvBanner);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String nextBanneer = tvBanner.getText().toString();
                            nextBanneer = nextBanneer.substring(1) + nextBanneer.charAt(0);
                            tvBanner.setText(nextBanneer);
                            tvBanner.setTextColor(Color.rgb(redValue, greenValue, blueValue));
                        }
                    });

                    redValue = (redValue + 10) % 256;
                    greenValue = (greenValue + 7) % 256;
                    blueValue = (blueValue + 15) % 256;

                    try {
                        Thread.sleep(80);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
