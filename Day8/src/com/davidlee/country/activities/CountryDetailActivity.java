package com.davidlee.country.activities;

import com.davidlee.country.Config;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class CountryDetailActivity extends Activity {
	
	private WebView mWebView = null;
	private String url = "";
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.country_detail_layout);
		
		Intent i = getIntent();
		url = i.getStringExtra(Config.URL);

		initUI();
	}

	private void initUI() {
		mWebView = (WebView) findViewById(R.id.country_detail_web_view);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl(url);
		
	}
}
