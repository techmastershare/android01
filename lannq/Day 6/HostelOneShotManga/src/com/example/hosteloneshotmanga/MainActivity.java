package com.example.hosteloneshotmanga;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	private ArrayList<Bitmap> listImages = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);
		listImages = new ArrayList<Bitmap>();
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_02));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_03));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_04));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_05));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_06));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_07));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_08));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_09));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_10));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_11));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_12));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_13));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_14));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_15));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_16));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_17));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_18));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_19));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_20));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_21));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_22));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_23));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_24));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_25));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_26));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_27));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_28));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_29));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_30));
		listImages.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.hotel_31));
		// listImages.add(BitmapFactory.decodeResource(getResources(),
		// R.drawable.hotel_32));
		// listImages.add(BitmapFactory.decodeResource(getResources(),
		// R.drawable.hotel_33));
		// listImages.add(BitmapFactory.decodeResource(getResources(),
		// R.drawable.hotel_34));
		// listImages.add(BitmapFactory.decodeResource(getResources(),
		// R.drawable.hotel_chu_thich));

		PageCurlView pageCurlView = new PageCurlView(getApplicationContext());
		this.setContentView(pageCurlView);
		pageCurlView.RenderPage(listImages, getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_view, menu);
		return true;
	}
}
