package com.example.countrydetail.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.example.countrydetail.R;

public class MainView extends TabHost {

	public MainView(Context context) {
		super(context);
		LayoutInflater li = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.main_view, this);
	}
}
