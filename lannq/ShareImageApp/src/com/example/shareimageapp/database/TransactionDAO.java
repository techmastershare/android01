package com.example.shareimageapp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shareimageapp.model.Contact;
import com.example.shareimageapp.model.ContactModel;
import com.example.shareimageapp.model.Transaction;
import com.example.shareimageapp.model.TransactionModel;

public class TransactionDAO {
	private SQLiteDatabase mDatabase;

	/**
	 * Contact table related constants.
	 */
	public final static String TRANSACTION_TABLE = "transactionTable";
	public final static String TRANSACTION_ID = "id";
	public final static String TRANSACTION_PHOTO = "photoPath";
	public final static String TRANSACTION_CONTACT = "contact";
	public final static String TRANSACTION_STATUS = "status";

	public TransactionDAO(SQLiteDatabase database) {
		mDatabase = database;
	}

	public long createTransaction(Transaction transaction) {
		ContentValues values = new ContentValues();
		values.put(TRANSACTION_PHOTO, transaction.getPhotoPath());
		values.put(TRANSACTION_CONTACT, transaction.getContact());
		values.put(TRANSACTION_STATUS, transaction.getStatus());
		return mDatabase.insert(TRANSACTION_TABLE, null, values);
	}

	/**
	 * Fetch all contacts
	 * 
	 * @return
	 */
	public TransactionModel getAllTransaction() {
		TransactionModel tm = new TransactionModel();
		Cursor cursor = mDatabase.query(true, TRANSACTION_TABLE, new String[] {
				TRANSACTION_ID, TRANSACTION_PHOTO, TRANSACTION_CONTACT,
				TRANSACTION_STATUS }, null, null, null, null, null, null);
		int idIndex = cursor.getColumnIndex(TRANSACTION_ID);
		int photoIndex = cursor.getColumnIndex(TRANSACTION_PHOTO);
		int contactIndex = cursor.getColumnIndex(TRANSACTION_CONTACT);
		int statusIndex = cursor.getColumnIndex(TRANSACTION_STATUS);
		if ((cursor != null) && (cursor.getCount() > 0)) {
			cursor.moveToFirst();
			while (true) {
				Transaction transaction = new Transaction(
						cursor.getString(contactIndex),
						cursor.getString(photoIndex),
						cursor.getString(statusIndex), cursor.getLong(idIndex));
				tm.add(transaction);
				if (!cursor.moveToNext())
					break;
			}
		}
		return tm;
	}

	public int updateTransaction(Transaction transaction) {
		ContentValues values = new ContentValues();
		values.put(TRANSACTION_PHOTO, transaction.getPhotoPath());
		values.put(TRANSACTION_CONTACT, transaction.getContact());
		values.put(TRANSACTION_STATUS, transaction.getStatus());
		String id = String.format("%d", transaction.getTransactionId());
		return mDatabase.update(TRANSACTION_TABLE, values, TRANSACTION_ID
				+ "=?", new String[] { id });
	}

	/**
	 * Deletes a contact
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(long transactionId) {
		String id = String.format("%d", transactionId);
		return mDatabase.delete(TRANSACTION_TABLE, TRANSACTION_ID + "=" + id, null) > 0;
	}

	public boolean delete(Transaction transaction) {
		if (transaction == null)
			return false;
		long id = transaction.getTransactionId();
		if (id < 0)
			return false;

		return delete(id);
	}
}
