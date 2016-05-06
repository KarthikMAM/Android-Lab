package com.example.karthikmam.ex11;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String ALARM = "alarm";
    TimePicker timePicker;
    BroadcastReceiver receiver;
    Ringtone ringTone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
    }

    public void btClick(View view) {
        switch (view.getId()) {
            case R.id.btSet:
                int hour = timePicker.getCurrentHour();
                int min = timePicker.getCurrentMinute();
                Toast.makeText(getApplicationContext(), hour + ":" + min, Toast.LENGTH_LONG).show();

                Intent alarm = new Intent(ALARM);
                receiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                        ringTone = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);
                        ringTone.play();

                        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(getApplicationContext());
                        notifBuilder.setSmallIcon(R.mipmap.ic_launcher);
                        notifBuilder.setContentTitle("Alarm");
                        notifBuilder.setContentText("Please wake up!!!");

                        NotificationManager notifManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notifManager.notify(
                                (int) Math.random(),
                                notifBuilder.build()
                        );
                    }
                };
                registerReceiver(receiver, new IntentFilter(ALARM));
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarm, 0);

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, min);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setInexactRepeating(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY,
                        pendingIntent
                );

                break;
            case R.id.btCancel:
                unregisterReceiver(receiver);
                if(ringTone.isPlaying()) { ringTone.stop(); }
                break;
        }
    }
}
