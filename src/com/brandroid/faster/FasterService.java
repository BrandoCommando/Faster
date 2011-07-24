package com.brandroid.faster;

import com.example.android.apis.R;
import com.example.android.apis.app.NotifyingService;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.widget.RemoteViews;

public class FasterService extends Service {
	private final static int TEST_ID = 1;
	private Intent mIntent;
    private NotificationManager mNM;
    
    private final IBinder mBinder = new Binder() {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }
    };

    private Runnable mTask = new Runnable() {
        public void run() {
            Faster.LogIt("Task Running!");
        }
    };
	
	public FasterService() {
		//super("FasterService");
		Faster.LogIt("FasterService Constructor");
	}
	
	@Override
	public void onCreate() {
		mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Faster.LogIt("Creating Service...");
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mIntent = intent;
		Faster.LogIt("Starting Service: " + intent.toString() + " (" + flags + "/" + startId + ")");
		testNotification();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Faster.LogIt("Destroying Service...");
		super.onDestroy();
	}
    
    public void testNotification()
    {
    	Faster.LogIt("testing notification");
    	Intent notifyIntent = new Intent(this, FasterActivity.class);
    	PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notifyIntent, 0);
    	RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification_view);
    	contentView.setImageViewResource(R.id.image, R.drawable.icon);
    	contentView.setTextViewText(R.id.text, "You got Faster! GJ!!!");
    	
    	Notification notify = new Notification();
    	notify.contentView = contentView;
    	notify.contentIntent = contentIntent;
    	
    	startForeground(R.layout.notification_view, notify);
    	
    	NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    	nm.notify(Faster.LOG_KEY, TEST_ID, notify);
    }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}
}
