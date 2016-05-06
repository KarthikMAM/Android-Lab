package com.example.karthikmam.files;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    int rot = 0;

    DatePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));

        Intent intent = new Intent("send");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(),
                PendingIntent.FLAG_UPDATE_CURRENT,
                intent,
                0
        );
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentText("Message sent");
                builder.setContentTitle("Success!!");

                ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(
                        1,
                        builder.build()
                );
            }
        },
        new IntentFilter("send"));

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("5554", null, "Hello world", pendingIntent, null);

        /*LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(getApplicationContext(), location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);

        File file = new File(Environment.getExternalStorageDirectory() + "/hello.txt");
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write("Hello world".getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Intent intent = new Intent("hello");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(),
                PendingIntent.FLAG_UPDATE_CURRENT,
                intent,
                0
        );
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setContentText("hello");
                builder.setContentTitle("Wl");
                builder.setSmallIcon(R.mipmap.ic_launcher);

                NotificationManager notif = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notif.notify(2, builder.build());

                Ringtone ringtone = RingtoneManager.getRingtone(
                        getApplicationContext(),
                        RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_ALARM)
                );
                ringtone.play();
            }
        }, new IntentFilter("hello"));

        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(System.currentTimeMillis());
        calender.set(Calendar.MINUTE, 15);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calender.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        );


        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("5554", null, "Hello karthik", null, null);

        ((ImageView) findViewById(R.id.imgView)).setImageResource(R.mipmap.ic_launcher);

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(80);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                rot = (rot + 10) % 360;
                                ((ImageView) findViewById(R.id.imgView)).setScaleX(rot / 360.0f);
                                ((ImageView) findViewById(R.id.imgView)).setRotationX(rot);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/
    }

    public class MyView extends View {

        int size = 100;
        int r = 10, g = 20, b = 30;

        public MyView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);

            canvas.drawPaint(paint);
            paint.setColor(Color.rgb(r, g, b));

            canvas.drawCircle(400, 400, size, paint);
            size = (size + 5) % 300;
            r = (r + 5) % 256;
            g = (r + 15) % 256;
            b = (r + 35) % 256;

            invalidate();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
