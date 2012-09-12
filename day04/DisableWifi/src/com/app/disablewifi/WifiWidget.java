package com.app.disablewifi;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.RemoteViews;

public class WifiWidget extends AppWidgetProvider {
	
	@Override
	public void onReceive(Context ctxt, Intent intent) {
		if (intent.getAction() == null) {
			ctxt.startService(new Intent(ctxt, ToggleService.class));
		} else {
			super.onReceive(ctxt, intent);
		}
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		context.startService(new Intent(context, ToggleService.class));
	}

	public static class ToggleService extends IntentService {

		public ToggleService() {
			super("WifiWidget$ToggleService");
		}

		private RemoteViews buildUpdate(Context context) {
			RemoteViews updateViews = new RemoteViews(context.getPackageName(),
					R.layout.widget);

			WifiManager wifi;
			wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

			if (wifi.isWifiEnabled()) {
				wifi.setWifiEnabled(false);
				updateViews.setImageViewResource(R.id.wifiState,
						R.drawable.wifi);
			} else {
				wifi.setWifiEnabled(true);
				updateViews.setImageViewResource(R.id.wifiState,
						R.drawable.icon_ow_1);
			}
			Intent i = new Intent(this, WifiWidget.class);
			PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
			updateViews.setOnClickPendingIntent(R.id.wifiState, pi);

			return updateViews;
		}

		@Override
		protected void onHandleIntent(Intent intent) {
			// TODO Auto-generated method stub
			ComponentName me = new ComponentName(this, WifiWidget.class);
			AppWidgetManager mgr = AppWidgetManager.getInstance(this);
			mgr.updateAppWidget(me, buildUpdate(this));
		}
	}

}
