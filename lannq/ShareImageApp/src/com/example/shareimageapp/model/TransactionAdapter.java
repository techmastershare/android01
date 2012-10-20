package com.example.shareimageapp.model;

import com.example.shareimageapp.controller.HistoryActivity;
import com.example.shareimageapp.view.ContactView;
import com.example.shareimageapp.view.TransactionView;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TransactionAdapter extends BaseAdapter {
	private Activity mActivity = null;
	private TransactionModel mTransactionModel = null;


	// Keeping the currently selected item
	int mCurrSelected = -1;

	public TransactionAdapter(Activity activity) {
		super();
		this.mActivity = activity;
	}

	public TransactionModel getTransactionModel() {
		return mTransactionModel;
	}

	public void setTransactionModel(TransactionModel transactionModel) {
		this.mTransactionModel = transactionModel;
	}

	public int getCount() {
		if (mTransactionModel == null)
			return 0;
		return mTransactionModel.count();
	}

	public Object getItem(int position) {
		if (mTransactionModel == null)
			return null;
		return mTransactionModel.get(position);
	}

	public long getItemId(int position) {
		return mTransactionModel.get(position).getTransactionId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TransactionView view = (TransactionView) convertView;
		Transaction transaction = mTransactionModel.get(position);
		
		if (convertView == null) {
			view = new TransactionView(this.mActivity);
		}
		
		
		view.setTransaction(transaction);
		
		if (transaction.isSelectedItem()) {
			view.setBackgroundColor(Color.BLUE);
		} else {
			view.setBackgroundColor(Color.BLACK);
		}

		// Update item view
		view.updateView();

		return view;
	}

	public long setSelectable(int position) {
		// The -1 value means that no item is selected
		if (mCurrSelected != -1) {
			Transaction transaction = (Transaction) getItem(mCurrSelected);
			transaction.setSelectedItem(false);
		}

		// Selecting the item in the position we got as an argument
		if (position != -1) {
			Transaction transaction = (Transaction) getItem(position);
			transaction.setSelectedItem(true);
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
