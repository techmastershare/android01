package com.example.pizzalist;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
	ViewPager mViewpager = null;
	CategoryAdapter mCategoryAdapter = null;
	Button mNextButton = null;
	Button mPrevButton = null;
	public static final int NUM_CAT = 3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);
		initUI();

		mNextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int current = mViewpager.getCurrentItem();
				mViewpager.setCurrentItem(current + 1);
			}
		});

		mPrevButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int current = mViewpager.getCurrentItem();
				mViewpager.setCurrentItem(current - 1);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_view, menu);
		return true;
	}

	protected void initUI() {
		mCategoryAdapter = new CategoryAdapter(getSupportFragmentManager());
		mViewpager = (ViewPager) findViewById(R.id.pager);
		mViewpager.setAdapter(mCategoryAdapter);

		mNextButton = (Button) findViewById(R.id.next_button);
		mPrevButton = (Button) findViewById(R.id.prev_button);
	}
}
