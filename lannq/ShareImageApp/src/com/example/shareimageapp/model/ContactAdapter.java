package com.example.shareimageapp.model;

import com.example.shareimageapp.view.ContactView;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ContactAdapter extends BaseAdapter {
	private Activity mActivity = null;
	private ContactModel mContactModel = null;


	// Keeping the currently selected item
	int mCurrSelected = -1;

	public ContactAdapter(Activity activity) {
		super();
		this.mActivity = activity;
	}

	public ContactModel getContactModel() {
		return mContactModel;
	}

	public void setContactModel(ContactModel contactModel) {
		this.mContactModel = contactModel;
	}

	public int getCount() {
		if (mContactModel == null)
			return 0;
		return mContactModel.count();
	}

	public Object getItem(int position) {
		if (mContactModel == null)
			return null;
		return mContactModel.get(position);
	}

	public long getItemId(int position) {
		return mContactModel.get(position).getContactId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ContactView view = (ContactView) convertView;
		Contact contact = mContactModel.get(position);
		
		if (convertView == null) {
			view = new ContactView(this.mActivity);
		}
		
		if (contact.isItemSelected()) {
			view.setBackgroundColor(Color.BLUE);
		} else {
			view.setBackgroundColor(Color.BLACK);
		}

		view.setContact(contact);

		// Update item view
		view.updateView();

		return view;
	}

	public long setSelectable(int position) {
		// The -1 value means that no item is selected
		if (mCurrSelected != -1) {
			Contact contact = (Contact) getItem(mCurrSelected);
			contact.setItemSelected(false);
		}

		// Selecting the item in the position we got as an argument
		if (position != -1) {
			Contact contact = (Contact) getItem(position);
			contact.setItemSelected(true);
			mCurrSelected = position;
		}

		// Making the list redraw
		notifyDataSetChanged();

		return getSelectedId();
	}

	public long getSelectedId() {
		if (mCurrSelected == -1)
			return 0;
		else {
			return getItemId(mCurrSelected);
		}
	}
}
