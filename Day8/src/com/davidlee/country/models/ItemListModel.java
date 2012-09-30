package com.davidlee.country.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.davidlee.country.common.ItemList;

public class ItemListModel {

	private ArrayList<ItemList> mItemArrayList  = new ArrayList<ItemList>();
	
	public void add(final ItemList itemList) {
		mItemArrayList.add(itemList);
	}

	public ItemList get(final int index) {
		if (index < 0)
			return null;
		return mItemArrayList.get(index);
	}

	public boolean remove(final ItemList itemList) {
		return mItemArrayList.remove(itemList);
	}

	public int count() {
		return mItemArrayList.size();
	}
	
	public void clear() {
		mItemArrayList.clear();
	}
	
	public void sortByName() {
		Collections.sort(mItemArrayList, new Comparator<ItemList>() {
			public int compare(ItemList o1, ItemList o2) {
				String name1 = o1.getName();
				String name2 = o2.getName();
				return name1.compareToIgnoreCase(name2);
			}
		});
	}
}
