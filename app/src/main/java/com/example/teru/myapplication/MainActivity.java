package com.example.teru.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    private static final String ACTION = "hog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter=new IntentFilter();
        filter.addAction(ACTION);
        registerReceiver(myReceiver,filter);

//        Intent i = new Intent(getApplicationContext(), MainActivity.class); // ReceivedActivityを呼び出すインテントを作成
//        i.setAction(ACTION);
//        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, i, 0); // ブロードキャストを投げるPendingIntentの作成
//
//        Calendar calendar = Calendar.getInstance(); // Calendar取得
//        calendar.setTimeInMillis(System.currentTimeMillis()); // 現在時刻を取得
//        calendar.add(Calendar.SECOND, 10); // 現時刻より15秒後を設定
//
//        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE); // AlramManager取得
//        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender); // AlramManagerにPendingIntentを登録

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setAction(ACTION);
        sendBroadcast(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(myReceiver);
    }

    //受信機
    public BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            Toast.makeText(getApplicationContext(),"受信",Toast.LENGTH_SHORT).show();
        }
    };
}
