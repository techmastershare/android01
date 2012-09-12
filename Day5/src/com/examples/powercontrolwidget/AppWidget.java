package com.examples.powercontrolwidget;

import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction()==null) 
			context.startService(new Intent(context, ToggleService.class));
		else 
			super.onReceive(context, intent);
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	public static class ToggleService extends IntentService {
		public ToggleService() {
			super("AppWidget$ToggleService");
		}

		@Override
		protected void onHandleIntent(Intent intent) {
			 ComponentName me=new ComponentName(this, AppWidget.class);
			 AppWidgetManager mgr=AppWidgetManager.getInstance(this);
			 mgr.updateAppWidget(me, buildUpdate(this));
		}

		private RemoteViews buildUpdate(Context context) {
			RemoteViews updateViews=new RemoteViews(context.getPackageName(),R.layout.widget);

			//Wifi
			WifiManager wifiManager = (WifiManager) context.getSystemService(Activity.WIFI_SERVICE);
			if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED) {
				updateViews.setImageViewResource(R.id.wifi_state, R.drawable.wifi_on);
				wifiManager.setWifiEnabled(false);
			}
			else
			{
				updateViews.setImageViewResource(R.id.wifi_state, R.drawable.wifi_on);
				wifiManager.setWifiEnabled(true);
			}
			
						
			Intent i=new Intent(this, AppWidget.class);
			PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
			updateViews.setOnClickPendingIntent(R.id.wifi_state, pi);
			
			return updateViews; 
		}
	}

}
