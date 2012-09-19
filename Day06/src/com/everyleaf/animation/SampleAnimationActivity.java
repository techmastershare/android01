package com.everyleaf.animation;

import com.everyleaf.animation.R;
import com.letgo.model.PageFactory;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SampleAnimationActivity extends Activity {

	public static Bitmap mImageDefault = null;
	
	
      @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.setContentView(R.layout.main);

        }

        @Override
        public void onDestroy(){
            super.onDestroy();
            System.gc();
            finish();
        }
}