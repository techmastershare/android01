package com.davidlee.country.activities;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Tab host
		TabHost tabHost = getTabHost();

		// Tab for Continent Category
		TabSpec categorySpec = tabHost.newTabSpec("tab1");
		categorySpec.setIndicator("Châu lục");
		Intent categoryIntent = new Intent(this, ContinentActivity.class);
		categorySpec.setContent(categoryIntent);

		// Tab for Search
		TabSpec searchSpec = tabHost.newTabSpec("tab2");
		searchSpec.setIndicator("Tìm kiếm");
		Intent searchIntent = new Intent(this, SearchActivity.class);
		searchSpec.setContent(searchIntent);

		// Add all tabSpec to Tabhost
		tabHost.addTab(categorySpec);
		tabHost.addTab(searchSpec);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
