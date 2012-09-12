package com.example.onoffwidget;

import android.graphics.drawable.Drawable;
import android.media.AudioManager;
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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	
	    mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);        
	    checkIfPhoneIsSilent();         
	    setButtonClickListener();        
	    Log.d("SilentModeApp", "This is a test");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void setButtonClickListener(){
    	Button toggleButton = (Button) findViewById(R.id.toggle_button);
    	toggleButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if (mPhoneIsSilent) {
					mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
					mPhoneIsSilent = false;
				} else {
					mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
					mPhoneIsSilent = true;
				}
				
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
    		newPhoneImage = getResources().getDrawable(R.drawable.phone_state_normal);
    	}
    	
    	imageView.setImageDrawable(newPhoneImage);
    }

    // Checks to see if the phone is currently in silent mode. 
	private void checkIfPhoneIsSilent() {
		int ringerMode = mAudioManager.getRingerMode();
		if (ringerMode == AudioManager.RINGER_MODE_SILENT) 
			mPhoneIsSilent = true;
		else mPhoneIsSilent = false;
	}
 
	@Override
	protected void onResume() {
		super.onResume();
		checkIfPhoneIsSilent();
		toggleUi();
	};
}
