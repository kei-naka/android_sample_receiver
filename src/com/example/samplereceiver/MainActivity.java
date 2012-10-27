package com.example.samplereceiver;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        register();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(listener);
    }

    private OnClickListener listener = new OnClickListener() {
    	@Override
    	public void onClick (View view) {
    		Intent intent = new Intent();
    		intent.setAction("gomiAction");
    		sendBroadcast(intent);
    	}
    };
    
    private void register() {
    	IntentFilter filter = new IntentFilter();
    	filter.addAction(Intent.ACTION_USER_PRESENT);
    	filter.addAction("gomiAction");
    	registerReceiver(receiver, filter);
    }
    
    private BroadcastReceiver receiver = new BroadcastReceiver () {
    	@Override
    	public void onReceive (Context context, Intent intent) {
    		if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
    			Log.d("onCreate", "user present");
    			Toast.makeText(context, "user present", Toast.LENGTH_SHORT).show();
    		} else if ("gomiAction".equals(intent.getAction())) {
    			Log.d("onCreate", "gomiAction");
    			Toast.makeText(context, "gomiAction", Toast.LENGTH_SHORT).show();
    		}
    	}
    };


}
