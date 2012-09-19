package com.example.slideimageview;

import java.util.ArrayList;

import com.seedotech.horizontallistviewdemo.customviews.HorizontalListView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private HorizontalListView mImageListView = null;
	private ArrayList<ImageContent> mArrayListImage = null;
	private MyCustomAdapter mCustomAdapter = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mImageListView = (HorizontalListView) findViewById(R.id.list_view);

		mArrayListImage = new ArrayList<ImageContent>();

		int imageId = 0;
		for (int i = 0; i < 30; i++) {
			imageId = getImageIdByNumber(i % 7);
			mArrayListImage.add(new ImageContent("Name " + i, imageId));
		}

		mCustomAdapter = new MyCustomAdapter(this, mArrayListImage);
		mImageListView.setAdapter(mCustomAdapter);
	}

	protected int getImageIdByNumber(int number) {
		switch (number) {
		case 0:
			return R.drawable.cat;
		case 1:
			return R.drawable.cat_on_grass;
		case 2:
			return R.drawable.cat_sleep;
		case 3:
			return R.drawable.dog;
		case 4:
			return R.drawable.dog1;
		case 5:
			return R.drawable.dog2;
		case 6:
			return R.drawable.animal_hugging;
		default:
			return R.drawable.animal_hugging;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
