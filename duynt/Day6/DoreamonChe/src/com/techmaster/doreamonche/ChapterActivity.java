package com.techmaster.doreamonche;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import com.techmaster.doreamonche.models.ChapterModel;
import com.techmaster.doreamonche.views.ChapterView;

public class ChapterActivity extends Activity {
	private ChapterView mChapterView = null;
	private ArrayList<ChapterModel> mChapterModelList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chapter_layout);
		initUI();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.gc();
		finish();
	}

	public void initUI() {
		ChapterModel model1 = new ChapterModel(1, Common.CHAPTER_1);
		ChapterModel model2 = new ChapterModel(2, Common.CHAPTER_2);
		ChapterModel model3 = new ChapterModel(3, Common.CHAPTER_3);
		mChapterModelList = new ArrayList<ChapterModel>();
		mChapterModelList.add(model1);
		mChapterModelList.add(model2);
		mChapterModelList.add(model3);

		mChapterView = new ChapterView(this, mChapterModelList);
		mChapterView.initUI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.app.Activity#onConfigurationChanged(android.content.res.Configuration
	 * )
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		mChapterView.onConfigurationChanged(newConfig);
	}

}