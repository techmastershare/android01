package com.example.countrydetail.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.example.countrydetail.R;

public class DetailView extends LinearLayout {

	public WebView webview = null;
	
	public DetailView(Context context) {
		super(context);
		LayoutInflater li = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.country_detail, this);
		webview = (WebView) findViewById(R.id.countryDetail_webView);
	}
}
