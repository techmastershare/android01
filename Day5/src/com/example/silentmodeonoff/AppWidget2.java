package com.example.silentmodeonoff;

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

public class AppWidget2 extends AppWidgetProvider {
	@Override
	public void onReceive(Context ctxt, Intent intent) {
		if (intent.getAction() == null) ctxt.startService(new Intent(ctxt, ToggleService2.class));
		else super.onReceive(ctxt, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		context.startService(new Intent(context, ToggleService2.class));
	}

	public static class ToggleService2 extends IntentService {
		public ToggleService2() {
			super("AppWidget2$ToggleService2");
		}

		@Override
		protected void onHandleIntent(Intent intent) {
			ComponentName me = new ComponentName(this, AppWidget2.class);
			AppWidgetManager mgr = AppWidgetManager.getInstance(this);
			mgr.updateAppWidget(me, buildUpdate(this));
		}

		private RemoteViews buildUpdate(Context context) {
			RemoteViews updateViews = new RemoteViews(context.getPackageName(),
					R.layout.widget2);

			WifiManager wifiManager = (WifiManager) context
					.getSystemService(Activity.WIFI_SERVICE);
			if (wifiManager.isWifiEnabled()) {
				updateViews.setImageViewResource(R.id.wirelessState,
						R.drawable.wifi_state_silent);
				wifiManager.setWifiEnabled(false);
			} else {
				updateViews.setImageViewResource(R.id.wirelessState,
						R.drawable.wifi_state_normal);
				wifiManager.setWifiEnabled(true);
			}

			Intent i = new Intent(this, AppWidget2.class);
			PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
			updateViews.setOnClickPendingIntent(R.id.wirelessState, pi);

			return updateViews;
		}
	}
}
