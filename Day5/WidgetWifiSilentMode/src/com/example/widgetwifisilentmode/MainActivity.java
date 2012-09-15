package com.example.widgetwifisilentmode;

import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private AudioManager mAudioManager;
	private boolean mPhoneIsSilent;

	private WifiManager mWifimanager;
	private boolean mWifiIsOn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);

		mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		checkIfPhoneIsSilent();
		setButtonClickListener();
		Log.d("SilentModeApp", "This is a test");

		mWifimanager = (WifiManager) getSystemService(WIFI_SERVICE);
		checkWifieIsOn();
		setButtonWifilClickListener();

	}

	private void checkWifieIsOn() {
		if (mWifimanager.isWifiEnabled()) {
			mWifiIsOn = true;
		} else {
			mWifiIsOn = false;
		}
	}

	private void setButtonWifilClickListener() {
		Button toggleButton = (Button) findViewById(R.id.wifi_button);
		toggleButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (mWifiIsOn) {
					mWifimanager.setWifiEnabled(true);

					mWifiIsOn = false;
				} else {

					mWifimanager.setWifiEnabled(false);

					mWifiIsOn = true;
				}

				wifiUi();
			}
		});
	}

	private void wifiUi() {
		ImageView imageView = (ImageView) findViewById(R.id.phone_icon);
		Drawable newPhoneImage;

		if (mWifiIsOn) {
			newPhoneImage = getResources().getDrawable(R.drawable.wifi_on);
		} else {
			newPhoneImage = getResources().getDrawable(R.drawable.wifi_off);
		}
		imageView.setImageDrawable(newPhoneImage);
	}

	private void checkIfPhoneIsSilent() {
		int ringerMode = mAudioManager.getRingerMode();
		if (ringerMode == AudioManager.RINGER_MODE_SILENT)
			mPhoneIsSilent = true;
		else
			mPhoneIsSilent = false;
	}

	private void setButtonClickListener() {
		Button toggleButton = (Button) findViewById(R.id.toggleButton);
		toggleButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (mPhoneIsSilent) {
					// Change back to normal mode
					mAudioManager
							.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
					mPhoneIsSilent = false;
				} else {
					// Change to silent mode
					mAudioManager
							.setRingerMode(AudioManager.RINGER_MODE_SILENT);
					mPhoneIsSilent = true;
				}
				// Now toggle the UI again
				toggleUi();
			}
		});
	}

	private void toggleUi() {
		ImageView imageView = (ImageView) findViewById(R.id.phone_icon);
		Drawable newPhoneImage;

		if (mPhoneIsSilent) {
			newPhoneImage = getResources().getDrawable(R.drawable.phone_silent);
		} else {
			newPhoneImage = getResources().getDrawable(R.drawable.phone_on);
		}
		imageView.setImageDrawable(newPhoneImage);
	}

	@Override
	protected void onResume() {

		super.onResume();
		checkIfPhoneIsSilent();
		toggleUi();
		 checkWifieIsOn();
		 wifiUi();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_view, menu);
		return true;
	}
}
