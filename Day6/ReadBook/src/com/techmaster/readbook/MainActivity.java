package com.techmaster.readbook;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;

public class MainActivity extends Activity {

	private ArrayList<Bitmap> mPages = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);

		mPages = new ArrayList<Bitmap>();
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d1));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d2));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d3));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d4));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d5));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d6));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d7));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d8));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d9));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d10));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d11));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d12));

		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d13));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d14));

		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d15));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d16));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d17));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d18));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d19));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d21));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d22));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d23));
		mPages.add(BitmapFactory.decodeResource(getResources(), R.drawable.d24));

		PageCurlView pagesView = (PageCurlView) findViewById(R.id.pages_view);
		pagesView.setPageList(mPages);

		// render pages
		pagesView.render();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_view, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
