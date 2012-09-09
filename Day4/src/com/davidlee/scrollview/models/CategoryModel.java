package com.davidlee.scrollview.models;

import java.util.ArrayList;

public class CategoryModel {

	private ArrayList<CategoryEntity> mCategoryList  = new ArrayList<CategoryEntity>();
	
	public void add(CategoryEntity item) {
		mCategoryList.add(item);
	}
	
	public CategoryEntity get(int index) {
		if (index < 0)
			return null;
		return mCategoryList.get(index);
	}
	
	public boolean remove (CategoryEntity item) {
		return mCategoryList.remove(item);
	}
	
	public int count() {
		return mCategoryList.size();
	}
	
	public void clear() {
		mCategoryList.clear();
	}
}
