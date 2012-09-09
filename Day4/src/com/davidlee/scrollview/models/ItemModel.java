package com.davidlee.scrollview.models;

import java.util.ArrayList;

public class ItemModel {

	private ArrayList<ItemEntity> mItemList  = new ArrayList<ItemEntity>();
	
	public void add(ItemEntity item) {
		mItemList.add(item);
	}
	
	public ItemEntity get(int index) {
		if (index < 0)
			return null;
		return mItemList.get(index);
	}
	
	public boolean remove (ItemEntity item) {
		return mItemList.remove(item);
	}
	
	public int count() {
		return mItemList.size();
	}
	
	public void clear() {
		mItemList.clear();
	}
	
}
