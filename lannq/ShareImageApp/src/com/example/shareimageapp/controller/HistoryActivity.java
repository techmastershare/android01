package com.example.shareimageapp.controller;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.shareimageapp.R;
import com.example.shareimageapp.database.DatabaseManager;
import com.example.shareimageapp.database.TransactionDAO;
import com.example.shareimageapp.model.Transaction;
import com.example.shareimageapp.model.TransactionAdapter;
import com.example.shareimageapp.model.TransactionModel;

public class HistoryActivity extends Activity {

	private ListView mHistory_listView;
	private TransactionAdapter mTransactionAdapter;
	private long mTransactionId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transaction_view);
		initUI();
	}

	protected void initUI() {
		mHistory_listView = (ListView) findViewById(R.id.transaction_listView);
		// Open database
		DatabaseManager.getInstance().open(getApplicationContext());

		SQLiteDatabase database = DatabaseManager.getInstance().getDatabase();
		// Get all contacts from database
		final TransactionDAO transactionDAO = new TransactionDAO(database);
		TransactionModel tm = transactionDAO.getAllTransaction();
		mTransactionAdapter = new TransactionAdapter(this);
		mTransactionAdapter.setTransactionModel(tm);
		mHistory_listView.setAdapter(mTransactionAdapter);

		Button backBtn = (Button) findViewById(R.id.back_button);
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		mHistory_listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				TransactionModel tm = mTransactionAdapter.getTransactionModel();
				Transaction transaction = tm.get(pos);
				mTransactionId = transaction.getTransactionId();
				mTransactionAdapter.setSelectable(pos);
			}
		});

		Button deleteBtn = (Button) findViewById(R.id.deleteTransaction_button);
		deleteBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TransactionModel tm = mTransactionAdapter.getTransactionModel();
				transactionDAO.delete(mTransactionId);
				tm.remove(mTransactionId);
				mTransactionAdapter.notifyDataSetChanged();
			}
		});
	}

}
