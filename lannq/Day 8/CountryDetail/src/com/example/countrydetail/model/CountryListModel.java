package com.example.countrydetail.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CountryListModel {
	private ArrayList<CountryItem> mCountryList = new ArrayList<CountryItem>();

	public void add(final CountryItem countryItem) {
		mCountryList.add(countryItem);
	}

	public CountryItem get(final int index) {
		if (index < 0)
			return null;
		return mCountryList.get(index);
	}

	public boolean remove(final CountryItem countryItem) {
		return mCountryList.remove(countryItem);
	}

	public int count() {
		return mCountryList.size();
	}

	public void clear() {
		mCountryList.clear();
	}

	public void sortByName() {
		Collections.sort(mCountryList, new Comparator<CountryItem>() {
			public int compare(CountryItem o1, CountryItem o2) {
				String name1 = o1.getName();
				String name2 = o2.getName();
				return name1.compareToIgnoreCase(name2);
			}
		});
	}
}
