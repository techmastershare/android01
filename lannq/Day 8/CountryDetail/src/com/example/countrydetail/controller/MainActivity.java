package com.example.countrydetail.controller;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.example.countrydetail.R;
import com.example.countrydetail.view.MainView;

public class MainActivity extends TabActivity {

	private TabHost mTabHost = null;
	private MainView mMainView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMainView = new MainView(this);
		setContentView(mMainView);
		buildTab();
	}

	protected void buildTab() {
		Resources res = getResources();
		mTabHost = getTabHost();
		
		//Create Intent for tabhost
		Intent intentAsia = new Intent(getApplicationContext(),
				AsiaTabActivity.class);
		Intent intentAfrica = new Intent(getApplicationContext(),
				AfricaTabActivity.class);
		Intent intentEurope = new Intent(getApplicationContext(),
				EuropeTabActivity.class);
		
		
		//Indicate Tab
		String tabName = res.getString(R.string.asia);
		TabSpec tabSpec = mTabHost.newTabSpec(tabName);
		tabSpec.setIndicator(tabName, null);
		tabSpec.setContent(intentAsia);
		mTabHost.addTab(tabSpec);
		
		tabName = res.getString(R.string.africa);
		tabSpec = mTabHost.newTabSpec(tabName);
		tabSpec.setIndicator(tabName, null);
		tabSpec.setContent(intentAfrica);
		mTabHost.addTab(tabSpec);
		
		tabName = res.getString(R.string.euro);
		tabSpec = mTabHost.newTabSpec(tabName);
		tabSpec.setIndicator(tabName, null);
		tabSpec.setContent(intentEurope);
		mTabHost.addTab(tabSpec);
	}
}
