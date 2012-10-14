package com.davidlee.country.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SearchActivity extends Activity {
	
	private EditText searchEditText = null;
	private Button searchButton = null;
	private ListView countryListView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		
		initUI();
	}

	private void initUI() {
		searchEditText = (EditText) findViewById(R.id.search_text);
		searchButton = (Button) findViewById(R.id.search_button);
		countryListView = (ListView) findViewById(R.id.country_list_view);

		searchEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			public void onFocusChange(View v, boolean hasFocus) {
					String text = searchEditText.getText().toString();
					Toast.makeText(getApplicationContext(), text, text.length()).show();
				
			}
		});
	}
}
