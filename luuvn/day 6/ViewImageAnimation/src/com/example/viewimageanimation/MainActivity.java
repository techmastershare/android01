package com.example.viewimageanimation;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;

public class MainActivity extends Activity {

	private ArrayList<Bitmap> listResource = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);

		listResource = new ArrayList<Bitmap>();

		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_1));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_2));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_3));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_4));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_5));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_6));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_7));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_8));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_9));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_10));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_11));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_12));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_13));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_14));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_15));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_16));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_17));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_17));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_18));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_19));
		listResource.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.image_20));
		

		PageCurlView pageCurlView = new PageCurlView(getApplicationContext(),
				listResource);
		setContentView(pageCurlView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
