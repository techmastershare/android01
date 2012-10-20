package com.example.shareimageapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.plugins.Action;
import com.example.plugins.Common;
import com.example.shareimageapp.R;
import com.example.shareimageapp.database.ContactDAO;
import com.example.shareimageapp.database.DatabaseManager;
import com.example.shareimageapp.model.Contact;
import com.example.shareimageapp.view.ContactEdit;

public class ContactActivity extends Activity implements OnClickListener {

	private ContactEdit mContactEdit;
	private Contact mContact = null;
	private Action mAction = new Action();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContactEdit = new ContactEdit(this);
		setContentView(mContactEdit);

		try {
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				mContact = extras.getParcelable(Common.CONTACT_EXTRA);
				mAction.set(extras.getInt(Common.ACTION_EXTRA, Action.UNKNOWN));
			}
		} catch (Exception e) {
		}

		initUI();
	}

	protected void initUI() {

		Button okBtn = mContactEdit.addContactButton;
		okBtn.setOnClickListener(this);

		Button cancelBtn = mContactEdit.cancelButton;
		cancelBtn.setOnClickListener(this);

		Button deleteBtn = mContactEdit.deleteContactButton;
		deleteBtn.setOnClickListener(this);

		if (mContact != null) {
			mContactEdit.nameEditText.setText(mContact.getName());
			mContactEdit.IpEditText.setText(mContact.getIp());
			deleteBtn.setVisibility(View.VISIBLE);
			okBtn.setText("Update");
		} else {
			deleteBtn.setVisibility(View.GONE);
			okBtn.setText("Add");
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.ok_button:
			onOkButtonClicked();
			break;
		case R.id.cancel_button:
			onCancelButtonClicked();
			break;
		case R.id.delete_button:
			onDeleteButtonClicked();
			break;
		}
		

	}

	protected void onDeleteButtonClicked() {
		ContactDAO contactDAO = new ContactDAO(DatabaseManager.getInstance()
				.getDatabase());

		if (!contactDAO.delete(mContact)) {
			String text = "Could not delete this contact";
			Toast toast = Toast.makeText(this, text, text.length());
			toast.show();
			return;
		}

		Intent intent = new Intent();
		intent.putExtra(Common.CONTACT_EXTRA, mContact);
		intent.putExtra(Common.ACTION_EXTRA, Action.DELETE); // Delete
		this.setResult(Activity.RESULT_OK, intent);
		finish();
	}

	protected void onOkButtonClicked() {
		ContactDAO contactDAO = new ContactDAO(DatabaseManager.getInstance()
				.getDatabase());

		Contact contact = null;
		String name = mContactEdit.nameEditText.getText().toString();
		String ip = mContactEdit.IpEditText.getText().toString();
		contact = new Contact(name, ip, 0, -1);

		int action;
		if (mContact == null) {
			// Add new
			long id = contactDAO.createContact(contact);
			if (id == -1) {
				String text = "Could not create a new contact";
				Toast toast = Toast.makeText(this, text, text.length());
				toast.show();
				return;
			}

			contact.setContactId(id);
			action = Action.INSERT;
		} else {
			contact.setContactId(mContact.getContactId());
			// Update
			int ret = contactDAO.updateContact(contact);
			if (ret < 0) {
				String text = "Could not update this contact";
				Toast toast = Toast.makeText(this, text, text.length());
				toast.show();
				return;
			}
			action = Action.UPDATE;
		}

		Intent intent = new Intent();
		intent.putExtra(Common.CONTACT_EXTRA, contact);
		intent.putExtra(Common.ACTION_EXTRA, action);
		this.setResult(Activity.RESULT_OK, intent);
		finish();
	}

	protected void onCancelButtonClicked() {
		finish();
	}

}
