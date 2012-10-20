package com.example.shareimageapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shareimageapp.R;
import com.example.shareimageapp.model.Contact;

public class ContactView extends LinearLayout {

	public TextView mName_textView;
	public TextView mIp_textView;
	private Contact mContact;


	public ContactView(Context context) {
		super(context);
		LayoutInflater li = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.contact_model, this);

		// Init UI
		mName_textView = (TextView) findViewById(R.id.name_textView);
	}
	
	public void updateView() {
		mName_textView.setText(mContact.getName());
	}


	public Contact getContact() {
		return mContact;
	}

	public void setContact(Contact contact) {
		this.mContact = contact;
	}
	
	

}
