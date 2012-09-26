package com.example.countrydetail.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.countrydetail.R;
import com.example.countrydetail.model.CountryItem;
import com.example.countrydetail.model.CountryListAdapter;
import com.example.countrydetail.model.CountryListModel;
import com.example.countrydetail.view.CountriesView;

public class AsiaTabActivity extends Activity {
	
	private CountriesView mCountriesView;
	private CountryListModel mCountryListModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCountriesView = new CountriesView(this);
		setContentView(mCountriesView);
		initList();
	}
	
	protected void initList() {		
		mCountryListModel = new CountryListModel();
		mCountryListModel.add(new CountryItem("Viet Nam", "Asia", R.drawable.vietnam));
		mCountryListModel.add(new CountryItem("Japan", "Asia", R.drawable.japan));
		mCountryListModel.add(new CountryItem("Korea", "Asia", R.drawable.korea));
		mCountryListModel.add(new CountryItem("Singapore", "Asia", R.drawable.singapore));
		mCountryListModel.add(new CountryItem("India", "Asia", R.drawable.india));
		
		ListView countryListView = mCountriesView.countryListView;
		CountryListAdapter countryListAdapter = new CountryListAdapter(this, mCountryListModel);
		countryListView.setAdapter(countryListAdapter);
		
		countryListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				int activityID = 1233;
				Intent intent;
				intent = new Intent(view.getContext(), CountryDetailActivity.class);
				intent.putExtra("country", mCountryListModel.get(pos).getName());
				startActivityForResult(intent, activityID);
			}
		});
	}
}
