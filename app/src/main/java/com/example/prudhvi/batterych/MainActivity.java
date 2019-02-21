package com.example.prudhvi.batterych;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private TextView t1;
private ProgressBar pb;
private BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadcastReceiver=new Myreceiver();
        t1=(TextView)findViewById(R.id.t1);
        pb=(ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter obj=new IntentFilter();
        obj.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(broadcastReceiver,obj);
    }

    @Override
    protected void onStop() {
        if(broadcastReceiver!=null)
            unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    class Myreceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
int level=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
pb.setProgress(level);
        }
    }
}
