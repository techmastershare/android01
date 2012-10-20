package com.example.shareimageapp.view;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shareimageapp.R;
import com.example.shareimageapp.model.Transaction;

public class TransactionView extends LinearLayout {

	public TextView mNameContact_textView;
	public ImageView mPhotoImage_imageView;
	public ImageView mContactImage_imageView;
	private Transaction mTransaction;


	public TransactionView(Context context) {
		super(context);
		LayoutInflater li = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.transaction_model, this);

		// Init UI
		mNameContact_textView = (TextView) findViewById(R.id.transactionContactName_textView);
		mPhotoImage_imageView = (ImageView) findViewById(R.id.transationPhoto_imageView);
	}
	
	public void updateView() {
		Uri uri = Uri.parse(mTransaction.getPhotoPath());
		mPhotoImage_imageView.setImageURI(uri);
		mNameContact_textView.setText(mTransaction.getContact());
	}


	public Transaction getTransaction() {
		return mTransaction;
	}

	public void setTransaction(Transaction transaction) {
		this.mTransaction = transaction;
	}	

}
