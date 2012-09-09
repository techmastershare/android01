package com.example.pizzalist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

	private int mNumberPage = 0;
	
	public CategoryAdapter(FragmentManager fm) {
		super(fm);
		this.mNumberPage = MainActivity.NUM_CAT;
	}
	
	

	public CategoryAdapter(FragmentManager fm, int mNumberPage) {
		super(fm);
		this.mNumberPage = mNumberPage;
	}



	@Override
	public Fragment getItem(int pos) {
		// TODO Auto-generated method stub
		return ListCategory.newInstance(pos);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mNumberPage;
	}

}
