package com.example.countrydetail.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.countrydetail.R;

public class CountriesView extends LinearLayout {

	public ListView countryListView;

	public CountriesView(Context context) {
		super(context);
		LayoutInflater li = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.country_list, this);
		countryListView = (ListView) findViewById(R.id.country_listView);
	}
}
