package com.brandroid.faster;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class FasterActivity extends Activity {
	private Intent mIntent;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mIntent = getIntent();
        if(mIntent == null)
        	mIntent = new Intent();
        
        ((Button)findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				testNotify();
			}
		});
    }
    
    public void testNotify()
    {
    	Faster.LogIt("Starting service to update status bar...");
		Intent intent = new Intent(this, FasterService.class);
		startService(intent);
		Faster.LogIt("Did it work?");
    }
}