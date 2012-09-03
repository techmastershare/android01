package com.example.imageslideapp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Gallery;

public class MainViewActivity extends Activity {

	private Gallery mListImageListView = null;
	private ArrayList<ListImageItem> mListImage = null;
	private ImagesAdapter mImagesAdapter = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mListImageListView = (Gallery) findViewById(R.id.horizontalListView);

		mListImage = new ArrayList<ListImageItem>();
		for (int i = 1; i < 15; i++) {
			mListImage.add(new ListImageItem("Images " + i));
		}
		
		mImagesAdapter = new ImagesAdapter(this, mListImage);
		mListImageListView.setAdapter(mImagesAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
