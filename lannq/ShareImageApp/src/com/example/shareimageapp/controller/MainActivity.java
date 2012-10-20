package com.example.shareimageapp.controller;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.plugins.Action;
import com.example.plugins.Common;
import com.example.plugins.P2PTransferPlugin;
import com.example.plugins.TransferInfo;
import com.example.plugins.Utils;
import com.example.shareimageapp.R;
import com.example.shareimageapp.database.ContactDAO;
import com.example.shareimageapp.database.DatabaseManager;
import com.example.shareimageapp.database.TransactionDAO;
import com.example.shareimageapp.model.Contact;
import com.example.shareimageapp.model.ContactAdapter;
import com.example.shareimageapp.model.ContactModel;
import com.example.shareimageapp.model.PhotoListAdapter;
import com.example.shareimageapp.model.PhotoModel;
import com.example.shareimageapp.model.Transaction;
import com.example.shareimageapp.model.TransactionModel;
import com.example.shareimageapp.view.MainView;

public class MainActivity extends Activity {

	private MainView mMainView;
	private String selectedImagePath;
	private ArrayList<PhotoModel> mPhotoModelList;
	private ContactAdapter mContactAdapter;
	private Uri mImageUri = null;
	private long mContactId = 0;
	private PhotoListAdapter mPhotoAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMainView = new MainView(getApplicationContext());
		setContentView(mMainView);

		initList();
		initContact();
		initInfo();

		mMainView.addPhotoButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(
						Intent.createChooser(intent, "Select Picture"),
						Common.SELECT_PICTURE);
			}
		});

		mMainView.historyButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onViewHistoryClick();
			}
		});
		
		mMainView.removePhotoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for (int i = 0; i < mPhotoModelList.size(); i++) {
					PhotoModel pm = mPhotoModelList.get(i);
					if (pm != null
							&& pm.getImageId() == mImageUri) {
						mPhotoModelList.remove(i);
					}
				}
				mPhotoAdapter.notifyDataSetChanged();
			}
		});
	}

	protected void onViewHistoryClick() {
		Intent intent = new Intent(this, HistoryActivity.class);
		startActivity(intent);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == Common.SELECT_PICTURE) {
				Uri selectedImageUri = data.getData();
				selectedImagePath = Utils.getRealPathFromURI(selectedImageUri,
						getApplicationContext());
				if (mPhotoModelList != null) {
					mPhotoModelList.add(new PhotoModel("name", "date",
							selectedImageUri));
				}
				mPhotoAdapter.notifyDataSetChanged();
			}

			else if (requestCode == Common.CONTACT_ACTIVITY_REQUEST_CODE) {
				int action = data.getIntExtra(Common.ACTION_EXTRA,
						Action.UNKNOWN);
				boolean shouldBeUpdated = false;
				Contact contact = null;
				ContactModel cm = this.mContactAdapter.getContactModel();
				if (action == Action.INSERT) {
					contact = (Contact) data
							.getParcelableExtra(Common.CONTACT_EXTRA);
					cm.add(contact);
					shouldBeUpdated = true;
				} else if (action == Action.UPDATE) {
					contact = (Contact) data
							.getParcelableExtra(Common.CONTACT_EXTRA);
					cm.update(contact);
					shouldBeUpdated = true;
				} else if (action == Action.DELETE) {
					contact = (Contact) data
							.getParcelableExtra(Common.CONTACT_EXTRA);
					cm.remove(contact.getContactId());
					shouldBeUpdated = true;
				}
				if (shouldBeUpdated)
					this.mContactAdapter.notifyDataSetChanged();
			}
		}
	}

	protected void initList() {
		mPhotoModelList = new ArrayList<PhotoModel>();
		int imageId = R.drawable.ic_launcher;
		mPhotoAdapter = new PhotoListAdapter(getApplicationContext(),
				mPhotoModelList);
		mMainView.photoListView.setAdapter(mPhotoAdapter);

		mMainView.photoListView
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int pos, long id) {
						PhotoModel photoModel = mPhotoModelList.get(pos);
						mPhotoAdapter.setSelectable(pos);
						mImageUri = photoModel.getImageId();
					}
				});

	}

	protected void initContact() {
		// Open database
		DatabaseManager.getInstance().open(getApplicationContext());

		SQLiteDatabase database = DatabaseManager.getInstance().getDatabase();
		// Get all contacts from database
		ContactDAO contactDAO = new ContactDAO(database);
		ContactModel cm = contactDAO.getAllContacts();
		mContactAdapter = new ContactAdapter(this);
		mContactAdapter.setContactModel(cm);

		mMainView.contactListView
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int pos, long id) {
						ContactModel contactModel = mContactAdapter
								.getContactModel();
						Contact contact = contactModel.get(pos);
						mContactId = contact.getContactId();
						mContactAdapter.setSelectable(pos);
						mMainView.infoTextView.setText("Send photo to IP: "
								+ contact.getIp());
					}
				});

		mMainView.contactListView.setAdapter(mContactAdapter);

		ImageButton btn = mMainView.addContactButton;
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onAddContactClicked();
			}
		});

		ImageButton btn2 = mMainView.removeContactButton;
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				editContactClicked();
			}
		});
	}

	protected void initInfo() {
		mMainView.shareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Start Server
				P2PTransferPlugin plugin = new P2PTransferPlugin();
				plugin.startP2PServer(getApplicationContext());
				Toast.makeText(getApplicationContext(), "Server Started", 1000)
						.show();
				String status = "Not done";
				// Send data
				if (mImageUri != null) {
					if (mContactId != 0) {
						ContactModel cm = mContactAdapter.getContactModel();
						Contact contact = cm.getById(mContactId);
						String ip = contact.getIp();
						if (ip != null) {
							String filePath = Utils.getRealPathFromURI(
									mImageUri, getApplicationContext());
							TransferInfo ti = new TransferInfo();
							ti.setIp(ip);
							ti.setFullFilePathName(filePath);
							plugin.sendFile(Common.SERVER_LISTEN_PORT, ti);

							status = "Completed";

							Toast.makeText(getApplicationContext(),
									"Send Completed", 1000).show();
						}
					} else {
						Toast.makeText(getApplicationContext(),
								"Please choose contact to send", 1000).show();
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"Please choose photo to send", 1000).show();
				}

				// Record to history
				recordHistory(status);
			}
		});
	}

	protected void recordHistory(String status) {
		// Open database
		DatabaseManager.getInstance().open(getApplicationContext());

		SQLiteDatabase database = DatabaseManager.getInstance().getDatabase();
		// Get all contacts from database
		TransactionDAO transactionDAO = new TransactionDAO(database);
		ContactModel contactModel = mContactAdapter.getContactModel();
		Contact contact = contactModel.getById(mContactId);
		Transaction transaction = new Transaction(contact.getName(),
				mImageUri.toString(), status, 0);
		long transactionId = transactionDAO.createTransaction(transaction);
		transaction.setTransactionId(transactionId);
	}

	protected void editContactClicked() {
		Intent intent = new Intent(MainActivity.this, ContactActivity.class);
		if (mContactId != 0) {
			ContactModel cm = mContactAdapter.getContactModel();
			Contact contact = cm.getById(mContactId);
			intent.putExtra(Common.CONTACT_EXTRA, contact);
			startActivityForResult(intent, Common.CONTACT_ACTIVITY_REQUEST_CODE);
		}
	}

	protected void onAddContactClicked() {
		Intent intent = new Intent(this, ContactActivity.class);
		startActivityForResult(intent, Common.CONTACT_ACTIVITY_REQUEST_CODE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_view, menu);
		return true;
	}
}
