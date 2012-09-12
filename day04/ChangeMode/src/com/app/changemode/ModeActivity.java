package com.app.changemode;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ModeActivity extends Activity {

	private AudioManager mAudioManager;
	// private WifiManager wifiManager;
	private boolean mPhoneIsSilent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget);

		mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		// wifiManager = (WifiManager)getSystemService(WIFI_SERVICE);

		checkIfPhoneIsSilent();

		setButtonClickListener();

		Log.d("SilentModeApp", "This is a test");
	}

	private void setButtonClickListener() {
		Button toggleButton = (Button) findViewById(R.id.toggleButton);
		toggleButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (mPhoneIsSilent) {
					// Change back to normal mode
					mAudioManager
							.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
					// wifiManager.setWifiEnabled(true);
					mPhoneIsSilent = false;
				} else {
					// Change to silent mode
					mAudioManager
							.setRingerMode(AudioManager.RINGER_MODE_SILENT);
					// wifiManager.setWifiEnabled(false);
					mPhoneIsSilent = true;
				}

				// Now toggle the UI again
				toggleUi();
			}
		});
	}

	/**
	 * Checks to see if the phone is currently in silent mode.
	 */
	private void checkIfPhoneIsSilent() {
		int ringerMode = mAudioManager.getRingerMode();

		if (ringerMode == AudioManager.RINGER_MODE_SILENT) {
			mPhoneIsSilent = true;
		} else {
			mPhoneIsSilent = false;
		}
		// int wifiState = wifiManager.getWifiState();
		// if(wifiState == WifiManager.WIFI_STATE_DISABLED){
		// mPhoneIsSilent = true;
		// }else{
		// mPhoneIsSilent = false;
		// }

	}

	/**
	 * Toggles the UI images from silent to normal and vice versa.
	 */
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
	};

}
