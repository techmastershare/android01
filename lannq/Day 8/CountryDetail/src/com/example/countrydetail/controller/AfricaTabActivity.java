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

public class AfricaTabActivity extends Activity {
	private CountriesView mCountriesView = null;
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
		mCountryListModel.add(new CountryItem("Cameroon", "Africa", R.drawable.cameroon));
		mCountryListModel.add(new CountryItem("Nigeria", "Africa", R.drawable.nigeria));
		mCountryListModel.add(new CountryItem("Togo", "Africa", R.drawable.togo));
		mCountryListModel.add(new CountryItem("South Africa", "Africa", R.drawable.south_africa));
		mCountryListModel.add(new CountryItem("Algeria", "Africa", R.drawable.algeria));
		
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
