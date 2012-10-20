package com.example.shareimageapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shareimageapp.R;

public class MainView extends LinearLayout {

	public ListView photoListView;
	public ListView contactListView;
	public ImageButton addPhotoButton;
	public ImageButton removePhotoButton;
	public ImageButton addContactButton;
	public ImageButton removeContactButton;
	public Button shareButton;
	public Button historyButton;
	public TextView infoTextView;

	public MainView(Context context) {
		super(context);
		LayoutInflater li = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.main_view, this);
		
		//Init UI
		photoListView = (ListView) findViewById(R.id.listPhoto_listView);
		contactListView = (ListView) findViewById(R.id.listContact_listView);
		addPhotoButton = (ImageButton) findViewById(R.id.addPhoto_button);
		removePhotoButton = (ImageButton) findViewById(R.id.removePhoto_button);
		addContactButton = (ImageButton) findViewById(R.id.addContact_button);
		removeContactButton = (ImageButton) findViewById(R.id.removeContact_button);
		shareButton = (Button) findViewById(R.id.share_button);
		historyButton = (Button) findViewById(R.id.history_button);
		infoTextView = (TextView) findViewById(R.id.infomation_textView);
	}

}
