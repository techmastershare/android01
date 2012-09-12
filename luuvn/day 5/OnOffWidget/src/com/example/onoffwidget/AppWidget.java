package com.example.onoffwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.widget.RemoteViews;
import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;

public class AppWidget extends AppWidgetProvider {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction() == null || intent.getAction() == "sound"
				|| intent.getAction() == "wifi") {
			Intent intentRec = new Intent(context, ToggleService.class);
			intentRec.setAction(intent.getAction());
			context.startService(intentRec);
		} else
			super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		context.startService(new Intent(context, ToggleService.class));
	}

	public static class ToggleService extends IntentService {
		public ToggleService() {
			super("AppWidget$ToggleService");
		}

		@Override
		protected void onHandleIntent(Intent intent) {
			ComponentName componentName = new ComponentName(this,
					AppWidget.class);
			AppWidgetManager appWidgetMnr = AppWidgetManager.getInstance(this);
			appWidgetMnr.updateAppWidget(componentName,
					buildUpdate(this, intent));
		}

		private RemoteViews buildUpdate(Context context, Intent intent) {
			RemoteViews updateViews = new RemoteViews(context.getPackageName(),
					R.layout.widget);

			if (intent.getAction() == "sound") {
				AudioManager audioManager = (AudioManager) context
						.getSystemService(Activity.AUDIO_SERVICE);
				if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
					updateViews.setImageViewResource(R.id.phone_state,
							R.drawable.phone_state_normal);
					audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				} else {
					updateViews.setImageViewResource(R.id.phone_state,
							R.drawable.phone_state_silent);
					audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
				}
			}

			if (intent.getAction() == "wifi") {
				WifiManager wifiManager = (WifiManager) context
						.getSystemService(Activity.WIFI_SERVICE);
				if (wifiManager.isWifiEnabled()) {
					updateViews.setImageViewResource(R.id.wifi_state,
							R.drawable.wifi_off);
					wifiManager.setWifiEnabled(false);
				} else {
					updateViews.setImageViewResource(R.id.wifi_state,
							R.drawable.wifi_on);
					wifiManager.setWifiEnabled(true);
				}
			}

			Intent intentSound = new Intent(this, AppWidget.class);
			intentSound.setAction("sound");
			PendingIntent pendingIntentSound = PendingIntent.getBroadcast(
					context, 0, intentSound, 0);
			updateViews.setOnClickPendingIntent(R.id.phone_state,
					pendingIntentSound);

			Intent intentWifi = new Intent(this, AppWidget.class);
			intentWifi.setAction("wifi");
			PendingIntent pendingIntentWifi = PendingIntent.getBroadcast(
					context, 0, intentWifi, 0);
			updateViews.setOnClickPendingIntent(R.id.wifi_state,
					pendingIntentWifi);

			return updateViews;
		}
	}
}
