package com.techmaster.doreamonche;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;

import com.techmaster.doreamonche.views.PageView;

public class PageActivity extends Activity {
	private PageView mPageView = null;
	private ArrayList<Bitmap> mList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book);
		initUI();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_book, menu);
		return true;
	}

	private void initUI() {
		Intent intent = getIntent();
		int i = intent.getIntExtra(Common.CHAPTER_ID, 1);
		mList = new ArrayList<Bitmap>();

		switch (i) {
		case 1:
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v1));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v2));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v3));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v4));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v5));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v6));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v7));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v8));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v9));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v10));
			break;
		case 2:
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v22));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v12));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v13));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v14));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v15));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v16));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v17));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v18));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v19));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v20));
			break;
		case 3:
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v23));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v24));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v25));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v26));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v27));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v28));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v29));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v30));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v31));
			mList.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.v32));
			break;
		}
		mPageView = new PageView(this, mList);
		mPageView.initUI();
	}

	/**
	 * @return the mPageView
	 */
	public PageView getmPageView() {
		return mPageView;
	}

	/**
	 * @param mPageView
	 *            the mPageView to set
	 */
	public void setmPageView(PageView mPageView) {
		this.mPageView = mPageView;
	}
}
