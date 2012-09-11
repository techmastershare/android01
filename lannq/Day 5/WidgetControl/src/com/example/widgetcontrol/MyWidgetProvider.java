package com.example.widgetcontrol;

import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {
	@Override
	public void onReceive(Context ctxt, Intent intent) {
		if (intent.getAction() == null || intent.getAction() == "update_sound" || intent.getAction() == "update_wifi"){
			Intent i = new Intent(ctxt, ToggleService.class);
			i.setAction(intent.getAction());
			ctxt.startService(i);
		}
		else
			super.onReceive(ctxt, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		context.startService(new Intent(context, ToggleService.class));
	}

	public static class ToggleService extends IntentService {
		public ToggleService() {
			super("MyWidgetProvider$ToggleService");
		}

		@Override
		protected void onHandleIntent(Intent intent) {
			ComponentName me = new ComponentName(this, MyWidgetProvider.class);
			AppWidgetManager mgr = AppWidgetManager.getInstance(this);

			mgr.updateAppWidget(me, buildUpdate(this, intent));

		}

		private RemoteViews buildUpdate(Context context, Intent intent) {
			RemoteViews updateViews = new RemoteViews(context.getPackageName(),
					R.layout.widget);

			if (intent.getAction() == "update_sound") {
				AudioManager audioManager = (AudioManager) context
						.getSystemService(Activity.AUDIO_SERVICE);
				if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
					updateViews.setImageViewResource(
							R.id.sound_switcher_button, R.drawable.sound_on);
					audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				} else {
					updateViews.setImageViewResource(
							R.id.sound_switcher_button, R.drawable.sound_off);
					audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
				}
			}
			if (intent.getAction() == "update_wifi") {
				WifiManager wifiManager = (WifiManager) context.getSystemService(Activity.WIFI_SERVICE);
				if (wifiManager.isWifiEnabled()) {
					updateViews.setImageViewResource(R.id.wifi_switcher_button, R.drawable.wifi_off);
					wifiManager.setWifiEnabled(false);
				} else {
					updateViews.setImageViewResource(R.id.wifi_switcher_button, R.drawable.wifi_on);
					wifiManager.setWifiEnabled(true);
				}
			}

			Intent i = new Intent(this, MyWidgetProvider.class);
			i.setAction("update_sound");
			PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
			updateViews.setOnClickPendingIntent(R.id.sound_switcher_button, pi);
			
			Intent i2 = new Intent(this, MyWidgetProvider.class);
			i2.setAction("update_wifi");
			PendingIntent pi2 = PendingIntent.getBroadcast(context, 0, i2, 0);
			updateViews.setOnClickPendingIntent(R.id.wifi_switcher_button, pi2);

			return updateViews;
		}
	}
}
