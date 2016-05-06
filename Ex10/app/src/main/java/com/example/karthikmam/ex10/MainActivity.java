package com.example.karthikmam.ex10;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etPhone;
    EditText etMessage;
    Button btSend;

    private final static String SENT = "sent";
    private final static String DELIVERED = "delivered";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPhone = (EditText) findViewById(R.id.etPhone);
        etMessage = (EditText) findViewById(R.id.etMessage);
        btSend = (Button) findViewById(R.id.btSend);
    }

    public void btClick(View view) {
        String phoneNo = etPhone.getText().toString();
        String msgContent = etMessage.getText().toString();

        try {
            Intent sentIntent = new Intent(SENT);
            PendingIntent sentPIntent = PendingIntent.getBroadcast(
                    getApplicationContext(),
                    0,
                    sentIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );

            registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String title = "Success";
                    String content = "Message delivered";

                    if(getResultCode() != Activity.RESULT_OK) {
                        title = "Failed";
                        content = "Message was not delivered";
                    }

                    NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(getApplicationContext());
                    notifBuilder.setSmallIcon(R.drawable.icon);
                    notifBuilder.setContentTitle(title);
                    notifBuilder.setContentText(content);

                    NotificationManager notifManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notifManager.notify((int) Math.random(), notifBuilder.build());
                }
            }, new IntentFilter(SENT));

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msgContent, sentPIntent, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
