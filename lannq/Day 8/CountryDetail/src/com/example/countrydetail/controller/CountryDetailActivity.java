package com.example.countrydetail.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.countrydetail.view.DetailView;

public class CountryDetailActivity extends Activity {

	private DetailView mDetailView = null;
	private String mCountry = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDetailView = new DetailView(this);
		setContentView(mDetailView);
		initUI();
	}

	@SuppressLint("SetJavaScriptEnabled")
	protected void initUI() {
		
		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			mCountry = extras.getString("country");
		}
		
		WebView webView = mDetailView.webview;
		webView.setWebViewClient(new WebViewClient());
		webView.setWebChromeClient(new WebChromeClient());
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new JavaScriptInterface(this),
				"JSInterface");
		String url = "file:///android_asset/www/countryDetail.html";
		webView.loadUrl(url);
	}

	public class JavaScriptInterface {
		Context mContext;

		/** Instantiate the interface and set the context */
		JavaScriptInterface(Context c) {
			mContext = c;
		}
		
		public void updateCountry(){
			mDetailView.webview.loadUrl("javascript:countryUpdate('" + mCountry + "')");
		}
	}
}
