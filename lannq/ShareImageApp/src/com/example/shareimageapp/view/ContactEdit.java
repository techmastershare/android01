package com.example.shareimageapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.shareimageapp.R;
import com.example.shareimageapp.model.Contact;

public class ContactEdit extends LinearLayout {

	public EditText nameEditText;
	public EditText IpEditText;
	public Button deleteContactButton;
	public Button addContactButton;
	public Button cancelButton;


	public ContactEdit(Context context) {
		super(context);
		LayoutInflater li = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.contact_view, this);

		// Init UI
		nameEditText = (EditText) findViewById(R.id.name_edit_text);
		IpEditText = (EditText) findViewById(R.id.ip_edit_text);
		deleteContactButton = (Button) findViewById(R.id.delete_button);
		addContactButton = (Button) findViewById(R.id.ok_button);
		cancelButton = (Button) findViewById(R.id.cancel_button);
	}	

}
